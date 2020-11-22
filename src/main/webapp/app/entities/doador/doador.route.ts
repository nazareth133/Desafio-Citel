import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDoador, Doador } from 'app/shared/model/doador.model';
import { DoadorService } from './doador.service';
import { DoadorComponent } from './doador.component';
import { DoadorDetailComponent } from './doador-detail.component';

@Injectable({ providedIn: 'root' })
export class DoadorResolve implements Resolve<IDoador> {
  constructor(private service: DoadorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDoador> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((doador: HttpResponse<Doador>) => {
          if (doador.body) {
            return of(doador.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Doador());
  }
}

export const doadorRoute: Routes = [
  {
    path: '',
    component: DoadorComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Doadors',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DoadorDetailComponent,
    resolve: {
      doador: DoadorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Doadors',
    },
    canActivate: [UserRouteAccessService],
  },
];
