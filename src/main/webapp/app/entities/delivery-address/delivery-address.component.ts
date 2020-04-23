import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDeliveryAddress } from 'app/shared/model/delivery-address.model';
import { DeliveryAddressService } from './delivery-address.service';
import { DeliveryAddressDeleteDialogComponent } from './delivery-address-delete-dialog.component';

@Component({
  selector: 'jhi-delivery-address',
  templateUrl: './delivery-address.component.html'
})
export class DeliveryAddressComponent implements OnInit, OnDestroy {
  deliveryAddresses?: IDeliveryAddress[];
  eventSubscriber?: Subscription;

  constructor(
    protected deliveryAddressService: DeliveryAddressService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.deliveryAddressService.query().subscribe((res: HttpResponse<IDeliveryAddress[]>) => (this.deliveryAddresses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDeliveryAddresses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDeliveryAddress): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDeliveryAddresses(): void {
    this.eventSubscriber = this.eventManager.subscribe('deliveryAddressListModification', () => this.loadAll());
  }

  delete(deliveryAddress: IDeliveryAddress): void {
    const modalRef = this.modalService.open(DeliveryAddressDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.deliveryAddress = deliveryAddress;
  }
}
