import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss'],
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
})
export class GameComponent {
  players: string[] = [];
  playerImages: string[] = [];
  newPlayer: string = '';
  playerImageTable: string[] = [
    'assets/joueurs/1.PNG',
    'assets/joueurs/2.PNG',
    'assets/joueurs/3.PNG',
    'assets/joueurs/4.PNG',
    'assets/joueurs/5.PNG',
    'assets/joueurs/6.PNG'
  ];
  constructor(private router: Router) { }

  addPlayer() {
    if (this.newPlayer.trim() && this.players.length < 6) {
      this.players.push(this.newPlayer.trim());
      const imageIndex = this.players.length - 1;
      this.playerImages.push(this.playerImageTable[imageIndex]);
      this.newPlayer = '';
    }
  }

  removePlayer(index: number) {
    this.players.splice(index, 1);
    this.playerImages.splice(index, 1);
  }
  goToQuestions() {
    this.router.navigate(['/question'], { queryParams: { players: this.players.join(',') } });
  }
}