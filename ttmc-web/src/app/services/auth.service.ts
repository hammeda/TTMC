import { HttpClient, HttpInterceptorFn } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, Observable, skip, tap, throwError } from 'rxjs';
import { User } from '../../views/register/user';
import { Credentials } from '../../views/login/credentials';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private http = inject(HttpClient)
  private router = inject(Router);


  private currentResponse: BehaviorSubject<AuthResponse | undefined> = new BehaviorSubject<AuthResponse | undefined>(undefined);


  get currentUser(): User | undefined {
    return this.currentResponse.value?.user;
  }

  get currentName() {
    return this.currentUser?.firstName;
  }

  get token() {
    return this.currentResponse.value?.token;
  }

  get isLogged(): boolean {
    return !!this.currentResponse.value;
  }

  get isAdmin(): boolean {
    return this.currentUser?.roles.includes('ADMIN') ?? false;
  }


  private readonly AUTH_KEY = "AUTH_RESPONSE"

  constructor() {
    const auth = sessionStorage.getItem(this.AUTH_KEY)
    if (auth) {
      this.currentResponse.next(JSON.parse(auth))
    }

    this.currentResponse.pipe(skip(1)).subscribe(response => {
      if (response) {
        sessionStorage.setItem(this.AUTH_KEY, JSON.stringify(response))
      }
      else {
        this.router.navigate(['/home'])
        sessionStorage.clear()
      }
    })
  }

  login(cred: Credentials): Observable<AuthResponse> {
    return this.http.post<AuthResponse>("http://localhost:9090/auth/authenticate", cred)
      //Ajouter des opérations lors de la préparation de l'observable
      .pipe(
        //Permet de lire la valeur qui sera retournée lors de la souscription
        tap(response => this.currentResponse.next(response))
      )
  }

  register(user: User): Observable<AuthResponse> {
    return this.http.post<AuthResponse>("/register", user)
  }

  logout(): void {
    this.currentResponse.next(undefined);
  }

}



export interface AuthResponse {
  token: string;
  user: User;
}
