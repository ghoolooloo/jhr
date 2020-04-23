import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IClosedOrder } from 'app/shared/model/closed-order.model';

type EntityResponseType = HttpResponse<IClosedOrder>;
type EntityArrayResponseType = HttpResponse<IClosedOrder[]>;

@Injectable({ providedIn: 'root' })
export class ClosedOrderService {
  public resourceUrl = SERVER_API_URL + 'api/closed-orders';

  constructor(protected http: HttpClient) {}

  create(closedOrder: IClosedOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(closedOrder);
    return this.http
      .post<IClosedOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(closedOrder: IClosedOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(closedOrder);
    return this.http
      .put<IClosedOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IClosedOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IClosedOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(closedOrder: IClosedOrder): IClosedOrder {
    const copy: IClosedOrder = Object.assign({}, closedOrder, {
      diningDate: closedOrder.diningDate && closedOrder.diningDate.isValid() ? closedOrder.diningDate.toJSON() : undefined,
      createdDate: closedOrder.createdDate && closedOrder.createdDate.isValid() ? closedOrder.createdDate.toJSON() : undefined,
      paidDate: closedOrder.paidDate && closedOrder.paidDate.isValid() ? closedOrder.paidDate.toJSON() : undefined,
      expiredDate: closedOrder.expiredDate && closedOrder.expiredDate.isValid() ? closedOrder.expiredDate.toJSON() : undefined,
      completedDate: closedOrder.completedDate && closedOrder.completedDate.isValid() ? closedOrder.completedDate.toJSON() : undefined,
      exceptionDate: closedOrder.exceptionDate && closedOrder.exceptionDate.isValid() ? closedOrder.exceptionDate.toJSON() : undefined,
      handledDate: closedOrder.handledDate && closedOrder.handledDate.isValid() ? closedOrder.handledDate.toJSON() : undefined,
      appliedDate: closedOrder.appliedDate && closedOrder.appliedDate.isValid() ? closedOrder.appliedDate.toJSON() : undefined,
      refundedDate: closedOrder.refundedDate && closedOrder.refundedDate.isValid() ? closedOrder.refundedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.diningDate = res.body.diningDate ? moment(res.body.diningDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.paidDate = res.body.paidDate ? moment(res.body.paidDate) : undefined;
      res.body.expiredDate = res.body.expiredDate ? moment(res.body.expiredDate) : undefined;
      res.body.completedDate = res.body.completedDate ? moment(res.body.completedDate) : undefined;
      res.body.exceptionDate = res.body.exceptionDate ? moment(res.body.exceptionDate) : undefined;
      res.body.handledDate = res.body.handledDate ? moment(res.body.handledDate) : undefined;
      res.body.appliedDate = res.body.appliedDate ? moment(res.body.appliedDate) : undefined;
      res.body.refundedDate = res.body.refundedDate ? moment(res.body.refundedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((closedOrder: IClosedOrder) => {
        closedOrder.diningDate = closedOrder.diningDate ? moment(closedOrder.diningDate) : undefined;
        closedOrder.createdDate = closedOrder.createdDate ? moment(closedOrder.createdDate) : undefined;
        closedOrder.paidDate = closedOrder.paidDate ? moment(closedOrder.paidDate) : undefined;
        closedOrder.expiredDate = closedOrder.expiredDate ? moment(closedOrder.expiredDate) : undefined;
        closedOrder.completedDate = closedOrder.completedDate ? moment(closedOrder.completedDate) : undefined;
        closedOrder.exceptionDate = closedOrder.exceptionDate ? moment(closedOrder.exceptionDate) : undefined;
        closedOrder.handledDate = closedOrder.handledDate ? moment(closedOrder.handledDate) : undefined;
        closedOrder.appliedDate = closedOrder.appliedDate ? moment(closedOrder.appliedDate) : undefined;
        closedOrder.refundedDate = closedOrder.refundedDate ? moment(closedOrder.refundedDate) : undefined;
      });
    }
    return res;
  }
}
