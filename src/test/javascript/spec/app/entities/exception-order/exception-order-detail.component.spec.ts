import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { ExceptionOrderDetailComponent } from 'app/entities/exception-order/exception-order-detail.component';
import { ExceptionOrder } from 'app/shared/model/exception-order.model';

describe('Component Tests', () => {
  describe('ExceptionOrder Management Detail Component', () => {
    let comp: ExceptionOrderDetailComponent;
    let fixture: ComponentFixture<ExceptionOrderDetailComponent>;
    const route = ({ data: of({ exceptionOrder: new ExceptionOrder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [ExceptionOrderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ExceptionOrderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExceptionOrderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load exceptionOrder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.exceptionOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
