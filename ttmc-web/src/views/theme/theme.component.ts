import { HttpClient } from '@angular/common/http';
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Theme } from './theme';
import { AuthService } from '../../app/services/auth.service';

@Component({
  selector: 'app-theme',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './theme.component.html',
  styleUrl: './theme.component.css'
})
export class ThemeComponent implements OnInit {
  themes: Theme[] = [];
  protected auth = inject(AuthService);
  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {

  }

  ngOnInit(): void {
    this.getThemes()
  }

  private getThemes() {
    this.http.get<any>('http://localhost:9090/themes').subscribe((data) => {
      this.themes = data.content || [];
      console.log(data);
    });
  }

  deleteTheme(themeId: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce thème ?')) {
      this.http.delete(`http://localhost:9090/themes/${themeId}`).subscribe({
        next: () => {
          this.getThemes()
        },
        error: () => {
          alert("Une erreur s'est produite lors de la suppression du thème.");
        },
      });
    }
  }

  goToQuestions(theme: Theme): void {
    this.router.navigate([`/themes`, theme.id, `questions`], {
      state: { theme }
    });
  }
}