import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { SalesDetailComponent } from 'app/entities/sales/sales-detail.component';
import { Sales } from 'app/shared/model/sales.model';

describe('Component Tests', () => {
  describe('Sales Management Detail Component', () => {
    let comp: SalesDetailComponent;
    let fixture: ComponentFixture<SalesDetailComponent>;
    const route = ({ data: of({ sales: new Sales(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [SalesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SalesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SalesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sales on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sales).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
