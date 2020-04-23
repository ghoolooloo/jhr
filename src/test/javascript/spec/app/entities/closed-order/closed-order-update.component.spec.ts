import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { ClosedOrderUpdateComponent } from 'app/entities/closed-order/closed-order-update.component';
import { ClosedOrderService } from 'app/entities/closed-order/closed-order.service';
import { ClosedOrder } from 'app/shared/model/closed-order.model';

describe('Component Tests', () => {
  describe('ClosedOrder Management Update Component', () => {
    let comp: ClosedOrderUpdateComponent;
    let fixture: ComponentFixture<ClosedOrderUpdateComponent>;
    let service: ClosedOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [ClosedOrderUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ClosedOrderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ClosedOrderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ClosedOrderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ClosedOrder(123);
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
        const entity = new ClosedOrder();
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
