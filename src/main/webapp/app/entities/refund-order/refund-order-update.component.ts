import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRefundOrder, RefundOrder } from 'app/shared/model/refund-order.model';
import { RefundOrderService } from './refund-order.service';

@Component({
  selector: 'jhi-refund-order-update',
  templateUrl: './refund-order-update.component.html'
})
export class RefundOrderUpdateComponent implements OnInit {
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

  constructor(protected refundOrderService: RefundOrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ refundOrder }) => {
      if (!refundOrder.id) {
        const today = moment().startOf('day');
        refundOrder.diningDate = today;
        refundOrder.createdDate = today;
        refundOrder.paidDate = today;
        refundOrder.completedDate = today;
        refundOrder.exceptionDate = today;
        refundOrder.handledDate = today;
        refundOrder.appliedDate = today;
        refundOrder.refundedDate = today;
      }

      this.updateForm(refundOrder);
    });
  }

  updateForm(refundOrder: IRefundOrder): void {
    this.editForm.patchValue({
      id: refundOrder.id,
      sn: refundOrder.sn,
      memberName: refundOrder.memberName,
      memberSN: refundOrder.memberSN,
      orderType: refundOrder.orderType,
      shopSN: refundOrder.shopSN,
      shopName: refundOrder.shopName,
      priceTotal: refundOrder.priceTotal,
      cardReduce: refundOrder.cardReduce,
      cards: refundOrder.cards,
      rewardPointsReduce: refundOrder.rewardPointsReduce,
      paymentTotal: refundOrder.paymentTotal,
      distributionPlatform: refundOrder.distributionPlatform,
      deliveryNo: refundOrder.deliveryNo,
      deliveryStatus: refundOrder.deliveryStatus,
      deliveryDesc: refundOrder.deliveryDesc,
      deliverier: refundOrder.deliverier,
      deliverierMobile: refundOrder.deliverierMobile,
      deliveryDeductFee: refundOrder.deliveryDeductFee,
      deliveryFee: refundOrder.deliveryFee,
      contact: refundOrder.contact,
      sex: refundOrder.sex,
      mobile: refundOrder.mobile,
      country: refundOrder.country,
      province: refundOrder.province,
      city: refundOrder.city,
      district: refundOrder.district,
      offsetType: refundOrder.offsetType,
      longitude: refundOrder.longitude,
      latitude: refundOrder.latitude,
      address: refundOrder.address,
      packingFee: refundOrder.packingFee,
      paymentMode: refundOrder.paymentMode,
      diningDate: refundOrder.diningDate ? refundOrder.diningDate.format(DATE_TIME_FORMAT) : null,
      remark: refundOrder.remark,
      status: refundOrder.status,
      createdDate: refundOrder.createdDate ? refundOrder.createdDate.format(DATE_TIME_FORMAT) : null,
      paidDate: refundOrder.paidDate ? refundOrder.paidDate.format(DATE_TIME_FORMAT) : null,
      completedDate: refundOrder.completedDate ? refundOrder.completedDate.format(DATE_TIME_FORMAT) : null,
      exceptionDate: refundOrder.exceptionDate ? refundOrder.exceptionDate.format(DATE_TIME_FORMAT) : null,
      handler: refundOrder.handler,
      handledDate: refundOrder.handledDate ? refundOrder.handledDate.format(DATE_TIME_FORMAT) : null,
      handledDesc: refundOrder.handledDesc,
      applicant: refundOrder.applicant,
      appliedDate: refundOrder.appliedDate ? refundOrder.appliedDate.format(DATE_TIME_FORMAT) : null,
      refundDesc: refundOrder.refundDesc,
      refundedBy: refundOrder.refundedBy,
      refundedDate: refundOrder.refundedDate ? refundOrder.refundedDate.format(DATE_TIME_FORMAT) : null,
      refundAmount: refundOrder.refundAmount,
      reply: refundOrder.reply,
      passed: refundOrder.passed
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const refundOrder = this.createFromForm();
    if (refundOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.refundOrderService.update(refundOrder));
    } else {
      this.subscribeToSaveResponse(this.refundOrderService.create(refundOrder));
    }
  }

  private createFromForm(): IRefundOrder {
    return {
      ...new RefundOrder(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRefundOrder>>): void {
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
