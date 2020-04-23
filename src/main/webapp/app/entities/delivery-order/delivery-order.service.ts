import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDeliveryOrder } from 'app/shared/model/delivery-order.model';

type EntityResponseType = HttpResponse<IDeliveryOrder>;
type EntityArrayResponseType = HttpResponse<IDeliveryOrder[]>;

@Injectable({ providedIn: 'root' })
export class DeliveryOrderService {
  public resourceUrl = SERVER_API_URL + 'api/delivery-orders';

  constructor(protected http: HttpClient) {}

  create(deliveryOrder: IDeliveryOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryOrder);
    return this.http
      .post<IDeliveryOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(deliveryOrder: IDeliveryOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deliveryOrder);
    return this.http
      .put<IDeliveryOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDeliveryOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDeliveryOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(deliveryOrder: IDeliveryOrder): IDeliveryOrder {
    const copy: IDeliveryOrder = Object.assign({}, deliveryOrder, {
      diningDate: deliveryOrder.diningDate && deliveryOrder.diningDate.isValid() ? deliveryOrder.diningDate.toJSON() : undefined,
      createdDate: deliveryOrder.createdDate && deliveryOrder.createdDate.isValid() ? deliveryOrder.createdDate.toJSON() : undefined,
      paidDate: deliveryOrder.paidDate && deliveryOrder.paidDate.isValid() ? deliveryOrder.paidDate.toJSON() : undefined,
      completedDate:
        deliveryOrder.completedDate && deliveryOrder.completedDate.isValid() ? deliveryOrder.completedDate.toJSON() : undefined,
      lastModifiedDate:
        deliveryOrder.lastModifiedDate && deliveryOrder.lastModifiedDate.isValid() ? deliveryOrder.lastModifiedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.diningDate = res.body.diningDate ? moment(res.body.diningDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.paidDate = res.body.paidDate ? moment(res.body.paidDate) : undefined;
      res.body.completedDate = res.body.completedDate ? moment(res.body.completedDate) : undefined;
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((deliveryOrder: IDeliveryOrder) => {
        deliveryOrder.diningDate = deliveryOrder.diningDate ? moment(deliveryOrder.diningDate) : undefined;
        deliveryOrder.createdDate = deliveryOrder.createdDate ? moment(deliveryOrder.createdDate) : undefined;
        deliveryOrder.paidDate = deliveryOrder.paidDate ? moment(deliveryOrder.paidDate) : undefined;
        deliveryOrder.completedDate = deliveryOrder.completedDate ? moment(deliveryOrder.completedDate) : undefined;
        deliveryOrder.lastModifiedDate = deliveryOrder.lastModifiedDate ? moment(deliveryOrder.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
