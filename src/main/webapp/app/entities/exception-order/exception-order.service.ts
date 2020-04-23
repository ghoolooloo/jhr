import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExceptionOrder } from 'app/shared/model/exception-order.model';

type EntityResponseType = HttpResponse<IExceptionOrder>;
type EntityArrayResponseType = HttpResponse<IExceptionOrder[]>;

@Injectable({ providedIn: 'root' })
export class ExceptionOrderService {
  public resourceUrl = SERVER_API_URL + 'api/exception-orders';

  constructor(protected http: HttpClient) {}

  create(exceptionOrder: IExceptionOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(exceptionOrder);
    return this.http
      .post<IExceptionOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(exceptionOrder: IExceptionOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(exceptionOrder);
    return this.http
      .put<IExceptionOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IExceptionOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IExceptionOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(exceptionOrder: IExceptionOrder): IExceptionOrder {
    const copy: IExceptionOrder = Object.assign({}, exceptionOrder, {
      diningDate: exceptionOrder.diningDate && exceptionOrder.diningDate.isValid() ? exceptionOrder.diningDate.toJSON() : undefined,
      createdDate: exceptionOrder.createdDate && exceptionOrder.createdDate.isValid() ? exceptionOrder.createdDate.toJSON() : undefined,
      paidDate: exceptionOrder.paidDate && exceptionOrder.paidDate.isValid() ? exceptionOrder.paidDate.toJSON() : undefined,
      exceptionDate:
        exceptionOrder.exceptionDate && exceptionOrder.exceptionDate.isValid() ? exceptionOrder.exceptionDate.toJSON() : undefined,
      handledDate: exceptionOrder.handledDate && exceptionOrder.handledDate.isValid() ? exceptionOrder.handledDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.diningDate = res.body.diningDate ? moment(res.body.diningDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.paidDate = res.body.paidDate ? moment(res.body.paidDate) : undefined;
      res.body.exceptionDate = res.body.exceptionDate ? moment(res.body.exceptionDate) : undefined;
      res.body.handledDate = res.body.handledDate ? moment(res.body.handledDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((exceptionOrder: IExceptionOrder) => {
        exceptionOrder.diningDate = exceptionOrder.diningDate ? moment(exceptionOrder.diningDate) : undefined;
        exceptionOrder.createdDate = exceptionOrder.createdDate ? moment(exceptionOrder.createdDate) : undefined;
        exceptionOrder.paidDate = exceptionOrder.paidDate ? moment(exceptionOrder.paidDate) : undefined;
        exceptionOrder.exceptionDate = exceptionOrder.exceptionDate ? moment(exceptionOrder.exceptionDate) : undefined;
        exceptionOrder.handledDate = exceptionOrder.handledDate ? moment(exceptionOrder.handledDate) : undefined;
      });
    }
    return res;
  }
}
