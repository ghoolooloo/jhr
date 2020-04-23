import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IClosedOrder, ClosedOrder } from 'app/shared/model/closed-order.model';
import { ClosedOrderService } from './closed-order.service';
import { ClosedOrderComponent } from './closed-order.component';
import { ClosedOrderDetailComponent } from './closed-order-detail.component';
import { ClosedOrderUpdateComponent } from './closed-order-update.component';

@Injectable({ providedIn: 'root' })
export class ClosedOrderResolve implements Resolve<IClosedOrder> {
  constructor(private service: ClosedOrderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IClosedOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((closedOrder: HttpResponse<ClosedOrder>) => {
          if (closedOrder.body) {
            return of(closedOrder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ClosedOrder());
  }
}

export const closedOrderRoute: Routes = [
  {
    path: '',
    component: ClosedOrderComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.closedOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ClosedOrderDetailComponent,
    resolve: {
      closedOrder: ClosedOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.closedOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ClosedOrderUpdateComponent,
    resolve: {
      closedOrder: ClosedOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.closedOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ClosedOrderUpdateComponent,
    resolve: {
      closedOrder: ClosedOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.closedOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
