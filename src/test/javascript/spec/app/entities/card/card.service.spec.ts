import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CardService } from 'app/entities/card/card.service';
import { ICard, Card } from 'app/shared/model/card.model';
import { CardType } from 'app/shared/model/enumerations/card-type.model';
import { CardStatus } from 'app/shared/model/enumerations/card-status.model';

describe('Service Tests', () => {
  describe('Card Service', () => {
    let injector: TestBed;
    let service: CardService;
    let httpMock: HttpTestingController;
    let elemDefault: ICard;
    let expectedResult: ICard | ICard[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CardService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Card(
        0,
        'AAAAAAA',
        CardType.CASH,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        0,
        0,
        false,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        0,
        CardStatus.NEW,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            validPeriodBeginAt: currentDate.format(DATE_TIME_FORMAT),
            validPeriodEndAt: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            deliveriedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Card', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            validPeriodBeginAt: currentDate.format(DATE_TIME_FORMAT),
            validPeriodEndAt: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            deliveriedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validPeriodBeginAt: currentDate,
            validPeriodEndAt: currentDate,
            createdDate: currentDate,
            deliveriedDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.create(new Card()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Card', () => {
        const returnedFromService = Object.assign(
          {
            sn: 'BBBBBB',
            cardType: 'BBBBBB',
            title: 'BBBBBB',
            thumbnail: 'BBBBBB',
            details: 'BBBBBB',
            validPeriodBeginAt: currentDate.format(DATE_TIME_FORMAT),
            validPeriodEndAt: currentDate.format(DATE_TIME_FORMAT),
            quantity: 1,
            receivedQuantity: 1,
            canUseWithOtherCard: true,
            acceptCategories: 'BBBBBB',
            acceptShops: 'BBBBBB',
            leastCost: 1,
            reduceCost: 1,
            discount: 1,
            gift: 'BBBBBB',
            giftQuantity: 1,
            status: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            deliveriedBy: 'BBBBBB',
            deliveriedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validPeriodBeginAt: currentDate,
            validPeriodEndAt: currentDate,
            createdDate: currentDate,
            deliveriedDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Card', () => {
        const returnedFromService = Object.assign(
          {
            sn: 'BBBBBB',
            cardType: 'BBBBBB',
            title: 'BBBBBB',
            thumbnail: 'BBBBBB',
            details: 'BBBBBB',
            validPeriodBeginAt: currentDate.format(DATE_TIME_FORMAT),
            validPeriodEndAt: currentDate.format(DATE_TIME_FORMAT),
            quantity: 1,
            receivedQuantity: 1,
            canUseWithOtherCard: true,
            acceptCategories: 'BBBBBB',
            acceptShops: 'BBBBBB',
            leastCost: 1,
            reduceCost: 1,
            discount: 1,
            gift: 'BBBBBB',
            giftQuantity: 1,
            status: 'BBBBBB',
            createdBy: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            deliveriedBy: 'BBBBBB',
            deliveriedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedBy: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validPeriodBeginAt: currentDate,
            validPeriodEndAt: currentDate,
            createdDate: currentDate,
            deliveriedDate: currentDate,
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

      it('should delete a Card', () => {
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
