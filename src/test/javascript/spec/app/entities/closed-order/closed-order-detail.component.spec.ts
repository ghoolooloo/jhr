import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { ClosedOrderDetailComponent } from 'app/entities/closed-order/closed-order-detail.component';
import { ClosedOrder } from 'app/shared/model/closed-order.model';

describe('Component Tests', () => {
  describe('ClosedOrder Management Detail Component', () => {
    let comp: ClosedOrderDetailComponent;
    let fixture: ComponentFixture<ClosedOrderDetailComponent>;
    const route = ({ data: of({ closedOrder: new ClosedOrder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [ClosedOrderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ClosedOrderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ClosedOrderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load closedOrder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.closedOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
