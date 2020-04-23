import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFinalCard, FinalCard } from 'app/shared/model/final-card.model';
import { FinalCardService } from './final-card.service';
import { FinalCardComponent } from './final-card.component';
import { FinalCardDetailComponent } from './final-card-detail.component';
import { FinalCardUpdateComponent } from './final-card-update.component';

@Injectable({ providedIn: 'root' })
export class FinalCardResolve implements Resolve<IFinalCard> {
  constructor(private service: FinalCardService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFinalCard> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((finalCard: HttpResponse<FinalCard>) => {
          if (finalCard.body) {
            return of(finalCard.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FinalCard());
  }
}

export const finalCardRoute: Routes = [
  {
    path: '',
    component: FinalCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.finalCard.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FinalCardDetailComponent,
    resolve: {
      finalCard: FinalCardResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.finalCard.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FinalCardUpdateComponent,
    resolve: {
      finalCard: FinalCardResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.finalCard.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FinalCardUpdateComponent,
    resolve: {
      finalCard: FinalCardResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.finalCard.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
