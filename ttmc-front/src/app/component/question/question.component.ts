import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, inject, Inject, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.scss'],
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule, HttpClientModule],
})
export class QuestionComponent implements OnInit {
  router = inject(Router);
  @Input() players: string[] = [];
  themes: any[] = [];
  playerThemes: Map<string, any> = new Map();
  playerQuestions: Map<string, any[]> = new Map();
  isAnswerVisible: boolean = false;

  sliderValue: number = 0; // Valeur initiale du slider (10 secondes)
  selectedDifficulty: number | null = null; // Niveau de difficulté sélectionné
  currentQuestion: any = null; // Question actuellement affichée
  timerInterval: any = null;

  currentPlayerIndex: number = 0;

  constructor(private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe((params: any) => {
      const playersParam = params['players'];
      if (playersParam) {
        this.players = playersParam.split(',');
      }
    });
    this.fetchThemes();
  }

  fetchThemes(): void {
    this.http.get<any>('http://192.168.1.15:9090/themes').subscribe(
      //this.http.get<any>('http://172.20.10.2:9090/themes').subscribe(
      response => {
        this.themes = response.content;
        this.assignThemesToPlayers();
      },
      error => {
        console.error('Error fetching themes:', error);
      }
    );
  }

  fetchQuestionsForTheme(themeId: number, player: string): void {
    this.http.get<any[]>(`http://192.168.1.15:9090/questions/theme/${themeId}`).subscribe(
      //this.http.get<any[]>(`http://172.20.10.2:9090/questions/theme/${themeId}`).subscribe(
      questions => {
        this.playerQuestions.set(player, questions);
      },
      error => {
        console.error(`Error fetching questions for theme ${themeId}:`, error);
      }
    );
  }

  assignThemesToPlayers(): void {
    this.players.forEach((player, index) => {
      const theme = this.themes[index % this.themes.length];
      this.playerThemes.set(player, theme);
      this.fetchQuestionsForTheme(theme.id, player);
    });
  }

  get currentPlayer(): string {
    return this.players[this.currentPlayerIndex];
  }

  getQuestionsForCurrentPlayer(): any[] {
    const currentPlayer = this.players[this.currentPlayerIndex];
    return this.playerQuestions.get(currentPlayer) || [];
  }

  selectDifficulty(difficulty: number): void {
    this.selectedDifficulty = difficulty;
    this.sliderValue = 0; // Réinitialise le slider (10 secondes)
    this.currentQuestion = this.getQuestionsForCurrentPlayer().find(q => q.difficulte === difficulty);

    // Lancer le slider comme un timer
    this.startSliderTimer();
  }

  startSliderTimer(): void {
    if (this.timerInterval) {
      clearInterval(this.timerInterval);
    }

    this.timerInterval = setInterval(() => {
      if (this.sliderValue < 10) {
        this.sliderValue++;
      } else {
        clearInterval(this.timerInterval);
        this.timerInterval = null;
        // Action à la fin du timer
        this.timeUp();
      }
    }, 1000);
  }

  timeUp(): void {
    const currentPlayer = this.players[this.currentPlayerIndex];
    const difficulty = this.selectedDifficulty;
    // setTimeout(() => {
    //   this.router.navigate(['/loose'], { queryParams: { currentPlayer, difficulty } });
    // }, 500);
    this.currentQuestion = null;
  }

  nextPlayer(): void {
    this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.players.length;
    this.selectedDifficulty = null;
    this.currentQuestion = null;
    this.sliderValue = 10;
    if (this.timerInterval) {
      clearInterval(this.timerInterval);
      this.timerInterval = null;
    }
  }
  answerIsCorrect: boolean | null = null;

  validateAnswer(isCorrect: boolean): void {
    this.answerIsCorrect = isCorrect;
    const currentPlayer = this.players[this.currentPlayerIndex];
    const difficulty = this.selectedDifficulty;
    if (isCorrect) {

      setTimeout(() => {
        this.router.navigate(['/success'], { queryParams: { currentPlayer, difficulty } });
      }, 500);
    } else {
      setTimeout(() => {
        this.router.navigate(['/loose'], { queryParams: { currentPlayer, difficulty } });
      }, 500);
    }
  }

  showAnswer(): void {
    this.isAnswerVisible = true;
  }

  hideAnswer(): void {
    this.isAnswerVisible = false;
  }

}
