import { ActivatedRouteSnapshot, Routes } from '@angular/router';
import { inject } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { AuthGuard } from './tools/auth/auth.guard';

export const routes: Routes = [
    {
        path: "",
        redirectTo: "home",
        pathMatch: "full",  // Demande au router de s'assurer que l'URL "complete" dans le navigateur est exactement la même que le path de cette route 
    },
    {
        path: "home",
        loadComponent: () => import('../views/home/home.component')
            .then(m => m.HomeComponent),
        canActivate: [AuthGuard]
    },
    {
        path: "themes",
        loadComponent: () => import('../views/theme/theme.component')
            .then(m => m.ThemeComponent),
        resolve: {
            themes: () => inject(HttpClient).get("http://localhost:9090/themes")
        },
        canActivate: [AuthGuard]
    },
    {
        path: "themes/:id",
        loadComponent: () => import('../views/theme-editor/theme-editor.component')
            .then(m => m.ThemeEditorComponent),
        resolve: {
            themes: () => inject(HttpClient).get("http://localhost:9090/themes")
        },
        canActivate: [AuthGuard]
    },
    {
        path: "themes/:themeId/questions",
        loadComponent: () => import('../views/question/question.component')
            .then(m => m.QuestionComponent),
        resolve: {
            questions: (route: ActivatedRouteSnapshot) => {
                const themeId = route.paramMap.get('themeId');
                return inject(HttpClient).get(`http://localhost:9090/questions/theme/${themeId}`);
            }
        },
        canActivate: [AuthGuard]
    },

    {
        path: "users",
        loadComponent: () => import('../views/user/user.component')
            .then(m => m.UserComponent),
        resolve: {
            users: () => inject(HttpClient).get("http://localhost:9090/users")
        },
        canActivate: [AuthGuard]
    },
    {
        path: "users/:id",
        loadComponent: () => import('../views/user-editor/user-editor.component')
            .then(m => m.UserEditorComponent),
        resolve: {
            users: () => inject(HttpClient).get("http://localhost:9090/users")
        },
        canActivate: [AuthGuard]
    },
    {
        path: "login",
        loadComponent: () => import('../views/login/login.component')
            .then(m => m.LoginComponent)
    },
    {
        path: "register",
        loadComponent: () => import('../views/register/register.component')
            .then(m => m.RegisterComponent),
        canActivate: [AuthGuard]
    },
];