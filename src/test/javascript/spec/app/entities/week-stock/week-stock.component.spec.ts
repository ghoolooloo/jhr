import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhrTestModule } from '../../../test.module';
import { WeekStockComponent } from 'app/entities/week-stock/week-stock.component';
import { WeekStockService } from 'app/entities/week-stock/week-stock.service';
import { WeekStock } from 'app/shared/model/week-stock.model';

describe('Component Tests', () => {
  describe('WeekStock Management Component', () => {
    let comp: WeekStockComponent;
    let fixture: ComponentFixture<WeekStockComponent>;
    let service: WeekStockService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [WeekStockComponent]
      })
        .overrideTemplate(WeekStockComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WeekStockComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WeekStockService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WeekStock(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.weekStocks && comp.weekStocks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
