import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SalesService } from 'app/entities/sales/sales.service';
import { ISales, Sales } from 'app/shared/model/sales.model';

describe('Service Tests', () => {
  describe('Sales Service', () => {
    let injector: TestBed;
    let service: SalesService;
    let httpMock: HttpTestingController;
    let elemDefault: ISales;
    let expectedResult: ISales | ISales[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SalesService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Sales(0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Sales', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            day: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            day: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.create(new Sales()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Sales', () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_TIME_FORMAT),
            shopSN: 'BBBBBB',
            shopName: 'BBBBBB',
            categorySN: 'BBBBBB',
            categoryName: 'BBBBBB',
            foodSN: 'BBBBBB',
            foodName: 'BBBBBB',
            foodOriginalPrice: 1,
            initQuantity: 1,
            salesQuantity: 1,
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            day: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Sales', () => {
        const returnedFromService = Object.assign(
          {
            day: currentDate.format(DATE_TIME_FORMAT),
            shopSN: 'BBBBBB',
            shopName: 'BBBBBB',
            categorySN: 'BBBBBB',
            categoryName: 'BBBBBB',
            foodSN: 'BBBBBB',
            foodName: 'BBBBBB',
            foodOriginalPrice: 1,
            initQuantity: 1,
            salesQuantity: 1,
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            day: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Sales', () => {
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
