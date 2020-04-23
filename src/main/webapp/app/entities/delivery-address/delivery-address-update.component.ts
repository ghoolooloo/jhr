import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDeliveryAddress, DeliveryAddress } from 'app/shared/model/delivery-address.model';
import { DeliveryAddressService } from './delivery-address.service';

@Component({
  selector: 'jhi-delivery-address-update',
  templateUrl: './delivery-address-update.component.html'
})
export class DeliveryAddressUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    memberName: [null, [Validators.required, Validators.maxLength(20)]],
    memberSN: [null, [Validators.required, Validators.maxLength(20)]],
    contact: [null, [Validators.required, Validators.maxLength(20)]],
    sex: [],
    mobile: [null, [Validators.maxLength(11)]],
    country: [null, [Validators.maxLength(20)]],
    province: [null, [Validators.required, Validators.maxLength(20)]],
    city: [null, [Validators.required, Validators.maxLength(20)]],
    district: [null, [Validators.maxLength(20)]],
    offsetType: [null, [Validators.required]],
    longitude: [null, [Validators.required]],
    latitude: [null, [Validators.required]],
    address: [null, [Validators.maxLength(50)]]
  });

  constructor(
    protected deliveryAddressService: DeliveryAddressService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deliveryAddress }) => {
      this.updateForm(deliveryAddress);
    });
  }

  updateForm(deliveryAddress: IDeliveryAddress): void {
    this.editForm.patchValue({
      id: deliveryAddress.id,
      memberName: deliveryAddress.memberName,
      memberSN: deliveryAddress.memberSN,
      contact: deliveryAddress.contact,
      sex: deliveryAddress.sex,
      mobile: deliveryAddress.mobile,
      country: deliveryAddress.country,
      province: deliveryAddress.province,
      city: deliveryAddress.city,
      district: deliveryAddress.district,
      offsetType: deliveryAddress.offsetType,
      longitude: deliveryAddress.longitude,
      latitude: deliveryAddress.latitude,
      address: deliveryAddress.address
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deliveryAddress = this.createFromForm();
    if (deliveryAddress.id !== undefined) {
      this.subscribeToSaveResponse(this.deliveryAddressService.update(deliveryAddress));
    } else {
      this.subscribeToSaveResponse(this.deliveryAddressService.create(deliveryAddress));
    }
  }

  private createFromForm(): IDeliveryAddress {
    return {
      ...new DeliveryAddress(),
      id: this.editForm.get(['id'])!.value,
      memberName: this.editForm.get(['memberName'])!.value,
      memberSN: this.editForm.get(['memberSN'])!.value,
      contact: this.editForm.get(['contact'])!.value,
      sex: this.editForm.get(['sex'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
      country: this.editForm.get(['country'])!.value,
      province: this.editForm.get(['province'])!.value,
      city: this.editForm.get(['city'])!.value,
      district: this.editForm.get(['district'])!.value,
      offsetType: this.editForm.get(['offsetType'])!.value,
      longitude: this.editForm.get(['longitude'])!.value,
      latitude: this.editForm.get(['latitude'])!.value,
      address: this.editForm.get(['address'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeliveryAddress>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
