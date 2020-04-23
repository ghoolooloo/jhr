import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeliveryOrder } from 'app/shared/model/delivery-order.model';
import { DeliveryOrderService } from './delivery-order.service';

@Component({
  templateUrl: './delivery-order-delete-dialog.component.html'
})
export class DeliveryOrderDeleteDialogComponent {
  deliveryOrder?: IDeliveryOrder;

  constructor(
    protected deliveryOrderService: DeliveryOrderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.deliveryOrderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('deliveryOrderListModification');
      this.activeModal.close();
    });
  }
}
