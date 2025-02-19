import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-theme-editor',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './theme-editor.component.html',
  styleUrl: './theme-editor.component.css'
})
export class ThemeEditorComponent {
  form: FormGroup = new FormGroup<any>({
    id: new FormControl(0),
    version: new FormControl(0),
    name: new FormControl("", { validators: [Validators.required] }),
  });
  constructor(private router: Router, route: ActivatedRoute, private http: HttpClient) {
    route.paramMap.subscribe(param => {
      const id: number = +param.get('id')!;
      this.http.get(`http://localhost:9090/themes/${id}`).subscribe((theme: any) => {
        this.form.patchValue(theme);
      });
    });
  }
  onSubmit(): void {
    if (this.form.valid) {
      this.http[this.form.value.id ? 'put' : 'post']("http://localhost:9090/themes", this.form.value)
        .subscribe(() => this.router.navigate(['/home']))
    }

  }
}
