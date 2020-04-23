import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeliveryAddress } from 'app/shared/model/delivery-address.model';
import { DeliveryAddressService } from './delivery-address.service';

@Component({
  templateUrl: './delivery-address-delete-dialog.component.html'
})
export class DeliveryAddressDeleteDialogComponent {
  deliveryAddress?: IDeliveryAddress;

  constructor(
    protected deliveryAddressService: DeliveryAddressService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.deliveryAddressService.delete(id).subscribe(() => {
      this.eventManager.broadcast('deliveryAddressListModification');
      this.activeModal.close();
    });
  }
}
