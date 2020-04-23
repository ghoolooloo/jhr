import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { DeliveryOrderService } from 'app/entities/delivery-order/delivery-order.service';
import { IDeliveryOrder, DeliveryOrder } from 'app/shared/model/delivery-order.model';
import { OrderType } from 'app/shared/model/enumerations/order-type.model';
import { DistributionPlatform } from 'app/shared/model/enumerations/distribution-platform.model';
import { Sex } from 'app/shared/model/enumerations/sex.model';
import { OffsetType } from 'app/shared/model/enumerations/offset-type.model';
import { PaymentMethod } from 'app/shared/model/enumerations/payment-method.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';

describe('Service Tests', () => {
  describe('DeliveryOrder Service', () => {
    let injector: TestBed;
    let service: DeliveryOrderService;
    let httpMock: HttpTestingController;
    let elemDefault: IDeliveryOrder;
    let expectedResult: IDeliveryOrder | IDeliveryOrder[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DeliveryOrderService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new DeliveryOrder(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        OrderType.TOGO,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0,
        0,
        DistributionPlatform.MERCHANT,
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        Sex.UNKNOWN,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        OffsetType.MARS,
        0,
        0,
        'AAAAAAA',
        0,
        PaymentMethod.WX,
        currentDate,
        'AAAAAAA',
        OrderStatus.NEW,
        currentDate,
        currentDate,
        currentDate,
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            diningDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            paidDate: currentDate.format(DATE_TIME_FORMAT),
            completedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DeliveryOrder', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            diningDate: currentDate.format(DATE_TIME_FORMAT),
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            paidDate: currentDate.format(DATE_TIME_FORMAT),
            completedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            diningDate: currentDate,
            createdDate: currentDate,
            paidDate: currentDate,
            completedDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.create(new DeliveryOrder()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DeliveryOrder', () => {
        const returnedFromService = Object.assign(
          {
            sn: 'BBBBBB',
            memberName: 'BBBBBB',
            memberSN: 'BBBBBB',
            orderType: 'BBBBBB',
            shopSN: 'BBBBBB',
            shopName: 'BBBBBB',
            priceTotal: 1,
            cardReduce: 1,
            cards: 'BBBBBB',
            rewardPointsReduce: 1,
            paymentTotal: 1,
            distributionPlatform: 'BBBBBB',
            deliveryNo: 'BBBBBB',
            deliveryStatus: 1,
            deliveryDesc: 'BBBBBB',
            deliverier: 'BBBBBB',
            deliverierMobile: 'BBBBBB',
            deliveryDeductFee: 1,
            deliveryFee: 1,
            contact: 'BBBBBB',
            sex: 'BBBBBB',
            mobile: 'BBBBBB',
            country: 'BBBBBB',
            province: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            offsetType: 'BBBBBB',
            longitude: 1,
            latitude: 1,
            address: 'BBBBBB',
            packingFee: 1,
            paymentMode: 'BBBBBB',
            diningDate: currentDate.format(DATE_TIME_FORMAT),
            remark: 'BBBBBB',
            status: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            paidDate: currentDate.format(DATE_TIME_FORMAT),
            completedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            diningDate: currentDate,
            createdDate: currentDate,
            paidDate: currentDate,
            completedDate: currentDate,
            lastModifiedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DeliveryOrder', () => {
        const returnedFromService = Object.assign(
          {
            sn: 'BBBBBB',
            memberName: 'BBBBBB',
            memberSN: 'BBBBBB',
            orderType: 'BBBBBB',
            shopSN: 'BBBBBB',
            shopName: 'BBBBBB',
            priceTotal: 1,
            cardReduce: 1,
            cards: 'BBBBBB',
            rewardPointsReduce: 1,
            paymentTotal: 1,
            distributionPlatform: 'BBBBBB',
            deliveryNo: 'BBBBBB',
            deliveryStatus: 1,
            deliveryDesc: 'BBBBBB',
            deliverier: 'BBBBBB',
            deliverierMobile: 'BBBBBB',
            deliveryDeductFee: 1,
            deliveryFee: 1,
            contact: 'BBBBBB',
            sex: 'BBBBBB',
            mobile: 'BBBBBB',
            country: 'BBBBBB',
            province: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            offsetType: 'BBBBBB',
            longitude: 1,
            latitude: 1,
            address: 'BBBBBB',
            packingFee: 1,
            paymentMode: 'BBBBBB',
            diningDate: currentDate.format(DATE_TIME_FORMAT),
            remark: 'BBBBBB',
            status: 'BBBBBB',
            createdDate: currentDate.format(DATE_TIME_FORMAT),
            paidDate: currentDate.format(DATE_TIME_FORMAT),
            completedDate: currentDate.format(DATE_TIME_FORMAT),
            lastModifiedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            diningDate: currentDate,
            createdDate: currentDate,
            paidDate: currentDate,
            completedDate: currentDate,
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

      it('should delete a DeliveryOrder', () => {
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
