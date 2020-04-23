import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICard, Card } from 'app/shared/model/card.model';
import { CardService } from './card.service';

@Component({
  selector: 'jhi-card-update',
  templateUrl: './card-update.component.html'
})
export class CardUpdateComponent implements OnInit {
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
    lastModifiedDate: [],
    lastModifiedBy: [null, [Validators.maxLength(20)]]
  });

  constructor(protected cardService: CardService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ card }) => {
      if (!card.id) {
        const today = moment().startOf('day');
        card.validPeriodBeginAt = today;
        card.validPeriodEndAt = today;
        card.createdDate = today;
        card.deliveriedDate = today;
        card.lastModifiedDate = today;
      }

      this.updateForm(card);
    });
  }

  updateForm(card: ICard): void {
    this.editForm.patchValue({
      id: card.id,
      sn: card.sn,
      cardType: card.cardType,
      title: card.title,
      thumbnail: card.thumbnail,
      details: card.details,
      validPeriodBeginAt: card.validPeriodBeginAt ? card.validPeriodBeginAt.format(DATE_TIME_FORMAT) : null,
      validPeriodEndAt: card.validPeriodEndAt ? card.validPeriodEndAt.format(DATE_TIME_FORMAT) : null,
      quantity: card.quantity,
      receivedQuantity: card.receivedQuantity,
      canUseWithOtherCard: card.canUseWithOtherCard,
      acceptCategories: card.acceptCategories,
      acceptShops: card.acceptShops,
      leastCost: card.leastCost,
      reduceCost: card.reduceCost,
      discount: card.discount,
      gift: card.gift,
      giftQuantity: card.giftQuantity,
      status: card.status,
      createdBy: card.createdBy,
      createdDate: card.createdDate ? card.createdDate.format(DATE_TIME_FORMAT) : null,
      deliveriedBy: card.deliveriedBy,
      deliveriedDate: card.deliveriedDate ? card.deliveriedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: card.lastModifiedDate ? card.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: card.lastModifiedBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const card = this.createFromForm();
    if (card.id !== undefined) {
      this.subscribeToSaveResponse(this.cardService.update(card));
    } else {
      this.subscribeToSaveResponse(this.cardService.create(card));
    }
  }

  private createFromForm(): ICard {
    return {
      ...new Card(),
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
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICard>>): void {
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
