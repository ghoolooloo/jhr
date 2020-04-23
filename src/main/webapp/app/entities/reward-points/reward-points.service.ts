import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRewardPoints } from 'app/shared/model/reward-points.model';

type EntityResponseType = HttpResponse<IRewardPoints>;
type EntityArrayResponseType = HttpResponse<IRewardPoints[]>;

@Injectable({ providedIn: 'root' })
export class RewardPointsService {
  public resourceUrl = SERVER_API_URL + 'api/reward-points';

  constructor(protected http: HttpClient) {}

  create(rewardPoints: IRewardPoints): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rewardPoints);
    return this.http
      .post<IRewardPoints>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(rewardPoints: IRewardPoints): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rewardPoints);
    return this.http
      .put<IRewardPoints>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRewardPoints>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRewardPoints[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(rewardPoints: IRewardPoints): IRewardPoints {
    const copy: IRewardPoints = Object.assign({}, rewardPoints, {
      lastModifiedDate:
        rewardPoints.lastModifiedDate && rewardPoints.lastModifiedDate.isValid() ? rewardPoints.lastModifiedDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.lastModifiedDate = res.body.lastModifiedDate ? moment(res.body.lastModifiedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((rewardPoints: IRewardPoints) => {
        rewardPoints.lastModifiedDate = rewardPoints.lastModifiedDate ? moment(rewardPoints.lastModifiedDate) : undefined;
      });
    }
    return res;
  }
}
