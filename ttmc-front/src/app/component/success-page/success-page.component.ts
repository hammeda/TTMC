import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-success-page',
  templateUrl: './success-page.component.html',
  styleUrls: ['./success-page.component.scss'],
  standalone: true,
  imports: [],
})

export class SuccessPageComponent implements OnInit {
  currentPlayer: string | null = null; 
  message: string = 'Tu peux distribuer 3 gorgées !';

  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.currentPlayer = params['currentPlayer'] || 'Joueur inconnu';
    });
  }

  goToNext(): void {
    this.router.navigate(['/question']); 
  }
}