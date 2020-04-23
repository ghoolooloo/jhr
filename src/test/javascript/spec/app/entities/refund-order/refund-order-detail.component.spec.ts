import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { RefundOrderDetailComponent } from 'app/entities/refund-order/refund-order-detail.component';
import { RefundOrder } from 'app/shared/model/refund-order.model';

describe('Component Tests', () => {
  describe('RefundOrder Management Detail Component', () => {
    let comp: RefundOrderDetailComponent;
    let fixture: ComponentFixture<RefundOrderDetailComponent>;
    const route = ({ data: of({ refundOrder: new RefundOrder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [RefundOrderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RefundOrderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RefundOrderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load refundOrder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.refundOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
