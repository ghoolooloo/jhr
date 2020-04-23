import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { SalesUpdateComponent } from 'app/entities/sales/sales-update.component';
import { SalesService } from 'app/entities/sales/sales.service';
import { Sales } from 'app/shared/model/sales.model';

describe('Component Tests', () => {
  describe('Sales Management Update Component', () => {
    let comp: SalesUpdateComponent;
    let fixture: ComponentFixture<SalesUpdateComponent>;
    let service: SalesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [SalesUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SalesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SalesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SalesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Sales(123);
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
        const entity = new Sales();
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
