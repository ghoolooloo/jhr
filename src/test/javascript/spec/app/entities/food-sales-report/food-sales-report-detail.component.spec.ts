import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { FoodSalesReportDetailComponent } from 'app/entities/food-sales-report/food-sales-report-detail.component';
import { FoodSalesReport } from 'app/shared/model/food-sales-report.model';

describe('Component Tests', () => {
  describe('FoodSalesReport Management Detail Component', () => {
    let comp: FoodSalesReportDetailComponent;
    let fixture: ComponentFixture<FoodSalesReportDetailComponent>;
    const route = ({ data: of({ foodSalesReport: new FoodSalesReport(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [FoodSalesReportDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FoodSalesReportDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FoodSalesReportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load foodSalesReport on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.foodSalesReport).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
