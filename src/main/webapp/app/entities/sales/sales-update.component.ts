import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISales, Sales } from 'app/shared/model/sales.model';
import { SalesService } from './sales.service';

@Component({
  selector: 'jhi-sales-update',
  templateUrl: './sales-update.component.html'
})
export class SalesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    day: [null, [Validators.required]],
    shopSN: [null, [Validators.required, Validators.maxLength(30)]],
    shopName: [null, [Validators.required, Validators.maxLength(20)]],
    categorySN: [null, [Validators.required, Validators.maxLength(5)]],
    categoryName: [null, [Validators.required, Validators.maxLength(10)]],
    foodSN: [null, [Validators.required, Validators.maxLength(10)]],
    foodName: [null, [Validators.required, Validators.maxLength(20)]],
    foodOriginalPrice: [null, [Validators.min(0), Validators.max(99999)]],
    initQuantity: [null, [Validators.required, Validators.min(0), Validators.max(99999)]],
    salesQuantity: [null, [Validators.required, Validators.min(0), Validators.max(99999)]],
    lastModifiedDate: [null, [Validators.required]]
  });

  constructor(protected salesService: SalesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sales }) => {
      if (!sales.id) {
        const today = moment().startOf('day');
        sales.day = today;
        sales.lastModifiedDate = today;
      }

      this.updateForm(sales);
    });
  }

  updateForm(sales: ISales): void {
    this.editForm.patchValue({
      id: sales.id,
      day: sales.day ? sales.day.format(DATE_TIME_FORMAT) : null,
      shopSN: sales.shopSN,
      shopName: sales.shopName,
      categorySN: sales.categorySN,
      categoryName: sales.categoryName,
      foodSN: sales.foodSN,
      foodName: sales.foodName,
      foodOriginalPrice: sales.foodOriginalPrice,
      initQuantity: sales.initQuantity,
      salesQuantity: sales.salesQuantity,
      lastModifiedDate: sales.lastModifiedDate ? sales.lastModifiedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sales = this.createFromForm();
    if (sales.id !== undefined) {
      this.subscribeToSaveResponse(this.salesService.update(sales));
    } else {
      this.subscribeToSaveResponse(this.salesService.create(sales));
    }
  }

  private createFromForm(): ISales {
    return {
      ...new Sales(),
      id: this.editForm.get(['id'])!.value,
      day: this.editForm.get(['day'])!.value ? moment(this.editForm.get(['day'])!.value, DATE_TIME_FORMAT) : undefined,
      shopSN: this.editForm.get(['shopSN'])!.value,
      shopName: this.editForm.get(['shopName'])!.value,
      categorySN: this.editForm.get(['categorySN'])!.value,
      categoryName: this.editForm.get(['categoryName'])!.value,
      foodSN: this.editForm.get(['foodSN'])!.value,
      foodName: this.editForm.get(['foodName'])!.value,
      foodOriginalPrice: this.editForm.get(['foodOriginalPrice'])!.value,
      initQuantity: this.editForm.get(['initQuantity'])!.value,
      salesQuantity: this.editForm.get(['salesQuantity'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISales>>): void {
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
