import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DeliveryAddressService } from 'app/entities/delivery-address/delivery-address.service';
import { IDeliveryAddress, DeliveryAddress } from 'app/shared/model/delivery-address.model';
import { Sex } from 'app/shared/model/enumerations/sex.model';
import { OffsetType } from 'app/shared/model/enumerations/offset-type.model';

describe('Service Tests', () => {
  describe('DeliveryAddress Service', () => {
    let injector: TestBed;
    let service: DeliveryAddressService;
    let httpMock: HttpTestingController;
    let elemDefault: IDeliveryAddress;
    let expectedResult: IDeliveryAddress | IDeliveryAddress[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DeliveryAddressService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new DeliveryAddress(
        0,
        'AAAAAAA',
        'AAAAAAA',
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
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a DeliveryAddress', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new DeliveryAddress()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a DeliveryAddress', () => {
        const returnedFromService = Object.assign(
          {
            memberName: 'BBBBBB',
            memberSN: 'BBBBBB',
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
            address: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of DeliveryAddress', () => {
        const returnedFromService = Object.assign(
          {
            memberName: 'BBBBBB',
            memberSN: 'BBBBBB',
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
            address: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a DeliveryAddress', () => {
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
