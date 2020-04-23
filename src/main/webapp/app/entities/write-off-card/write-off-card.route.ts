import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWriteOffCard, WriteOffCard } from 'app/shared/model/write-off-card.model';
import { WriteOffCardService } from './write-off-card.service';
import { WriteOffCardComponent } from './write-off-card.component';
import { WriteOffCardDetailComponent } from './write-off-card-detail.component';
import { WriteOffCardUpdateComponent } from './write-off-card-update.component';

@Injectable({ providedIn: 'root' })
export class WriteOffCardResolve implements Resolve<IWriteOffCard> {
  constructor(private service: WriteOffCardService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWriteOffCard> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((writeOffCard: HttpResponse<WriteOffCard>) => {
          if (writeOffCard.body) {
            return of(writeOffCard.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WriteOffCard());
  }
}

export const writeOffCardRoute: Routes = [
  {
    path: '',
    component: WriteOffCardComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.writeOffCard.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WriteOffCardDetailComponent,
    resolve: {
      writeOffCard: WriteOffCardResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.writeOffCard.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WriteOffCardUpdateComponent,
    resolve: {
      writeOffCard: WriteOffCardResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.writeOffCard.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WriteOffCardUpdateComponent,
    resolve: {
      writeOffCard: WriteOffCardResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.writeOffCard.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
