import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IClosedOrder } from 'app/shared/model/closed-order.model';

@Component({
  selector: 'jhi-closed-order-detail',
  templateUrl: './closed-order-detail.component.html'
})
export class ClosedOrderDetailComponent implements OnInit {
  closedOrder: IClosedOrder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ closedOrder }) => (this.closedOrder = closedOrder));
  }

  previousState(): void {
    window.history.back();
  }
}
