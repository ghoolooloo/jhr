import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { FoodSalesReportUpdateComponent } from 'app/entities/food-sales-report/food-sales-report-update.component';
import { FoodSalesReportService } from 'app/entities/food-sales-report/food-sales-report.service';
import { FoodSalesReport } from 'app/shared/model/food-sales-report.model';

describe('Component Tests', () => {
  describe('FoodSalesReport Management Update Component', () => {
    let comp: FoodSalesReportUpdateComponent;
    let fixture: ComponentFixture<FoodSalesReportUpdateComponent>;
    let service: FoodSalesReportService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [FoodSalesReportUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FoodSalesReportUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FoodSalesReportUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FoodSalesReportService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FoodSalesReport(123);
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
        const entity = new FoodSalesReport();
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
