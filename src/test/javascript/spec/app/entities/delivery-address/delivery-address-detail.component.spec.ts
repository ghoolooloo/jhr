import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { DeliveryAddressDetailComponent } from 'app/entities/delivery-address/delivery-address-detail.component';
import { DeliveryAddress } from 'app/shared/model/delivery-address.model';

describe('Component Tests', () => {
  describe('DeliveryAddress Management Detail Component', () => {
    let comp: DeliveryAddressDetailComponent;
    let fixture: ComponentFixture<DeliveryAddressDetailComponent>;
    const route = ({ data: of({ deliveryAddress: new DeliveryAddress(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [DeliveryAddressDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DeliveryAddressDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DeliveryAddressDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load deliveryAddress on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.deliveryAddress).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
