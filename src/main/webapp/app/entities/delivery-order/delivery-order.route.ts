import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDeliveryOrder, DeliveryOrder } from 'app/shared/model/delivery-order.model';
import { DeliveryOrderService } from './delivery-order.service';
import { DeliveryOrderComponent } from './delivery-order.component';
import { DeliveryOrderDetailComponent } from './delivery-order-detail.component';
import { DeliveryOrderUpdateComponent } from './delivery-order-update.component';

@Injectable({ providedIn: 'root' })
export class DeliveryOrderResolve implements Resolve<IDeliveryOrder> {
  constructor(private service: DeliveryOrderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDeliveryOrder> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((deliveryOrder: HttpResponse<DeliveryOrder>) => {
          if (deliveryOrder.body) {
            return of(deliveryOrder.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DeliveryOrder());
  }
}

export const deliveryOrderRoute: Routes = [
  {
    path: '',
    component: DeliveryOrderComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DeliveryOrderDetailComponent,
    resolve: {
      deliveryOrder: DeliveryOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DeliveryOrderUpdateComponent,
    resolve: {
      deliveryOrder: DeliveryOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DeliveryOrderUpdateComponent,
    resolve: {
      deliveryOrder: DeliveryOrderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.deliveryOrder.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
