import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDeliveryAddress, DeliveryAddress } from 'app/shared/model/delivery-address.model';
import { DeliveryAddressService } from './delivery-address.service';
import { DeliveryAddressComponent } from './delivery-address.component';
import { DeliveryAddressDetailComponent } from './delivery-address-detail.component';
import { DeliveryAddressUpdateComponent } from './delivery-address-update.component';

@Injectable({ providedIn: 'root' })
export class DeliveryAddressResolve implements Resolve<IDeliveryAddress> {
  constructor(private service: DeliveryAddressService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDeliveryAddress> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((deliveryAddress: HttpResponse<DeliveryAddress>) => {
          if (deliveryAddress.body) {
            return of(deliveryAddress.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DeliveryAddress());
  }
}

export const deliveryAddressRoute: Routes = [
  {
    path: '',
    component: DeliveryAddressComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.deliveryAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DeliveryAddressDetailComponent,
    resolve: {
      deliveryAddress: DeliveryAddressResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.deliveryAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DeliveryAddressUpdateComponent,
    resolve: {
      deliveryAddress: DeliveryAddressResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.deliveryAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DeliveryAddressUpdateComponent,
    resolve: {
      deliveryAddress: DeliveryAddressResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.deliveryAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
