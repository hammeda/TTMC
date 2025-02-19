import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { catchError, tap, throwError } from 'rxjs';
export const interceptors: HttpInterceptorFn = (req, next) => {
  const service = inject(AuthService);

  console.log('Token récupéré:', service.token);

  if (service.token) {
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${service.token}`,
      },
    });

    console.log('Requête interceptée avec le token:', clonedRequest);

    return next(clonedRequest).pipe(catchError(err => {
      if (err.status == 401) {
        service.logout();
      }
      return throwError(() => err)
    }));
  }
  console.log('Requête interceptée sans token:', req);
  return next(req);
};
