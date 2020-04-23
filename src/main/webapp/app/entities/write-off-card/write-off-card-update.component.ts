import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWriteOffCard, WriteOffCard } from 'app/shared/model/write-off-card.model';
import { WriteOffCardService } from './write-off-card.service';

@Component({
  selector: 'jhi-write-off-card-update',
  templateUrl: './write-off-card-update.component.html'
})
export class WriteOffCardUpdateComponent implements OnInit {
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
    orderSN: [null, [Validators.maxLength(20)]],
    status: [null, [Validators.required]],
    writeOffDate: [null, [Validators.required]]
  });

  constructor(protected writeOffCardService: WriteOffCardService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ writeOffCard }) => {
      if (!writeOffCard.id) {
        const today = moment().startOf('day');
        writeOffCard.validPeriodBeginAt = today;
        writeOffCard.validPeriodEndAt = today;
        writeOffCard.receivedDate = today;
        writeOffCard.writeOffDate = today;
      }

      this.updateForm(writeOffCard);
    });
  }

  updateForm(writeOffCard: IWriteOffCard): void {
    this.editForm.patchValue({
      id: writeOffCard.id,
      code: writeOffCard.code,
      cardSN: writeOffCard.cardSN,
      cardType: writeOffCard.cardType,
      title: writeOffCard.title,
      thumbnail: writeOffCard.thumbnail,
      details: writeOffCard.details,
      validPeriodBeginAt: writeOffCard.validPeriodBeginAt ? writeOffCard.validPeriodBeginAt.format(DATE_TIME_FORMAT) : null,
      validPeriodEndAt: writeOffCard.validPeriodEndAt ? writeOffCard.validPeriodEndAt.format(DATE_TIME_FORMAT) : null,
      canUseWithOtherCard: writeOffCard.canUseWithOtherCard,
      acceptCategories: writeOffCard.acceptCategories,
      acceptShops: writeOffCard.acceptShops,
      leastCost: writeOffCard.leastCost,
      reduceCost: writeOffCard.reduceCost,
      discount: writeOffCard.discount,
      gift: writeOffCard.gift,
      giftQuantity: writeOffCard.giftQuantity,
      receivedBy: writeOffCard.receivedBy,
      receivedDate: writeOffCard.receivedDate ? writeOffCard.receivedDate.format(DATE_TIME_FORMAT) : null,
      orderSN: writeOffCard.orderSN,
      status: writeOffCard.status,
      writeOffDate: writeOffCard.writeOffDate ? writeOffCard.writeOffDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const writeOffCard = this.createFromForm();
    if (writeOffCard.id !== undefined) {
      this.subscribeToSaveResponse(this.writeOffCardService.update(writeOffCard));
    } else {
      this.subscribeToSaveResponse(this.writeOffCardService.create(writeOffCard));
    }
  }

  private createFromForm(): IWriteOffCard {
    return {
      ...new WriteOffCard(),
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
      orderSN: this.editForm.get(['orderSN'])!.value,
      status: this.editForm.get(['status'])!.value,
      writeOffDate: this.editForm.get(['writeOffDate'])!.value
        ? moment(this.editForm.get(['writeOffDate'])!.value, DATE_TIME_FORMAT)
        : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWriteOffCard>>): void {
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
