import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { RefundOrderUpdateComponent } from 'app/entities/refund-order/refund-order-update.component';
import { RefundOrderService } from 'app/entities/refund-order/refund-order.service';
import { RefundOrder } from 'app/shared/model/refund-order.model';

describe('Component Tests', () => {
  describe('RefundOrder Management Update Component', () => {
    let comp: RefundOrderUpdateComponent;
    let fixture: ComponentFixture<RefundOrderUpdateComponent>;
    let service: RefundOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [RefundOrderUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RefundOrderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RefundOrderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RefundOrderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RefundOrder(123);
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
        const entity = new RefundOrder();
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
