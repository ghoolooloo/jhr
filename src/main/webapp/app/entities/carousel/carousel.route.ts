import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICarousel, Carousel } from 'app/shared/model/carousel.model';
import { CarouselService } from './carousel.service';
import { CarouselComponent } from './carousel.component';
import { CarouselDetailComponent } from './carousel-detail.component';
import { CarouselUpdateComponent } from './carousel-update.component';

@Injectable({ providedIn: 'root' })
export class CarouselResolve implements Resolve<ICarousel> {
  constructor(private service: CarouselService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICarousel> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((carousel: HttpResponse<Carousel>) => {
          if (carousel.body) {
            return of(carousel.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Carousel());
  }
}

export const carouselRoute: Routes = [
  {
    path: '',
    component: CarouselComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.carousel.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CarouselDetailComponent,
    resolve: {
      carousel: CarouselResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.carousel.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CarouselUpdateComponent,
    resolve: {
      carousel: CarouselResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.carousel.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CarouselUpdateComponent,
    resolve: {
      carousel: CarouselResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhrApp.carousel.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
