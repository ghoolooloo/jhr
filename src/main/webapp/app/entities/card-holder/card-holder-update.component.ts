import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICardHolder, CardHolder } from 'app/shared/model/card-holder.model';
import { CardHolderService } from './card-holder.service';

@Component({
  selector: 'jhi-card-holder-update',
  templateUrl: './card-holder-update.component.html'
})
export class CardHolderUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [null, [Validators.required, Validators.maxLength(40)]],
    cardSN: [null, [Validators.required, Validators.maxLength(20)]],
    cardType: [null, [Validators.required]],
    title: [null, [Validators.required, Validators.maxLength(100)]],
    thumbnail: [null, [Validators.maxLength(200)]],
    details: [null, [Validators.maxLength(300)]],
    validPeriodBeginAt: [],
    validPeriodEndAt: [],
    canUseWithOtherCard: [null, [Validators.required]],
    acceptCategories: [null, [Validators.maxLength(500)]],
    acceptShops: [null, [Validators.maxLength(500)]],
    leastCost: [null, [Validators.min(0), Validators.max(99999)]],
    reduceCost: [null, [Validators.min(0), Validators.max(99999)]],
    discount: [null, [Validators.min(0), Validators.max(100)]],
    gift: [null, [Validators.maxLength(10)]],
    giftQuantity: [null, [Validators.min(0), Validators.max(99999)]],
    receivedBy: [null, [Validators.required, Validators.maxLength(20)]],
    receivedDate: [null, [Validators.required]],
    held: [null, [Validators.maxLength(20)]]
  });

  constructor(protected cardHolderService: CardHolderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cardHolder }) => {
      if (!cardHolder.id) {
        const today = moment().startOf('day');
        cardHolder.validPeriodBeginAt = today;
        cardHolder.validPeriodEndAt = today;
        cardHolder.receivedDate = today;
      }

      this.updateForm(cardHolder);
    });
  }

  updateForm(cardHolder: ICardHolder): void {
    this.editForm.patchValue({
      id: cardHolder.id,
      code: cardHolder.code,
      cardSN: cardHolder.cardSN,
      cardType: cardHolder.cardType,
      title: cardHolder.title,
      thumbnail: cardHolder.thumbnail,
      details: cardHolder.details,
      validPeriodBeginAt: cardHolder.validPeriodBeginAt ? cardHolder.validPeriodBeginAt.format(DATE_TIME_FORMAT) : null,
      validPeriodEndAt: cardHolder.validPeriodEndAt ? cardHolder.validPeriodEndAt.format(DATE_TIME_FORMAT) : null,
      canUseWithOtherCard: cardHolder.canUseWithOtherCard,
      acceptCategories: cardHolder.acceptCategories,
      acceptShops: cardHolder.acceptShops,
      leastCost: cardHolder.leastCost,
      reduceCost: cardHolder.reduceCost,
      discount: cardHolder.discount,
      gift: cardHolder.gift,
      giftQuantity: cardHolder.giftQuantity,
      receivedBy: cardHolder.receivedBy,
      receivedDate: cardHolder.receivedDate ? cardHolder.receivedDate.format(DATE_TIME_FORMAT) : null,
      held: cardHolder.held
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const cardHolder = this.createFromForm();
    if (cardHolder.id !== undefined) {
      this.subscribeToSaveResponse(this.cardHolderService.update(cardHolder));
    } else {
      this.subscribeToSaveResponse(this.cardHolderService.create(cardHolder));
    }
  }

  private createFromForm(): ICardHolder {
    return {
      ...new CardHolder(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      cardSN: this.editForm.get(['cardSN'])!.value,
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
      canUseWithOtherCard: this.editForm.get(['canUseWithOtherCard'])!.value,
      acceptCategories: this.editForm.get(['acceptCategories'])!.value,
      acceptShops: this.editForm.get(['acceptShops'])!.value,
      leastCost: this.editForm.get(['leastCost'])!.value,
      reduceCost: this.editForm.get(['reduceCost'])!.value,
      discount: this.editForm.get(['discount'])!.value,
      gift: this.editForm.get(['gift'])!.value,
      giftQuantity: this.editForm.get(['giftQuantity'])!.value,
      receivedBy: this.editForm.get(['receivedBy'])!.value,
      receivedDate: this.editForm.get(['receivedDate'])!.value
        ? moment(this.editForm.get(['receivedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      held: this.editForm.get(['held'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICardHolder>>): void {
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
