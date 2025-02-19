import { HttpClient } from '@angular/common/http';
import { Component, DestroyRef, inject, Input, OnInit } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormArray, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs';
import { Theme } from '../theme/theme';
import { Question } from './question';
import { QuestionInputComponent } from "./question-input/question-input.component";
import { AuthService } from '../../app/services/auth.service';

@Component({
  selector: 'app-question',
  standalone: true,
  imports: [ReactiveFormsModule, QuestionInputComponent],
  templateUrl: './question.component.html',
  styleUrl: './question.component.css'
})
export class QuestionComponent implements OnInit {
  theme!: Theme;

  ngOnInit(): void {

    const navigation = history.state;
    this.theme = navigation.theme;
    console.log('Received theme:', this.theme);

  }


}
