import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExceptionOrder } from 'app/shared/model/exception-order.model';
import { ExceptionOrderService } from './exception-order.service';

@Component({
  templateUrl: './exception-order-delete-dialog.component.html'
})
export class ExceptionOrderDeleteDialogComponent {
  exceptionOrder?: IExceptionOrder;

  constructor(
    protected exceptionOrderService: ExceptionOrderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.exceptionOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('exceptionOrderListModification');
      this.activeModal.close();
    });
  }
}
