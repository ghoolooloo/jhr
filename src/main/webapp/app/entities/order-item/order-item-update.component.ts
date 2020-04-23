import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrderItem, OrderItem } from 'app/shared/model/order-item.model';
import { OrderItemService } from './order-item.service';

@Component({
  selector: 'jhi-order-item-update',
  templateUrl: './order-item-update.component.html'
})
export class OrderItemUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    orderSN: [null, [Validators.required, Validators.maxLength(20)]],
    foodCategoryName: [null, [Validators.required, Validators.maxLength(10)]],
    foodCategorySN: [null, [Validators.required, Validators.maxLength(5)]],
    foodName: [null, [Validators.required, Validators.maxLength(20)]],
    foodSN: [null, [Validators.required, Validators.maxLength(10)]],
    foodThumbnail: [null, [Validators.maxLength(200)]],
    foodPicture: [null, [Validators.maxLength(200)]],
    price: [null, [Validators.required, Validators.min(0), Validators.max(99999)]],
    originalPrice: [null, [Validators.min(0), Validators.max(99999)]],
    cost: [null, [Validators.min(0), Validators.max(99999)]],
    packingFee: [null, [Validators.min(0), Validators.max(99999)]],
    desc: [null, [Validators.maxLength(100)]],
    quantity: [null, [Validators.required, Validators.min(1)]],
    coupon: [null, [Validators.maxLength(32)]]
  });

  constructor(protected orderItemService: OrderItemService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ orderItem }) => {
      this.updateForm(orderItem);
    });
  }

  updateForm(orderItem: IOrderItem): void {
    this.editForm.patchValue({
      id: orderItem.id,
      orderSN: orderItem.orderSN,
      foodCategoryName: orderItem.foodCategoryName,
      foodCategorySN: orderItem.foodCategorySN,
      foodName: orderItem.foodName,
      foodSN: orderItem.foodSN,
      foodThumbnail: orderItem.foodThumbnail,
      foodPicture: orderItem.foodPicture,
      price: orderItem.price,
      originalPrice: orderItem.originalPrice,
      cost: orderItem.cost,
      packingFee: orderItem.packingFee,
      desc: orderItem.desc,
      quantity: orderItem.quantity,
      coupon: orderItem.coupon
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const orderItem = this.createFromForm();
    if (orderItem.id !== undefined) {
      this.subscribeToSaveResponse(this.orderItemService.update(orderItem));
    } else {
      this.subscribeToSaveResponse(this.orderItemService.create(orderItem));
    }
  }

  private createFromForm(): IOrderItem {
    return {
      ...new OrderItem(),
      id: this.editForm.get(['id'])!.value,
      orderSN: this.editForm.get(['orderSN'])!.value,
      foodCategoryName: this.editForm.get(['foodCategoryName'])!.value,
      foodCategorySN: this.editForm.get(['foodCategorySN'])!.value,
      foodName: this.editForm.get(['foodName'])!.value,
      foodSN: this.editForm.get(['foodSN'])!.value,
      foodThumbnail: this.editForm.get(['foodThumbnail'])!.value,
      foodPicture: this.editForm.get(['foodPicture'])!.value,
      price: this.editForm.get(['price'])!.value,
      originalPrice: this.editForm.get(['originalPrice'])!.value,
      cost: this.editForm.get(['cost'])!.value,
      packingFee: this.editForm.get(['packingFee'])!.value,
      desc: this.editForm.get(['desc'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      coupon: this.editForm.get(['coupon'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrderItem>>): void {
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
