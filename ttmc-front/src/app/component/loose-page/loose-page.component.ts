import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-loose-page',
  templateUrl: './loose-page.component.html',
  styleUrls: ['./loose-page.component.scss'],
  standalone: true,
  imports: [],
})
export class LoosePageComponent implements OnInit {
  currentPlayer: string | null = null;
  difficulty: number | null = null;

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.currentPlayer = params['currentPlayer'] || 'Joueur inconnu';
      this.difficulty = params['difficulty'] ? parseInt(params['difficulty'], 10) : null;
    });
  }

  goToNext(): void {
    this.router.navigate(['/question']);
  }
}