import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-user-editor',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './user-editor.component.html',
  styleUrl: './user-editor.component.css'
})
export class UserEditorComponent {
  form: FormGroup = new FormGroup<any>({
    id: new FormControl(0),
    version: new FormControl(0),
    firstName: new FormControl("", { validators: [Validators.required] }),
    lastName: new FormControl("", { validators: [Validators.required] }),
    email: new FormControl("", { validators: [Validators.required, Validators.email] }),
    // password: new FormControl("", { validators: [Validators.required, Validators.minLength(6)] }),
    roles: new FormControl([], Validators.required)
  });
  constructor(private router: Router, route: ActivatedRoute, private http: HttpClient) {
    route.paramMap.subscribe(param => {
      const id: number = +param.get('id')!;
      this.http.get(`http://localhost:9090/users/${id}`).subscribe((user: any) => {
        this.form.patchValue(user);
      });
    });
  }
  onSubmit(): void {
    if (this.form.valid) {
      this.http.put("http://localhost:9090/users", this.form.value)
        .subscribe(() => this.router.navigate(['/home']))
    }

  }
}
