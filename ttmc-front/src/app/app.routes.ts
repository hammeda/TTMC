import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./home/home.page').then((m) => m.HomePage),
  },
  {
    path: 'game',
    loadComponent: () => import('./component/game/game.component').then((m) => m.GameComponent),
  },
  {
    path: 'question',
    loadComponent: () => import('./component/question/question.component').then((m) => m.QuestionComponent),
  },
  {
    path: 'success',
    loadComponent: () => import('./component/success-page/success-page.component').then((m) => m.SuccessPageComponent),
  },
  {
    path: 'loose',
    loadComponent: () => import('./component/loose-page/loose-page.component').then((m) => m.LoosePageComponent),
  },
];
