import { Component, OnInit } from '@angular/core';
import { User } from '../register/user';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit {
  users: User[] = [];

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {

  }

  ngOnInit(): void {
    this.getUsers()
  }

  private getUsers() {
    this.http.get<any>('http://localhost:9090/users').subscribe((data) => {
      this.users = data.content || [];
      console.log(data);
    });
  }

  deleteUser(userId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce user ?')) {
      this.http.delete(`http://localhost:9090/users/${userId}`).subscribe({
        next: () => {
          this.getUsers()
        },
        error: () => {
          alert("Une erreur s'est produite lors de la suppression du thème.");
        },
      });
    }
  }

}
