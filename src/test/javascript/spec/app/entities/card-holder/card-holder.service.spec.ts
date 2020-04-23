import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { CardHolderService } from 'app/entities/card-holder/card-holder.service';
import { ICardHolder, CardHolder } from 'app/shared/model/card-holder.model';
import { CardType } from 'app/shared/model/enumerations/card-type.model';

describe('Service Tests', () => {
  describe('CardHolder Service', () => {
    let injector: TestBed;
    let service: CardHolderService;
    let httpMock: HttpTestingController;
    let elemDefault: ICardHolder;
    let expectedResult: ICardHolder | ICardHolder[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CardHolderService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CardHolder(
        0,
        'AAAAAAA',
        'AAAAAAA',
        CardType.CASH,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        false,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        0,
        'AAAAAAA',
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
            receivedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CardHolder', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            validPeriodBeginAt: currentDate.format(DATE_TIME_FORMAT),
            validPeriodEndAt: currentDate.format(DATE_TIME_FORMAT),
            receivedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validPeriodBeginAt: currentDate,
            validPeriodEndAt: currentDate,
            receivedDate: currentDate
          },
          returnedFromService
        );

        service.create(new CardHolder()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CardHolder', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            cardSN: 'BBBBBB',
            cardType: 'BBBBBB',
            title: 'BBBBBB',
            thumbnail: 'BBBBBB',
            details: 'BBBBBB',
            validPeriodBeginAt: currentDate.format(DATE_TIME_FORMAT),
            validPeriodEndAt: currentDate.format(DATE_TIME_FORMAT),
            canUseWithOtherCard: true,
            acceptCategories: 'BBBBBB',
            acceptShops: 'BBBBBB',
            leastCost: 1,
            reduceCost: 1,
            discount: 1,
            gift: 'BBBBBB',
            giftQuantity: 1,
            receivedBy: 'BBBBBB',
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            held: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validPeriodBeginAt: currentDate,
            validPeriodEndAt: currentDate,
            receivedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CardHolder', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            cardSN: 'BBBBBB',
            cardType: 'BBBBBB',
            title: 'BBBBBB',
            thumbnail: 'BBBBBB',
            details: 'BBBBBB',
            validPeriodBeginAt: currentDate.format(DATE_TIME_FORMAT),
            validPeriodEndAt: currentDate.format(DATE_TIME_FORMAT),
            canUseWithOtherCard: true,
            acceptCategories: 'BBBBBB',
            acceptShops: 'BBBBBB',
            leastCost: 1,
            reduceCost: 1,
            discount: 1,
            gift: 'BBBBBB',
            giftQuantity: 1,
            receivedBy: 'BBBBBB',
            receivedDate: currentDate.format(DATE_TIME_FORMAT),
            held: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            validPeriodBeginAt: currentDate,
            validPeriodEndAt: currentDate,
            receivedDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CardHolder', () => {
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
