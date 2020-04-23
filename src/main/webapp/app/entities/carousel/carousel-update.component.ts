import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ICarousel, Carousel } from 'app/shared/model/carousel.model';
import { CarouselService } from './carousel.service';

@Component({
  selector: 'jhi-carousel-update',
  templateUrl: './carousel-update.component.html'
})
export class CarouselUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    picture: [null, [Validators.required, Validators.maxLength(200)]],
    sort: [null, [Validators.required, Validators.min(0), Validators.max(999999999)]],
    status: [null, [Validators.required]],
    createdBy: [null, [Validators.required, Validators.maxLength(20)]],
    createdDate: [null, [Validators.required]],
    lastModifiedDate: [],
    lastModifiedBy: [null, [Validators.maxLength(20)]]
  });

  constructor(protected carouselService: CarouselService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carousel }) => {
      if (!carousel.id) {
        const today = moment().startOf('day');
        carousel.createdDate = today;
        carousel.lastModifiedDate = today;
      }

      this.updateForm(carousel);
    });
  }

  updateForm(carousel: ICarousel): void {
    this.editForm.patchValue({
      id: carousel.id,
      picture: carousel.picture,
      sort: carousel.sort,
      status: carousel.status,
      createdBy: carousel.createdBy,
      createdDate: carousel.createdDate ? carousel.createdDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedDate: carousel.lastModifiedDate ? carousel.lastModifiedDate.format(DATE_TIME_FORMAT) : null,
      lastModifiedBy: carousel.lastModifiedBy
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const carousel = this.createFromForm();
    if (carousel.id !== undefined) {
      this.subscribeToSaveResponse(this.carouselService.update(carousel));
    } else {
      this.subscribeToSaveResponse(this.carouselService.create(carousel));
    }
  }

  private createFromForm(): ICarousel {
    return {
      ...new Carousel(),
      id: this.editForm.get(['id'])!.value,
      picture: this.editForm.get(['picture'])!.value,
      sort: this.editForm.get(['sort'])!.value,
      status: this.editForm.get(['status'])!.value,
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICarousel>>): void {
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
