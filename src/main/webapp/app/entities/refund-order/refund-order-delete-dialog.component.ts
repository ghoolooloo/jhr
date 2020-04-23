import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefundOrder } from 'app/shared/model/refund-order.model';
import { RefundOrderService } from './refund-order.service';

@Component({
  templateUrl: './refund-order-delete-dialog.component.html'
})
export class RefundOrderDeleteDialogComponent {
  refundOrder?: IRefundOrder;

  constructor(
    protected refundOrderService: RefundOrderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.refundOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('refundOrderListModification');
      this.activeModal.close();
    });
  }
}
