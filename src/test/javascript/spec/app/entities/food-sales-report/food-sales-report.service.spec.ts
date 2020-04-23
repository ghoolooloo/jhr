import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FoodSalesReportService } from 'app/entities/food-sales-report/food-sales-report.service';
import { IFoodSalesReport, FoodSalesReport } from 'app/shared/model/food-sales-report.model';

describe('Service Tests', () => {
  describe('FoodSalesReport Service', () => {
    let injector: TestBed;
    let service: FoodSalesReportService;
    let httpMock: HttpTestingController;
    let elemDefault: IFoodSalesReport;
    let expectedResult: IFoodSalesReport | IFoodSalesReport[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FoodSalesReportService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FoodSalesReport(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FoodSalesReport', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            day: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            day: currentDate
          },
          returnedFromService
        );

        service.create(new FoodSalesReport()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FoodSalesReport', () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_TIME_FORMAT),
            shopSN: 'BBBBBB',
            shopName: 'BBBBBB',
            categorySN: 'BBBBBB',
            categoryName: 'BBBBBB',
            foodSN: 'BBBBBB',
            foodName: 'BBBBBB',
            cost: 1,
            price: 1,
            originalPrice: 1,
            salesQuantity: 1,
            salesAmount: 1,
            refundOrders: 1,
            refundAmount: 1,
            grossProfit: 1,
            initQuantity: 1,
            remainder: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            day: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FoodSalesReport', () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_TIME_FORMAT),
            shopSN: 'BBBBBB',
            shopName: 'BBBBBB',
            categorySN: 'BBBBBB',
            categoryName: 'BBBBBB',
            foodSN: 'BBBBBB',
            foodName: 'BBBBBB',
            cost: 1,
            price: 1,
            originalPrice: 1,
            salesQuantity: 1,
            salesAmount: 1,
            refundOrders: 1,
            refundAmount: 1,
            grossProfit: 1,
            initQuantity: 1,
            remainder: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            day: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FoodSalesReport', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
