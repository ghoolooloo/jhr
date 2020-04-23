import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICardHolder, CardHolder } from 'app/shared/model/card-holder.model';
import { CardHolderService } from './card-holder.service';
import { CardHolderComponent } from './card-holder.component';
import { CardHolderDetailComponent } from './card-holder-detail.component';
import { CardHolderUpdateComponent } from './card-holder-update.component';

@Injectable({ providedIn: 'root' })
export class CardHolderResolve implements Resolve<ICardHolder> {
  constructor(private service: CardHolderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICardHolder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((cardHolder: HttpResponse<CardHolder>) => {
          if (cardHolder.body) {
            return of(cardHolder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CardHolder());
  }
}

export const cardHolderRoute: Routes = [
  {
    path: '',
    component: CardHolderComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.cardHolder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CardHolderDetailComponent,
    resolve: {
      cardHolder: CardHolderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.cardHolder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CardHolderUpdateComponent,
    resolve: {
      cardHolder: CardHolderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.cardHolder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CardHolderUpdateComponent,
    resolve: {
      cardHolder: CardHolderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.cardHolder.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
