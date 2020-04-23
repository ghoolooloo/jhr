import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFinalCard } from 'app/shared/model/final-card.model';

type EntityResponseType = HttpResponse<IFinalCard>;
type EntityArrayResponseType = HttpResponse<IFinalCard[]>;

@Injectable({ providedIn: 'root' })
export class FinalCardService {
  public resourceUrl = SERVER_API_URL + 'api/final-cards';

  constructor(protected http: HttpClient) {}

  create(finalCard: IFinalCard): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(finalCard);
    return this.http
      .post<IFinalCard>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(finalCard: IFinalCard): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(finalCard);
    return this.http
      .put<IFinalCard>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFinalCard>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFinalCard[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(finalCard: IFinalCard): IFinalCard {
    const copy: IFinalCard = Object.assign({}, finalCard, {
      validPeriodBeginAt:
        finalCard.validPeriodBeginAt && finalCard.validPeriodBeginAt.isValid() ? finalCard.validPeriodBeginAt.toJSON() : undefined,
      validPeriodEndAt:
        finalCard.validPeriodEndAt && finalCard.validPeriodEndAt.isValid() ? finalCard.validPeriodEndAt.toJSON() : undefined,
      createdDate: finalCard.createdDate && finalCard.createdDate.isValid() ? finalCard.createdDate.toJSON() : undefined,
      deliveriedDate: finalCard.deliveriedDate && finalCard.deliveriedDate.isValid() ? finalCard.deliveriedDate.toJSON() : undefined,
      discontinueDate: finalCard.discontinueDate && finalCard.discontinueDate.isValid() ? finalCard.discontinueDate.toJSON() : undefined,
      lastModifiedDate: finalCard.lastModifiedDate && finalCard.lastModifiedDate.isValid() ? finalCard.lastModifiedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.validPeriodBeginAt = res.body.validPeriodBeginAt ? moment(res.body.validPeriodBeginAt) : undefined;
      res.body.validPeriodEndAt = res.body.validPeriodEndAt ? moment(res.body.validPeriodEndAt) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.deliveriedDate = res.body.deliveriedDate ? moment(res.body.deliveriedDate) : undefined;
      res.body.discontinueDate = res.body.discontinueDate ? moment(res.body.discontinueDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((finalCard: IFinalCard) => {
        finalCard.validPeriodBeginAt = finalCard.validPeriodBeginAt ? moment(finalCard.validPeriodBeginAt) : undefined;
        finalCard.validPeriodEndAt = finalCard.validPeriodEndAt ? moment(finalCard.validPeriodEndAt) : undefined;
        finalCard.createdDate = finalCard.createdDate ? moment(finalCard.createdDate) : undefined;
        finalCard.deliveriedDate = finalCard.deliveriedDate ? moment(finalCard.deliveriedDate) : undefined;
        finalCard.discontinueDate = finalCard.discontinueDate ? moment(finalCard.discontinueDate) : undefined;
        finalCard.lastModifiedDate = finalCard.lastModifiedDate ? moment(finalCard.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
