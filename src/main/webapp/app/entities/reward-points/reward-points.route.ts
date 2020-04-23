import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRewardPoints, RewardPoints } from 'app/shared/model/reward-points.model';
import { RewardPointsService } from './reward-points.service';
import { RewardPointsComponent } from './reward-points.component';
import { RewardPointsDetailComponent } from './reward-points-detail.component';
import { RewardPointsUpdateComponent } from './reward-points-update.component';

@Injectable({ providedIn: 'root' })
export class RewardPointsResolve implements Resolve<IRewardPoints> {
  constructor(private service: RewardPointsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRewardPoints> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rewardPoints: HttpResponse<RewardPoints>) => {
          if (rewardPoints.body) {
            return of(rewardPoints.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RewardPoints());
  }
}

export const rewardPointsRoute: Routes = [
  {
    path: '',
    component: RewardPointsComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.rewardPoints.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RewardPointsDetailComponent,
    resolve: {
      rewardPoints: RewardPointsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.rewardPoints.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RewardPointsUpdateComponent,
    resolve: {
      rewardPoints: RewardPointsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.rewardPoints.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RewardPointsUpdateComponent,
    resolve: {
      rewardPoints: RewardPointsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.rewardPoints.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
