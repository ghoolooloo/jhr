import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { ExceptionOrderUpdateComponent } from 'app/entities/exception-order/exception-order-update.component';
import { ExceptionOrderService } from 'app/entities/exception-order/exception-order.service';
import { ExceptionOrder } from 'app/shared/model/exception-order.model';

describe('Component Tests', () => {
  describe('ExceptionOrder Management Update Component', () => {
    let comp: ExceptionOrderUpdateComponent;
    let fixture: ComponentFixture<ExceptionOrderUpdateComponent>;
    let service: ExceptionOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [ExceptionOrderUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ExceptionOrderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExceptionOrderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExceptionOrderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ExceptionOrder(123);
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
        const entity = new ExceptionOrder();
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
