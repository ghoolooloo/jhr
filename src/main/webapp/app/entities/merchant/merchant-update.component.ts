import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMerchant, Merchant } from 'app/shared/model/merchant.model';
import { MerchantService } from './merchant.service';

@Component({
  selector: 'jhi-merchant-update',
  templateUrl: './merchant-update.component.html'
})
export class MerchantUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(20)]],
    logo: [null, [Validators.maxLength(200)]],
    tel: [null, [Validators.maxLength(13)]],
    email: [null, [Validators.maxLength(40), Validators.pattern('^[\\w-]+@[\\w-]+(\\.[\\w-]+)+$')]],
    website: [null, [Validators.maxLength(200)]],
    icp: [null, [Validators.maxLength(20)]],
    createdBy: [null, [Validators.required, Validators.maxLength(20)]],
    createdDate: [null, [Validators.required]],
    lastModifiedDate: [],
    lastModifiedBy: [null, [Validators.maxLength(20)]]
  });

  constructor(protected merchantService: MerchantService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ merchant }) => {
      if (!merchant.id) {
        const today = moment().startOf('day');
        merchant.createdDate = today;
        merchant.lastModifiedDate = today;
      }

      this.updateForm(merchant);
    });
  }

  updateForm(merchant: IMerchant): void {
    this.editForm.patchValue({
      id: merchant.id,
      name: merchant.name,
      logo: merchant.logo,
      tel: merchant.tel,
      email: merchant.email,
      website: merchant.website,
      icp: merchant.icp,
      createdBy: merchant.createdBy,
      createdDate: merchant.createdDate ? merchant.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: merchant.lastModifiedDate ? merchant.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: merchant.lastModifiedBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const merchant = this.createFromForm();
    if (merchant.id !== undefined) {
      this.subscribeToSaveResponse(this.merchantService.update(merchant));
    } else {
      this.subscribeToSaveResponse(this.merchantService.create(merchant));
    }
  }

  private createFromForm(): IMerchant {
    return {
      ...new Merchant(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      logo: this.editForm.get(['logo'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      email: this.editForm.get(['email'])!.value,
      website: this.editForm.get(['website'])!.value,
      icp: this.editForm.get(['icp'])!.value,
      createdBy: this.editForm.get(['createdBy'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      lastModifiedBy: this.editForm.get(['lastModifiedBy'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMerchant>>): void {
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
