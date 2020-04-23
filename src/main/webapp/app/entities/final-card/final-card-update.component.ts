import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFinalCard, FinalCard } from 'app/shared/model/final-card.model';
import { FinalCardService } from './final-card.service';

@Component({
  selector: 'jhi-final-card-update',
  templateUrl: './final-card-update.component.html'
})
export class FinalCardUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    sn: [null, [Validators.required, Validators.maxLength(20)]],
    cardType: [null, [Validators.required]],
    title: [null, [Validators.required, Validators.maxLength(100)]],
    thumbnail: [null, [Validators.maxLength(200)]],
    details: [null, [Validators.maxLength(300)]],
    validPeriodBeginAt: [],
    validPeriodEndAt: [],
    quantity: [null, [Validators.min(0), Validators.max(999999999)]],
    receivedQuantity: [null, [Validators.min(0), Validators.max(999999999)]],
    canUseWithOtherCard: [null, [Validators.required]],
    acceptCategories: [null, [Validators.maxLength(500)]],
    acceptShops: [null, [Validators.maxLength(500)]],
    leastCost: [null, [Validators.min(0), Validators.max(99999)]],
    reduceCost: [null, [Validators.min(0), Validators.max(99999)]],
    discount: [null, [Validators.min(0), Validators.max(100)]],
    gift: [null, [Validators.maxLength(10)]],
    giftQuantity: [null, [Validators.min(0), Validators.max(99999)]],
    status: [null, [Validators.required]],
    createdBy: [null, [Validators.required, Validators.maxLength(20)]],
    createdDate: [null, [Validators.required]],
    deliveriedBy: [null, [Validators.maxLength(20)]],
    deliveriedDate: [],
    discontinueBy: [null, [Validators.maxLength(20)]],
    discontinueDate: [],
    lastModifiedDate: [],
    lastModifiedBy: [null, [Validators.maxLength(20)]]
  });

  constructor(protected finalCardService: FinalCardService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ finalCard }) => {
      if (!finalCard.id) {
        const today = moment().startOf('day');
        finalCard.validPeriodBeginAt = today;
        finalCard.validPeriodEndAt = today;
        finalCard.createdDate = today;
        finalCard.deliveriedDate = today;
        finalCard.discontinueDate = today;
        finalCard.lastModifiedDate = today;
      }

      this.updateForm(finalCard);
    });
  }

  updateForm(finalCard: IFinalCard): void {
    this.editForm.patchValue({
      id: finalCard.id,
      sn: finalCard.sn,
      cardType: finalCard.cardType,
      title: finalCard.title,
      thumbnail: finalCard.thumbnail,
      details: finalCard.details,
      validPeriodBeginAt: finalCard.validPeriodBeginAt ? finalCard.validPeriodBeginAt.format(DATE_TIME_FORMAT) : null,
      validPeriodEndAt: finalCard.validPeriodEndAt ? finalCard.validPeriodEndAt.format(DATE_TIME_FORMAT) : null,
      quantity: finalCard.quantity,
      receivedQuantity: finalCard.receivedQuantity,
      canUseWithOtherCard: finalCard.canUseWithOtherCard,
      acceptCategories: finalCard.acceptCategories,
      acceptShops: finalCard.acceptShops,
      leastCost: finalCard.leastCost,
      reduceCost: finalCard.reduceCost,
      discount: finalCard.discount,
      gift: finalCard.gift,
      giftQuantity: finalCard.giftQuantity,
      status: finalCard.status,
      createdBy: finalCard.createdBy,
      createdDate: finalCard.createdDate ? finalCard.createdDate.format(DATE_TIME_FORMAT) : null,
      deliveriedBy: finalCard.deliveriedBy,
      deliveriedDate: finalCard.deliveriedDate ? finalCard.deliveriedDate.format(DATE_TIME_FORMAT) : null,
      discontinueBy: finalCard.discontinueBy,
      discontinueDate: finalCard.discontinueDate ? finalCard.discontinueDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: finalCard.lastModifiedDate ? finalCard.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: finalCard.lastModifiedBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const finalCard = this.createFromForm();
    if (finalCard.id !== undefined) {
      this.subscribeToSaveResponse(this.finalCardService.update(finalCard));
    } else {
      this.subscribeToSaveResponse(this.finalCardService.create(finalCard));
    }
  }

  private createFromForm(): IFinalCard {
    return {
      ...new FinalCard(),
      id: this.editForm.get(['id'])!.value,
      sn: this.editForm.get(['sn'])!.value,
      cardType: this.editForm.get(['cardType'])!.value,
      title: this.editForm.get(['title'])!.value,
      thumbnail: this.editForm.get(['thumbnail'])!.value,
      details: this.editForm.get(['details'])!.value,
      validPeriodBeginAt: this.editForm.get(['validPeriodBeginAt'])!.value
        ? moment(this.editForm.get(['validPeriodBeginAt'])!.value, DATE_TIME_FORMAT)
        : undefined,
      validPeriodEndAt: this.editForm.get(['validPeriodEndAt'])!.value
        ? moment(this.editForm.get(['validPeriodEndAt'])!.value, DATE_TIME_FORMAT)
        : undefined,
      quantity: this.editForm.get(['quantity'])!.value,
      receivedQuantity: this.editForm.get(['receivedQuantity'])!.value,
      canUseWithOtherCard: this.editForm.get(['canUseWithOtherCard'])!.value,
      acceptCategories: this.editForm.get(['acceptCategories'])!.value,
      acceptShops: this.editForm.get(['acceptShops'])!.value,
      leastCost: this.editForm.get(['leastCost'])!.value,
      reduceCost: this.editForm.get(['reduceCost'])!.value,
      discount: this.editForm.get(['discount'])!.value,
      gift: this.editForm.get(['gift'])!.value,
      giftQuantity: this.editForm.get(['giftQuantity'])!.value,
      status: this.editForm.get(['status'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      deliveriedBy: this.editForm.get(['deliveriedBy'])!.value,
      deliveriedDate: this.editForm.get(['deliveriedDate'])!.value
        ? moment(this.editForm.get(['deliveriedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      discontinueBy: this.editForm.get(['discontinueBy'])!.value,
      discontinueDate: this.editForm.get(['discontinueDate'])!.value
        ? moment(this.editForm.get(['discontinueDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFinalCard>>): void {
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
