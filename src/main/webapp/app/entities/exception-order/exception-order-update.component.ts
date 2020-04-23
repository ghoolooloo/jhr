import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IExceptionOrder, ExceptionOrder } from 'app/shared/model/exception-order.model';
import { ExceptionOrderService } from './exception-order.service';

@Component({
  selector: 'jhi-exception-order-update',
  templateUrl: './exception-order-update.component.html'
})
export class ExceptionOrderUpdateComponent implements OnInit {
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
    exceptionDate: [],
    handler: [null, [Validators.required, Validators.maxLength(20)]],
    handledDate: [],
    handledDesc: [null, [Validators.maxLength(100)]]
  });

  constructor(protected exceptionOrderService: ExceptionOrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ exceptionOrder }) => {
      if (!exceptionOrder.id) {
        const today = moment().startOf('day');
        exceptionOrder.diningDate = today;
        exceptionOrder.createdDate = today;
        exceptionOrder.paidDate = today;
        exceptionOrder.exceptionDate = today;
        exceptionOrder.handledDate = today;
      }

      this.updateForm(exceptionOrder);
    });
  }

  updateForm(exceptionOrder: IExceptionOrder): void {
    this.editForm.patchValue({
      id: exceptionOrder.id,
      sn: exceptionOrder.sn,
      memberName: exceptionOrder.memberName,
      memberSN: exceptionOrder.memberSN,
      orderType: exceptionOrder.orderType,
      shopSN: exceptionOrder.shopSN,
      shopName: exceptionOrder.shopName,
      priceTotal: exceptionOrder.priceTotal,
      cardReduce: exceptionOrder.cardReduce,
      cards: exceptionOrder.cards,
      rewardPointsReduce: exceptionOrder.rewardPointsReduce,
      paymentTotal: exceptionOrder.paymentTotal,
      distributionPlatform: exceptionOrder.distributionPlatform,
      deliveryNo: exceptionOrder.deliveryNo,
      deliveryStatus: exceptionOrder.deliveryStatus,
      deliveryDesc: exceptionOrder.deliveryDesc,
      deliverier: exceptionOrder.deliverier,
      deliverierMobile: exceptionOrder.deliverierMobile,
      deliveryDeductFee: exceptionOrder.deliveryDeductFee,
      deliveryFee: exceptionOrder.deliveryFee,
      contact: exceptionOrder.contact,
      sex: exceptionOrder.sex,
      mobile: exceptionOrder.mobile,
      country: exceptionOrder.country,
      province: exceptionOrder.province,
      city: exceptionOrder.city,
      district: exceptionOrder.district,
      offsetType: exceptionOrder.offsetType,
      longitude: exceptionOrder.longitude,
      latitude: exceptionOrder.latitude,
      address: exceptionOrder.address,
      packingFee: exceptionOrder.packingFee,
      paymentMode: exceptionOrder.paymentMode,
      diningDate: exceptionOrder.diningDate ? exceptionOrder.diningDate.format(DATE_TIME_FORMAT) : null,
      remark: exceptionOrder.remark,
      status: exceptionOrder.status,
      createdDate: exceptionOrder.createdDate ? exceptionOrder.createdDate.format(DATE_TIME_FORMAT) : null,
      paidDate: exceptionOrder.paidDate ? exceptionOrder.paidDate.format(DATE_TIME_FORMAT) : null,
      exceptionDate: exceptionOrder.exceptionDate ? exceptionOrder.exceptionDate.format(DATE_TIME_FORMAT) : null,
      handler: exceptionOrder.handler,
      handledDate: exceptionOrder.handledDate ? exceptionOrder.handledDate.format(DATE_TIME_FORMAT) : null,
      handledDesc: exceptionOrder.handledDesc
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const exceptionOrder = this.createFromForm();
    if (exceptionOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.exceptionOrderService.update(exceptionOrder));
    } else {
      this.subscribeToSaveResponse(this.exceptionOrderService.create(exceptionOrder));
    }
  }

  private createFromForm(): IExceptionOrder {
    return {
      ...new ExceptionOrder(),
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
      exceptionDate: this.editForm.get(['exceptionDate'])!.value
        ? moment(this.editForm.get(['exceptionDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      handler: this.editForm.get(['handler'])!.value,
      handledDate: this.editForm.get(['handledDate'])!.value
        ? moment(this.editForm.get(['handledDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      handledDesc: this.editForm.get(['handledDesc'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExceptionOrder>>): void {
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
