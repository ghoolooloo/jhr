import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDeliveryAddress } from 'app/shared/model/delivery-address.model';

type EntityResponseType = HttpResponse<IDeliveryAddress>;
type EntityArrayResponseType = HttpResponse<IDeliveryAddress[]>;

@Injectable({ providedIn: 'root' })
export class DeliveryAddressService {
  public resourceUrl = SERVER_API_URL + 'api/delivery-addresses';

  constructor(protected http: HttpClient) {}

  create(deliveryAddress: IDeliveryAddress): Observable<EntityResponseType> {
    return this.http.post<IDeliveryAddress>(this.resourceUrl, deliveryAddress, { observe: 'response' });
  }

  update(deliveryAddress: IDeliveryAddress): Observable<EntityResponseType> {
    return this.http.put<IDeliveryAddress>(this.resourceUrl, deliveryAddress, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDeliveryAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDeliveryAddress[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
