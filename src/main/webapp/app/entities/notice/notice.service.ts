import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INotice } from 'app/shared/model/notice.model';

type EntityResponseType = HttpResponse<INotice>;
type EntityArrayResponseType = HttpResponse<INotice[]>;

@Injectable({ providedIn: 'root' })
export class NoticeService {
  public resourceUrl = SERVER_API_URL + 'api/notices';

  constructor(protected http: HttpClient) {}

  create(notice: INotice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(notice);
    return this.http
      .post<INotice>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(notice: INotice): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(notice);
    return this.http
      .put<INotice>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<INotice>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<INotice[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(notice: INotice): INotice {
    const copy: INotice = Object.assign({}, notice, {
      createdDate: notice.createdDate && notice.createdDate.isValid() ? notice.createdDate.toJSON() : undefined,
      lastModifiedDate: notice.lastModifiedDate && notice.lastModifiedDate.isValid() ? notice.lastModifiedDate.toJSON() : undefined
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
      res.body.forEach((notice: INotice) => {
        notice.createdDate = notice.createdDate ? moment(notice.createdDate) : undefined;
        notice.lastModifiedDate = notice.lastModifiedDate ? moment(notice.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
