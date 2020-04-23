import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWriteOffCard } from 'app/shared/model/write-off-card.model';

type EntityResponseType = HttpResponse<IWriteOffCard>;
type EntityArrayResponseType = HttpResponse<IWriteOffCard[]>;

@Injectable({ providedIn: 'root' })
export class WriteOffCardService {
  public resourceUrl = SERVER_API_URL + 'api/write-off-cards';

  constructor(protected http: HttpClient) {}

  create(writeOffCard: IWriteOffCard): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(writeOffCard);
    return this.http
      .post<IWriteOffCard>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(writeOffCard: IWriteOffCard): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(writeOffCard);
    return this.http
      .put<IWriteOffCard>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWriteOffCard>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWriteOffCard[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(writeOffCard: IWriteOffCard): IWriteOffCard {
    const copy: IWriteOffCard = Object.assign({}, writeOffCard, {
      validPeriodBeginAt:
        writeOffCard.validPeriodBeginAt && writeOffCard.validPeriodBeginAt.isValid() ? writeOffCard.validPeriodBeginAt.toJSON() : undefined,
      validPeriodEndAt:
        writeOffCard.validPeriodEndAt && writeOffCard.validPeriodEndAt.isValid() ? writeOffCard.validPeriodEndAt.toJSON() : undefined,
      receivedDate: writeOffCard.receivedDate && writeOffCard.receivedDate.isValid() ? writeOffCard.receivedDate.toJSON() : undefined,
      writeOffDate: writeOffCard.writeOffDate && writeOffCard.writeOffDate.isValid() ? writeOffCard.writeOffDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.validPeriodBeginAt = res.body.validPeriodBeginAt ? moment(res.body.validPeriodBeginAt) : undefined;
      res.body.validPeriodEndAt = res.body.validPeriodEndAt ? moment(res.body.validPeriodEndAt) : undefined;
      res.body.receivedDate = res.body.receivedDate ? moment(res.body.receivedDate) : undefined;
      res.body.writeOffDate = res.body.writeOffDate ? moment(res.body.writeOffDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((writeOffCard: IWriteOffCard) => {
        writeOffCard.validPeriodBeginAt = writeOffCard.validPeriodBeginAt ? moment(writeOffCard.validPeriodBeginAt) : undefined;
        writeOffCard.validPeriodEndAt = writeOffCard.validPeriodEndAt ? moment(writeOffCard.validPeriodEndAt) : undefined;
        writeOffCard.receivedDate = writeOffCard.receivedDate ? moment(writeOffCard.receivedDate) : undefined;
        writeOffCard.writeOffDate = writeOffCard.writeOffDate ? moment(writeOffCard.writeOffDate) : undefined;
      });
    }
    return res;
  }
}
