import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IClosedOrder, ClosedOrder } from 'app/shared/model/closed-order.model';
import { ClosedOrderService } from './closed-order.service';

@Component({
  selector: 'jhi-closed-order-update',
  templateUrl: './closed-order-update.component.html'
})
export class ClosedOrderUpdateComponent implements OnInit {
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
    expiredDate: [],
    completedDate: [],
    exceptionDate: [],
    handler: [null, [Validators.required, Validators.maxLength(20)]],
    handledDate: [],
    handledDesc: [null, [Validators.maxLength(100)]],
    applicant: [null, [Validators.required, Validators.maxLength(20)]],
    appliedDate: [null, [Validators.required]],
    refundDesc: [null, [Validators.maxLength(100)]],
    refundedBy: [null, [Validators.maxLength(20)]],
    refundedDate: [],
    refundAmount: [null, [Validators.min(0), Validators.max(99999)]],
    reply: [null, [Validators.maxLength(100)]],
    passed: []
  });

  constructor(protected closedOrderService: ClosedOrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ closedOrder }) => {
      if (!closedOrder.id) {
        const today = moment().startOf('day');
        closedOrder.diningDate = today;
        closedOrder.createdDate = today;
        closedOrder.paidDate = today;
        closedOrder.expiredDate = today;
        closedOrder.completedDate = today;
        closedOrder.exceptionDate = today;
        closedOrder.handledDate = today;
        closedOrder.appliedDate = today;
        closedOrder.refundedDate = today;
      }

      this.updateForm(closedOrder);
    });
  }

  updateForm(closedOrder: IClosedOrder): void {
    this.editForm.patchValue({
      id: closedOrder.id,
      sn: closedOrder.sn,
      memberName: closedOrder.memberName,
      memberSN: closedOrder.memberSN,
      orderType: closedOrder.orderType,
      shopSN: closedOrder.shopSN,
      shopName: closedOrder.shopName,
      priceTotal: closedOrder.priceTotal,
      cardReduce: closedOrder.cardReduce,
      cards: closedOrder.cards,
      rewardPointsReduce: closedOrder.rewardPointsReduce,
      paymentTotal: closedOrder.paymentTotal,
      distributionPlatform: closedOrder.distributionPlatform,
      deliveryNo: closedOrder.deliveryNo,
      deliveryStatus: closedOrder.deliveryStatus,
      deliveryDesc: closedOrder.deliveryDesc,
      deliverier: closedOrder.deliverier,
      deliverierMobile: closedOrder.deliverierMobile,
      deliveryDeductFee: closedOrder.deliveryDeductFee,
      deliveryFee: closedOrder.deliveryFee,
      contact: closedOrder.contact,
      sex: closedOrder.sex,
      mobile: closedOrder.mobile,
      country: closedOrder.country,
      province: closedOrder.province,
      city: closedOrder.city,
      district: closedOrder.district,
      offsetType: closedOrder.offsetType,
      longitude: closedOrder.longitude,
      latitude: closedOrder.latitude,
      address: closedOrder.address,
      packingFee: closedOrder.packingFee,
      paymentMode: closedOrder.paymentMode,
      diningDate: closedOrder.diningDate ? closedOrder.diningDate.format(DATE_TIME_FORMAT) : null,
      remark: closedOrder.remark,
      status: closedOrder.status,
      createdDate: closedOrder.createdDate ? closedOrder.createdDate.format(DATE_TIME_FORMAT) : null,
      paidDate: closedOrder.paidDate ? closedOrder.paidDate.format(DATE_TIME_FORMAT) : null,
      expiredDate: closedOrder.expiredDate ? closedOrder.expiredDate.format(DATE_TIME_FORMAT) : null,
      completedDate: closedOrder.completedDate ? closedOrder.completedDate.format(DATE_TIME_FORMAT) : null,
      exceptionDate: closedOrder.exceptionDate ? closedOrder.exceptionDate.format(DATE_TIME_FORMAT) : null,
      handler: closedOrder.handler,
      handledDate: closedOrder.handledDate ? closedOrder.handledDate.format(DATE_TIME_FORMAT) : null,
      handledDesc: closedOrder.handledDesc,
      applicant: closedOrder.applicant,
      appliedDate: closedOrder.appliedDate ? closedOrder.appliedDate.format(DATE_TIME_FORMAT) : null,
      refundDesc: closedOrder.refundDesc,
      refundedBy: closedOrder.refundedBy,
      refundedDate: closedOrder.refundedDate ? closedOrder.refundedDate.format(DATE_TIME_FORMAT) : null,
      refundAmount: closedOrder.refundAmount,
      reply: closedOrder.reply,
      passed: closedOrder.passed
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const closedOrder = this.createFromForm();
    if (closedOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.closedOrderService.update(closedOrder));
    } else {
      this.subscribeToSaveResponse(this.closedOrderService.create(closedOrder));
    }
  }

  private createFromForm(): IClosedOrder {
    return {
      ...new ClosedOrder(),
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
      expiredDate: this.editForm.get(['expiredDate'])!.value
        ? moment(this.editForm.get(['expiredDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      completedDate: this.editForm.get(['completedDate'])!.value
        ? moment(this.editForm.get(['completedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      exceptionDate: this.editForm.get(['exceptionDate'])!.value
        ? moment(this.editForm.get(['exceptionDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      handler: this.editForm.get(['handler'])!.value,
      handledDate: this.editForm.get(['handledDate'])!.value
        ? moment(this.editForm.get(['handledDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      handledDesc: this.editForm.get(['handledDesc'])!.value,
      applicant: this.editForm.get(['applicant'])!.value,
      appliedDate: this.editForm.get(['appliedDate'])!.value
        ? moment(this.editForm.get(['appliedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      refundDesc: this.editForm.get(['refundDesc'])!.value,
      refundedBy: this.editForm.get(['refundedBy'])!.value,
      refundedDate: this.editForm.get(['refundedDate'])!.value
        ? moment(this.editForm.get(['refundedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      refundAmount: this.editForm.get(['refundAmount'])!.value,
      reply: this.editForm.get(['reply'])!.value,
      passed: this.editForm.get(['passed'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClosedOrder>>): void {
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
