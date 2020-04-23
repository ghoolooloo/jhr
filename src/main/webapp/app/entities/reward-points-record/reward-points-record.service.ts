import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRewardPointsRecord } from 'app/shared/model/reward-points-record.model';

type EntityResponseType = HttpResponse<IRewardPointsRecord>;
type EntityArrayResponseType = HttpResponse<IRewardPointsRecord[]>;

@Injectable({ providedIn: 'root' })
export class RewardPointsRecordService {
  public resourceUrl = SERVER_API_URL + 'api/reward-points-records';

  constructor(protected http: HttpClient) {}

  create(rewardPointsRecord: IRewardPointsRecord): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rewardPointsRecord);
    return this.http
      .post<IRewardPointsRecord>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rewardPointsRecord: IRewardPointsRecord): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rewardPointsRecord);
    return this.http
      .put<IRewardPointsRecord>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRewardPointsRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRewardPointsRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(rewardPointsRecord: IRewardPointsRecord): IRewardPointsRecord {
    const copy: IRewardPointsRecord = Object.assign({}, rewardPointsRecord, {
      createdDate:
        rewardPointsRecord.createdDate && rewardPointsRecord.createdDate.isValid() ? rewardPointsRecord.createdDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((rewardPointsRecord: IRewardPointsRecord) => {
        rewardPointsRecord.createdDate = rewardPointsRecord.createdDate ? moment(rewardPointsRecord.createdDate) : undefined;
      });
    }
    return res;
  }
}
