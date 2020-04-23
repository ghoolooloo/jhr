import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRewardPointsRecord, RewardPointsRecord } from 'app/shared/model/reward-points-record.model';
import { RewardPointsRecordService } from './reward-points-record.service';
import { RewardPointsRecordComponent } from './reward-points-record.component';
import { RewardPointsRecordDetailComponent } from './reward-points-record-detail.component';
import { RewardPointsRecordUpdateComponent } from './reward-points-record-update.component';

@Injectable({ providedIn: 'root' })
export class RewardPointsRecordResolve implements Resolve<IRewardPointsRecord> {
  constructor(private service: RewardPointsRecordService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRewardPointsRecord> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rewardPointsRecord: HttpResponse<RewardPointsRecord>) => {
          if (rewardPointsRecord.body) {
            return of(rewardPointsRecord.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RewardPointsRecord());
  }
}

export const rewardPointsRecordRoute: Routes = [
  {
    path: '',
    component: RewardPointsRecordComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.rewardPointsRecord.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RewardPointsRecordDetailComponent,
    resolve: {
      rewardPointsRecord: RewardPointsRecordResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.rewardPointsRecord.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RewardPointsRecordUpdateComponent,
    resolve: {
      rewardPointsRecord: RewardPointsRecordResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.rewardPointsRecord.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RewardPointsRecordUpdateComponent,
    resolve: {
      rewardPointsRecord: RewardPointsRecordResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.rewardPointsRecord.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
