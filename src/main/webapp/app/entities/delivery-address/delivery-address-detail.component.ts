import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeliveryAddress } from 'app/shared/model/delivery-address.model';

@Component({
  selector: 'jhi-delivery-address-detail',
  templateUrl: './delivery-address-detail.component.html'
})
export class DeliveryAddressDetailComponent implements OnInit {
  deliveryAddress: IDeliveryAddress | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deliveryAddress }) => (this.deliveryAddress = deliveryAddress));
  }

  previousState(): void {
    window.history.back();
  }
}
