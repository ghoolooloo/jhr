import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICarousel } from 'app/shared/model/carousel.model';

type EntityResponseType = HttpResponse<ICarousel>;
type EntityArrayResponseType = HttpResponse<ICarousel[]>;

@Injectable({ providedIn: 'root' })
export class CarouselService {
  public resourceUrl = SERVER_API_URL + 'api/carousels';

  constructor(protected http: HttpClient) {}

  create(carousel: ICarousel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(carousel);
    return this.http
      .post<ICarousel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(carousel: ICarousel): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(carousel);
    return this.http
      .put<ICarousel>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICarousel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICarousel[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(carousel: ICarousel): ICarousel {
    const copy: ICarousel = Object.assign({}, carousel, {
      createdDate: carousel.createdDate && carousel.createdDate.isValid() ? carousel.createdDate.toJSON() : undefined,
      lastModifiedDate: carousel.lastModifiedDate && carousel.lastModifiedDate.isValid() ? carousel.lastModifiedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((carousel: ICarousel) => {
        carousel.createdDate = carousel.createdDate ? moment(carousel.createdDate) : undefined;
        carousel.lastModifiedDate = carousel.lastModifiedDate ? moment(carousel.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
