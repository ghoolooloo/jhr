import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICard } from 'app/shared/model/card.model';

type EntityResponseType = HttpResponse<ICard>;
type EntityArrayResponseType = HttpResponse<ICard[]>;

@Injectable({ providedIn: 'root' })
export class CardService {
  public resourceUrl = SERVER_API_URL + 'api/cards';

  constructor(protected http: HttpClient) {}

  create(card: ICard): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(card);
    return this.http
      .post<ICard>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(card: ICard): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(card);
    return this.http
      .put<ICard>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICard>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICard[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(card: ICard): ICard {
    const copy: ICard = Object.assign({}, card, {
      validPeriodBeginAt: card.validPeriodBeginAt && card.validPeriodBeginAt.isValid() ? card.validPeriodBeginAt.toJSON() : undefined,
      validPeriodEndAt: card.validPeriodEndAt && card.validPeriodEndAt.isValid() ? card.validPeriodEndAt.toJSON() : undefined,
      createdDate: card.createdDate && card.createdDate.isValid() ? card.createdDate.toJSON() : undefined,
      deliveriedDate: card.deliveriedDate && card.deliveriedDate.isValid() ? card.deliveriedDate.toJSON() : undefined,
      lastModifiedDate: card.lastModifiedDate && card.lastModifiedDate.isValid() ? card.lastModifiedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.validPeriodBeginAt = res.body.validPeriodBeginAt ? moment(res.body.validPeriodBeginAt) : undefined;
      res.body.validPeriodEndAt = res.body.validPeriodEndAt ? moment(res.body.validPeriodEndAt) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.deliveriedDate = res.body.deliveriedDate ? moment(res.body.deliveriedDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((card: ICard) => {
        card.validPeriodBeginAt = card.validPeriodBeginAt ? moment(card.validPeriodBeginAt) : undefined;
        card.validPeriodEndAt = card.validPeriodEndAt ? moment(card.validPeriodEndAt) : undefined;
        card.createdDate = card.createdDate ? moment(card.createdDate) : undefined;
        card.deliveriedDate = card.deliveriedDate ? moment(card.deliveriedDate) : undefined;
        card.lastModifiedDate = card.lastModifiedDate ? moment(card.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
