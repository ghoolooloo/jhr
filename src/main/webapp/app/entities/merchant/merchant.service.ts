import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMerchant } from 'app/shared/model/merchant.model';

type EntityResponseType = HttpResponse<IMerchant>;
type EntityArrayResponseType = HttpResponse<IMerchant[]>;

@Injectable({ providedIn: 'root' })
export class MerchantService {
  public resourceUrl = SERVER_API_URL + 'api/merchants';

  constructor(protected http: HttpClient) {}

  create(merchant: IMerchant): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(merchant);
    return this.http
      .post<IMerchant>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(merchant: IMerchant): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(merchant);
    return this.http
      .put<IMerchant>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMerchant>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMerchant[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(merchant: IMerchant): IMerchant {
    const copy: IMerchant = Object.assign({}, merchant, {
      createdDate: merchant.createdDate && merchant.createdDate.isValid() ? merchant.createdDate.toJSON() : undefined,
      lastModifiedDate: merchant.lastModifiedDate && merchant.lastModifiedDate.isValid() ? merchant.lastModifiedDate.toJSON() : undefined
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
      res.body.forEach((merchant: IMerchant) => {
        merchant.createdDate = merchant.createdDate ? moment(merchant.createdDate) : undefined;
        merchant.lastModifiedDate = merchant.lastModifiedDate ? moment(merchant.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
