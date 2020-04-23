import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFoodSalesReport } from 'app/shared/model/food-sales-report.model';

type EntityResponseType = HttpResponse<IFoodSalesReport>;
type EntityArrayResponseType = HttpResponse<IFoodSalesReport[]>;

@Injectable({ providedIn: 'root' })
export class FoodSalesReportService {
  public resourceUrl = SERVER_API_URL + 'api/food-sales-reports';

  constructor(protected http: HttpClient) {}

  create(foodSalesReport: IFoodSalesReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(foodSalesReport);
    return this.http
      .post<IFoodSalesReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(foodSalesReport: IFoodSalesReport): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(foodSalesReport);
    return this.http
      .put<IFoodSalesReport>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFoodSalesReport>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFoodSalesReport[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(foodSalesReport: IFoodSalesReport): IFoodSalesReport {
    const copy: IFoodSalesReport = Object.assign({}, foodSalesReport, {
      day: foodSalesReport.day && foodSalesReport.day.isValid() ? foodSalesReport.day.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.day = res.body.day ? moment(res.body.day) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((foodSalesReport: IFoodSalesReport) => {
        foodSalesReport.day = foodSalesReport.day ? moment(foodSalesReport.day) : undefined;
      });
    }
    return res;
  }
}
