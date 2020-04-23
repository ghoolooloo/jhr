import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFoodSalesReport, FoodSalesReport } from 'app/shared/model/food-sales-report.model';
import { FoodSalesReportService } from './food-sales-report.service';
import { FoodSalesReportComponent } from './food-sales-report.component';
import { FoodSalesReportDetailComponent } from './food-sales-report-detail.component';
import { FoodSalesReportUpdateComponent } from './food-sales-report-update.component';

@Injectable({ providedIn: 'root' })
export class FoodSalesReportResolve implements Resolve<IFoodSalesReport> {
  constructor(private service: FoodSalesReportService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFoodSalesReport> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((foodSalesReport: HttpResponse<FoodSalesReport>) => {
          if (foodSalesReport.body) {
            return of(foodSalesReport.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FoodSalesReport());
  }
}

export const foodSalesReportRoute: Routes = [
  {
    path: '',
    component: FoodSalesReportComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'jhrApp.foodSalesReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FoodSalesReportDetailComponent,
    resolve: {
      foodSalesReport: FoodSalesReportResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.foodSalesReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FoodSalesReportUpdateComponent,
    resolve: {
      foodSalesReport: FoodSalesReportResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.foodSalesReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FoodSalesReportUpdateComponent,
    resolve: {
      foodSalesReport: FoodSalesReportResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.foodSalesReport.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
