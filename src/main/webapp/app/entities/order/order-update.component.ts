import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IOrder, Order } from 'app/shared/model/order.model';
import { OrderService } from './order.service';

@Component({
  selector: 'jhi-order-update',
  templateUrl: './order-update.component.html'
})
export class OrderUpdateComponent implements OnInit {
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
    expiredDate: []
  });

  constructor(protected orderService: OrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ order }) => {
      if (!order.id) {
        const today = moment().startOf('day');
        order.diningDate = today;
        order.createdDate = today;
        order.paidDate = today;
        order.expiredDate = today;
      }

      this.updateForm(order);
    });
  }

  updateForm(order: IOrder): void {
    this.editForm.patchValue({
      id: order.id,
      sn: order.sn,
      memberName: order.memberName,
      memberSN: order.memberSN,
      orderType: order.orderType,
      shopSN: order.shopSN,
      shopName: order.shopName,
      priceTotal: order.priceTotal,
      cardReduce: order.cardReduce,
      cards: order.cards,
      rewardPointsReduce: order.rewardPointsReduce,
      paymentTotal: order.paymentTotal,
      distributionPlatform: order.distributionPlatform,
      deliveryNo: order.deliveryNo,
      deliveryFee: order.deliveryFee,
      contact: order.contact,
      sex: order.sex,
      mobile: order.mobile,
      country: order.country,
      province: order.province,
      city: order.city,
      district: order.district,
      offsetType: order.offsetType,
      longitude: order.longitude,
      latitude: order.latitude,
      address: order.address,
      packingFee: order.packingFee,
      paymentMode: order.paymentMode,
      diningDate: order.diningDate ? order.diningDate.format(DATE_TIME_FORMAT) : null,
      remark: order.remark,
      status: order.status,
      createdDate: order.createdDate ? order.createdDate.format(DATE_TIME_FORMAT) : null,
      paidDate: order.paidDate ? order.paidDate.format(DATE_TIME_FORMAT) : null,
      expiredDate: order.expiredDate ? order.expiredDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const order = this.createFromForm();
    if (order.id !== undefined) {
      this.subscribeToSaveResponse(this.orderService.update(order));
    } else {
      this.subscribeToSaveResponse(this.orderService.create(order));
    }
  }

  private createFromForm(): IOrder {
    return {
      ...new Order(),
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
      expiredDate: this.editForm.get(['expiredDate'])!.value
        ? moment(this.editForm.get(['expiredDate'])!.value, DATE_TIME_FORMAT)
        : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrder>>): void {
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
