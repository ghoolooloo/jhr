import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IClosedOrder } from 'app/shared/model/closed-order.model';
import { ClosedOrderService } from './closed-order.service';

@Component({
  templateUrl: './closed-order-delete-dialog.component.html'
})
export class ClosedOrderDeleteDialogComponent {
  closedOrder?: IClosedOrder;

  constructor(
    protected closedOrderService: ClosedOrderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.closedOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('closedOrderListModification');
      this.activeModal.close();
    });
  }
}
