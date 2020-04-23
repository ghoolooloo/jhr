import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRefundOrder, RefundOrder } from 'app/shared/model/refund-order.model';
import { RefundOrderService } from './refund-order.service';
import { RefundOrderComponent } from './refund-order.component';
import { RefundOrderDetailComponent } from './refund-order-detail.component';
import { RefundOrderUpdateComponent } from './refund-order-update.component';

@Injectable({ providedIn: 'root' })
export class RefundOrderResolve implements Resolve<IRefundOrder> {
  constructor(private service: RefundOrderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRefundOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((refundOrder: HttpResponse<RefundOrder>) => {
          if (refundOrder.body) {
            return of(refundOrder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RefundOrder());
  }
}

export const refundOrderRoute: Routes = [
  {
    path: '',
    component: RefundOrderComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.refundOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: RefundOrderDetailComponent,
    resolve: {
      refundOrder: RefundOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.refundOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: RefundOrderUpdateComponent,
    resolve: {
      refundOrder: RefundOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.refundOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: RefundOrderUpdateComponent,
    resolve: {
      refundOrder: RefundOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.refundOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
