import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFoodSalesReport, FoodSalesReport } from 'app/shared/model/food-sales-report.model';
import { FoodSalesReportService } from './food-sales-report.service';

@Component({
  selector: 'jhi-food-sales-report-update',
  templateUrl: './food-sales-report-update.component.html'
})
export class FoodSalesReportUpdateComponent implements OnInit {
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
    cost: [null, [Validators.required]],
    price: [null, [Validators.required]],
    originalPrice: [],
    salesQuantity: [],
    salesAmount: [],
    refundOrders: [],
    refundAmount: [],
    grossProfit: [],
    initQuantity: [],
    remainder: []
  });

  constructor(
    protected foodSalesReportService: FoodSalesReportService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ foodSalesReport }) => {
      if (!foodSalesReport.id) {
        const today = moment().startOf('day');
        foodSalesReport.day = today;
      }

      this.updateForm(foodSalesReport);
    });
  }

  updateForm(foodSalesReport: IFoodSalesReport): void {
    this.editForm.patchValue({
      id: foodSalesReport.id,
      day: foodSalesReport.day ? foodSalesReport.day.format(DATE_TIME_FORMAT) : null,
      shopSN: foodSalesReport.shopSN,
      shopName: foodSalesReport.shopName,
      categorySN: foodSalesReport.categorySN,
      categoryName: foodSalesReport.categoryName,
      foodSN: foodSalesReport.foodSN,
      foodName: foodSalesReport.foodName,
      cost: foodSalesReport.cost,
      price: foodSalesReport.price,
      originalPrice: foodSalesReport.originalPrice,
      salesQuantity: foodSalesReport.salesQuantity,
      salesAmount: foodSalesReport.salesAmount,
      refundOrders: foodSalesReport.refundOrders,
      refundAmount: foodSalesReport.refundAmount,
      grossProfit: foodSalesReport.grossProfit,
      initQuantity: foodSalesReport.initQuantity,
      remainder: foodSalesReport.remainder
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const foodSalesReport = this.createFromForm();
    if (foodSalesReport.id !== undefined) {
      this.subscribeToSaveResponse(this.foodSalesReportService.update(foodSalesReport));
    } else {
      this.subscribeToSaveResponse(this.foodSalesReportService.create(foodSalesReport));
    }
  }

  private createFromForm(): IFoodSalesReport {
    return {
      ...new FoodSalesReport(),
      id: this.editForm.get(['id'])!.value,
      day: this.editForm.get(['day'])!.value ? moment(this.editForm.get(['day'])!.value, DATE_TIME_FORMAT) : undefined,
      shopSN: this.editForm.get(['shopSN'])!.value,
      shopName: this.editForm.get(['shopName'])!.value,
      categorySN: this.editForm.get(['categorySN'])!.value,
      categoryName: this.editForm.get(['categoryName'])!.value,
      foodSN: this.editForm.get(['foodSN'])!.value,
      foodName: this.editForm.get(['foodName'])!.value,
      cost: this.editForm.get(['cost'])!.value,
      price: this.editForm.get(['price'])!.value,
      originalPrice: this.editForm.get(['originalPrice'])!.value,
      salesQuantity: this.editForm.get(['salesQuantity'])!.value,
      salesAmount: this.editForm.get(['salesAmount'])!.value,
      refundOrders: this.editForm.get(['refundOrders'])!.value,
      refundAmount: this.editForm.get(['refundAmount'])!.value,
      grossProfit: this.editForm.get(['grossProfit'])!.value,
      initQuantity: this.editForm.get(['initQuantity'])!.value,
      remainder: this.editForm.get(['remainder'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFoodSalesReport>>): void {
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
