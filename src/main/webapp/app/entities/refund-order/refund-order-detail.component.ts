import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRefundOrder } from 'app/shared/model/refund-order.model';

@Component({
  selector: 'jhi-refund-order-detail',
  templateUrl: './refund-order-detail.component.html'
})
export class RefundOrderDetailComponent implements OnInit {
  refundOrder: IRefundOrder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refundOrder }) => (this.refundOrder = refundOrder));
  }

  previousState(): void {
    window.history.back();
  }
}
