import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExceptionOrder } from 'app/shared/model/exception-order.model';

@Component({
  selector: 'jhi-exception-order-detail',
  templateUrl: './exception-order-detail.component.html'
})
export class ExceptionOrderDetailComponent implements OnInit {
  exceptionOrder: IExceptionOrder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ exceptionOrder }) => (this.exceptionOrder = exceptionOrder));
  }

  previousState(): void {
    window.history.back();
  }
}
