import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICardHolder } from 'app/shared/model/card-holder.model';

type EntityResponseType = HttpResponse<ICardHolder>;
type EntityArrayResponseType = HttpResponse<ICardHolder[]>;

@Injectable({ providedIn: 'root' })
export class CardHolderService {
  public resourceUrl = SERVER_API_URL + 'api/card-holders';

  constructor(protected http: HttpClient) {}

  create(cardHolder: ICardHolder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cardHolder);
    return this.http
      .post<ICardHolder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(cardHolder: ICardHolder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(cardHolder);
    return this.http
      .put<ICardHolder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICardHolder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICardHolder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(cardHolder: ICardHolder): ICardHolder {
    const copy: ICardHolder = Object.assign({}, cardHolder, {
      validPeriodBeginAt:
        cardHolder.validPeriodBeginAt && cardHolder.validPeriodBeginAt.isValid() ? cardHolder.validPeriodBeginAt.toJSON() : undefined,
      validPeriodEndAt:
        cardHolder.validPeriodEndAt && cardHolder.validPeriodEndAt.isValid() ? cardHolder.validPeriodEndAt.toJSON() : undefined,
      receivedDate: cardHolder.receivedDate && cardHolder.receivedDate.isValid() ? cardHolder.receivedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.validPeriodBeginAt = res.body.validPeriodBeginAt ? moment(res.body.validPeriodBeginAt) : undefined;
      res.body.validPeriodEndAt = res.body.validPeriodEndAt ? moment(res.body.validPeriodEndAt) : undefined;
      res.body.receivedDate = res.body.receivedDate ? moment(res.body.receivedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((cardHolder: ICardHolder) => {
        cardHolder.validPeriodBeginAt = cardHolder.validPeriodBeginAt ? moment(cardHolder.validPeriodBeginAt) : undefined;
        cardHolder.validPeriodEndAt = cardHolder.validPeriodEndAt ? moment(cardHolder.validPeriodEndAt) : undefined;
        cardHolder.receivedDate = cardHolder.receivedDate ? moment(cardHolder.receivedDate) : undefined;
      });
    }
    return res;
  }
}
