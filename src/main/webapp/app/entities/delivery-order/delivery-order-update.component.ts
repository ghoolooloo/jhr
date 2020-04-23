import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDeliveryOrder, DeliveryOrder } from 'app/shared/model/delivery-order.model';
import { DeliveryOrderService } from './delivery-order.service';

@Component({
  selector: 'jhi-delivery-order-update',
  templateUrl: './delivery-order-update.component.html'
})
export class DeliveryOrderUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    sn: [null, [Validators.required, Validators.maxLength(20)]],
    memberName: [null, [Validators.required, Validators.maxLength(20)]],
    memberSN: [null, [Validators.required, Validators.maxLength(20)]],
    orderType: [null, [Validators.required]],
    shopSN: [null, [Validators.required, Validators.maxLength(30)]],
    shopName: [null, [Validators.required, Validators.maxLength(20)]],
    priceTotal: [null, [Validators.required, Validators.min(0)]],
    cardReduce: [],
    cards: [null, [Validators.maxLength(300)]],
    rewardPointsReduce: [],
    paymentTotal: [null, [Validators.required, Validators.min(0)]],
    distributionPlatform: [],
    deliveryNo: [null, [Validators.maxLength(32)]],
    deliveryStatus: [],
    deliveryDesc: [null, [Validators.maxLength(50)]],
    deliverier: [null, [Validators.maxLength(20)]],
    deliverierMobile: [null, [Validators.maxLength(11)]],
    deliveryDeductFee: [],
    deliveryFee: [],
    contact: [null, [Validators.maxLength(20)]],
    sex: [],
    mobile: [null, [Validators.maxLength(11)]],
    country: [null, [Validators.maxLength(20)]],
    province: [null, [Validators.maxLength(20)]],
    city: [null, [Validators.maxLength(20)]],
    district: [null, [Validators.maxLength(20)]],
    offsetType: [],
    longitude: [],
    latitude: [],
    address: [null, [Validators.maxLength(50)]],
    packingFee: [],
    paymentMode: [null, [Validators.required]],
    diningDate: [null, [Validators.required]],
    remark: [null, [Validators.maxLength(30)]],
    status: [null, [Validators.required]],
    createdDate: [null, [Validators.required]],
    paidDate: [],
    completedDate: [],
    lastModifiedDate: []
  });

  constructor(protected deliveryOrderService: DeliveryOrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deliveryOrder }) => {
      if (!deliveryOrder.id) {
        const today = moment().startOf('day');
        deliveryOrder.diningDate = today;
        deliveryOrder.createdDate = today;
        deliveryOrder.paidDate = today;
        deliveryOrder.completedDate = today;
        deliveryOrder.lastModifiedDate = today;
      }

      this.updateForm(deliveryOrder);
    });
  }

  updateForm(deliveryOrder: IDeliveryOrder): void {
    this.editForm.patchValue({
      id: deliveryOrder.id,
      sn: deliveryOrder.sn,
      memberName: deliveryOrder.memberName,
      memberSN: deliveryOrder.memberSN,
      orderType: deliveryOrder.orderType,
      shopSN: deliveryOrder.shopSN,
      shopName: deliveryOrder.shopName,
      priceTotal: deliveryOrder.priceTotal,
      cardReduce: deliveryOrder.cardReduce,
      cards: deliveryOrder.cards,
      rewardPointsReduce: deliveryOrder.rewardPointsReduce,
      paymentTotal: deliveryOrder.paymentTotal,
      distributionPlatform: deliveryOrder.distributionPlatform,
      deliveryNo: deliveryOrder.deliveryNo,
      deliveryStatus: deliveryOrder.deliveryStatus,
      deliveryDesc: deliveryOrder.deliveryDesc,
      deliverier: deliveryOrder.deliverier,
      deliverierMobile: deliveryOrder.deliverierMobile,
      deliveryDeductFee: deliveryOrder.deliveryDeductFee,
      deliveryFee: deliveryOrder.deliveryFee,
      contact: deliveryOrder.contact,
      sex: deliveryOrder.sex,
      mobile: deliveryOrder.mobile,
      country: deliveryOrder.country,
      province: deliveryOrder.province,
      city: deliveryOrder.city,
      district: deliveryOrder.district,
      offsetType: deliveryOrder.offsetType,
      longitude: deliveryOrder.longitude,
      latitude: deliveryOrder.latitude,
      address: deliveryOrder.address,
      packingFee: deliveryOrder.packingFee,
      paymentMode: deliveryOrder.paymentMode,
      diningDate: deliveryOrder.diningDate ? deliveryOrder.diningDate.format(DATE_TIME_FORMAT) : null,
      remark: deliveryOrder.remark,
      status: deliveryOrder.status,
      createdDate: deliveryOrder.createdDate ? deliveryOrder.createdDate.format(DATE_TIME_FORMAT) : null,
      paidDate: deliveryOrder.paidDate ? deliveryOrder.paidDate.format(DATE_TIME_FORMAT) : null,
      completedDate: deliveryOrder.completedDate ? deliveryOrder.completedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: deliveryOrder.lastModifiedDate ? deliveryOrder.lastModifiedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deliveryOrder = this.createFromForm();
    if (deliveryOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.deliveryOrderService.update(deliveryOrder));
    } else {
      this.subscribeToSaveResponse(this.deliveryOrderService.create(deliveryOrder));
    }
  }

  private createFromForm(): IDeliveryOrder {
    return {
      ...new DeliveryOrder(),
      id: this.editForm.get(['id'])!.value,
      sn: this.editForm.get(['sn'])!.value,
      memberName: this.editForm.get(['memberName'])!.value,
      memberSN: this.editForm.get(['memberSN'])!.value,
      orderType: this.editForm.get(['orderType'])!.value,
      shopSN: this.editForm.get(['shopSN'])!.value,
      shopName: this.editForm.get(['shopName'])!.value,
      priceTotal: this.editForm.get(['priceTotal'])!.value,
      cardReduce: this.editForm.get(['cardReduce'])!.value,
      cards: this.editForm.get(['cards'])!.value,
      rewardPointsReduce: this.editForm.get(['rewardPointsReduce'])!.value,
      paymentTotal: this.editForm.get(['paymentTotal'])!.value,
      distributionPlatform: this.editForm.get(['distributionPlatform'])!.value,
      deliveryNo: this.editForm.get(['deliveryNo'])!.value,
      deliveryStatus: this.editForm.get(['deliveryStatus'])!.value,
      deliveryDesc: this.editForm.get(['deliveryDesc'])!.value,
      deliverier: this.editForm.get(['deliverier'])!.value,
      deliverierMobile: this.editForm.get(['deliverierMobile'])!.value,
      deliveryDeductFee: this.editForm.get(['deliveryDeductFee'])!.value,
      deliveryFee: this.editForm.get(['deliveryFee'])!.value,
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
      address: this.editForm.get(['address'])!.value,
      packingFee: this.editForm.get(['packingFee'])!.value,
      paymentMode: this.editForm.get(['paymentMode'])!.value,
      diningDate: this.editForm.get(['diningDate'])!.value ? moment(this.editForm.get(['diningDate'])!.value, DATE_TIME_FORMAT) : undefined,
      remark: this.editForm.get(['remark'])!.value,
      status: this.editForm.get(['status'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      paidDate: this.editForm.get(['paidDate'])!.value ? moment(this.editForm.get(['paidDate'])!.value, DATE_TIME_FORMAT) : undefined,
      completedDate: this.editForm.get(['completedDate'])!.value
        ? moment(this.editForm.get(['completedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeliveryOrder>>): void {
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
