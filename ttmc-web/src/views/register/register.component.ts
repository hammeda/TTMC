import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ReactiveFormsModule, ValidatorFn, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractFormComponent } from '../../app/tools/abstract-form/abstract-form.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent extends AbstractFormComponent {

  passwordControl: FormControl = new FormControl("", { validators: [Validators.required, Validators.minLength(6)] })

  confirmPasswordControl: FormControl = new FormControl<any>("", { validators: [Validators.required, this.mustMatch(this.passwordControl)] });

  form: FormGroup = new FormGroup<any>({
    id: new FormControl(0),
    email: new FormControl("ines@gmail.com", { validators: [Validators.required, Validators.email] }),
    firstName: new FormControl("ines", { validators: [Validators.required] }),
    lastName: new FormControl("hammed", { validators: [Validators.required] }),
    password: this.passwordControl,
    roles: new FormControl([], { validators: [Validators.required] })
  });

  constructor(private http: HttpClient, private router: Router) {
    super();
  }

  onSubmit$(): void {
    this.http.post("http://localhost:9090/auth/register", this.form.value).subscribe({
      next: () => {
        this.router.navigate(['/home'])
      },
      error: e => console.log(e)
    });
  }


}

