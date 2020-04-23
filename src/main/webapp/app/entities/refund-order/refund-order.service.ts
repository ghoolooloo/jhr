import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRefundOrder } from 'app/shared/model/refund-order.model';

type EntityResponseType = HttpResponse<IRefundOrder>;
type EntityArrayResponseType = HttpResponse<IRefundOrder[]>;

@Injectable({ providedIn: 'root' })
export class RefundOrderService {
  public resourceUrl = SERVER_API_URL + 'api/refund-orders';

  constructor(protected http: HttpClient) {}

  create(refundOrder: IRefundOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(refundOrder);
    return this.http
      .post<IRefundOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(refundOrder: IRefundOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(refundOrder);
    return this.http
      .put<IRefundOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRefundOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRefundOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(refundOrder: IRefundOrder): IRefundOrder {
    const copy: IRefundOrder = Object.assign({}, refundOrder, {
      diningDate: refundOrder.diningDate && refundOrder.diningDate.isValid() ? refundOrder.diningDate.toJSON() : undefined,
      createdDate: refundOrder.createdDate && refundOrder.createdDate.isValid() ? refundOrder.createdDate.toJSON() : undefined,
      paidDate: refundOrder.paidDate && refundOrder.paidDate.isValid() ? refundOrder.paidDate.toJSON() : undefined,
      completedDate: refundOrder.completedDate && refundOrder.completedDate.isValid() ? refundOrder.completedDate.toJSON() : undefined,
      exceptionDate: refundOrder.exceptionDate && refundOrder.exceptionDate.isValid() ? refundOrder.exceptionDate.toJSON() : undefined,
      handledDate: refundOrder.handledDate && refundOrder.handledDate.isValid() ? refundOrder.handledDate.toJSON() : undefined,
      appliedDate: refundOrder.appliedDate && refundOrder.appliedDate.isValid() ? refundOrder.appliedDate.toJSON() : undefined,
      refundedDate: refundOrder.refundedDate && refundOrder.refundedDate.isValid() ? refundOrder.refundedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.diningDate = res.body.diningDate ? moment(res.body.diningDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.paidDate = res.body.paidDate ? moment(res.body.paidDate) : undefined;
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
      res.body.forEach((refundOrder: IRefundOrder) => {
        refundOrder.diningDate = refundOrder.diningDate ? moment(refundOrder.diningDate) : undefined;
        refundOrder.createdDate = refundOrder.createdDate ? moment(refundOrder.createdDate) : undefined;
        refundOrder.paidDate = refundOrder.paidDate ? moment(refundOrder.paidDate) : undefined;
        refundOrder.completedDate = refundOrder.completedDate ? moment(refundOrder.completedDate) : undefined;
        refundOrder.exceptionDate = refundOrder.exceptionDate ? moment(refundOrder.exceptionDate) : undefined;
        refundOrder.handledDate = refundOrder.handledDate ? moment(refundOrder.handledDate) : undefined;
        refundOrder.appliedDate = refundOrder.appliedDate ? moment(refundOrder.appliedDate) : undefined;
        refundOrder.refundedDate = refundOrder.refundedDate ? moment(refundOrder.refundedDate) : undefined;
      });
    }
    return res;
  }
}
