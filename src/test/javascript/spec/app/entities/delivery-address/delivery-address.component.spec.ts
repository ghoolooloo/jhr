import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhrTestModule } from '../../../test.module';
import { DeliveryAddressComponent } from 'app/entities/delivery-address/delivery-address.component';
import { DeliveryAddressService } from 'app/entities/delivery-address/delivery-address.service';
import { DeliveryAddress } from 'app/shared/model/delivery-address.model';

describe('Component Tests', () => {
  describe('DeliveryAddress Management Component', () => {
    let comp: DeliveryAddressComponent;
    let fixture: ComponentFixture<DeliveryAddressComponent>;
    let service: DeliveryAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [DeliveryAddressComponent]
      })
        .overrideTemplate(DeliveryAddressComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DeliveryAddressComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DeliveryAddressService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DeliveryAddress(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.deliveryAddresses && comp.deliveryAddresses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
