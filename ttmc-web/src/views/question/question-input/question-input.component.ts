import { Component, DestroyRef, inject, Input } from '@angular/core';
import { Theme } from '../../theme/theme';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from '../question';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../../app/services/auth.service';

@Component({
  selector: 'app-question-input',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './question-input.component.html',
  styleUrl: './question-input.component.css'
})
export class QuestionInputComponent {
  questions!: Question;
  @Input({ required: true }) theme!: Theme;
  @Input({ required: true }) difficulte!: number;
  protected http: HttpClient = inject(HttpClient)
  protected router: Router = inject(Router)


  protected auth = inject(AuthService);

  form: FormGroup = new FormGroup<any>({
    id: new FormControl(0),
    version: new FormControl(0),
    nomQuestion: new FormControl("", { validators: [Validators.required] }),
    reponse: new FormControl("", { validators: [Validators.required] }),
    difficulte: new FormControl(0, { validators: [Validators.required] }),
    theme: new FormControl({}, { validators: [Validators.required] }),
  });

  ngOnInit(): void {
    const navigation = history.state;
    this.theme = navigation.theme;
    console.log('Received theme:', this.theme);
    this.http.get<Question>("http://localhost:9090/questions/theme/" + this.theme.id + "/difficulte/" + this.difficulte)
      .subscribe(data => { this.form.patchValue(data); this.questions = data })
    this.form.patchValue({ theme: this.theme, difficulte: this.difficulte })
  }

  onSubmit(): void {
    console.log(this.form.value)
    if (this.form.valid) {
      this.http[this.form.value.id ? 'put' : 'post']("http://localhost:9090/questions", this.form.value)
        .subscribe(data => this.form.patchValue(data))
    }
  }
}