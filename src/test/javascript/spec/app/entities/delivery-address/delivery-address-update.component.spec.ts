import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { DeliveryAddressUpdateComponent } from 'app/entities/delivery-address/delivery-address-update.component';
import { DeliveryAddressService } from 'app/entities/delivery-address/delivery-address.service';
import { DeliveryAddress } from 'app/shared/model/delivery-address.model';

describe('Component Tests', () => {
  describe('DeliveryAddress Management Update Component', () => {
    let comp: DeliveryAddressUpdateComponent;
    let fixture: ComponentFixture<DeliveryAddressUpdateComponent>;
    let service: DeliveryAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [DeliveryAddressUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(DeliveryAddressUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DeliveryAddressUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DeliveryAddressService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DeliveryAddress(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new DeliveryAddress();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
