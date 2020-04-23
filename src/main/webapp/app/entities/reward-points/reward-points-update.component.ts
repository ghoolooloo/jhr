import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRewardPoints, RewardPoints } from 'app/shared/model/reward-points.model';
import { RewardPointsService } from './reward-points.service';

@Component({
  selector: 'jhi-reward-points-update',
  templateUrl: './reward-points-update.component.html'
})
export class RewardPointsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    memberName: [null, [Validators.required, Validators.maxLength(20)]],
    memberSN: [null, [Validators.required, Validators.maxLength(20)]],
    balance: [null, [Validators.required, Validators.min(0)]],
    lastModifiedDate: [null, [Validators.required]]
  });

  constructor(protected rewardPointsService: RewardPointsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rewardPoints }) => {
      if (!rewardPoints.id) {
        const today = moment().startOf('day');
        rewardPoints.lastModifiedDate = today;
      }

      this.updateForm(rewardPoints);
    });
  }

  updateForm(rewardPoints: IRewardPoints): void {
    this.editForm.patchValue({
      id: rewardPoints.id,
      memberName: rewardPoints.memberName,
      memberSN: rewardPoints.memberSN,
      balance: rewardPoints.balance,
      lastModifiedDate: rewardPoints.lastModifiedDate ? rewardPoints.lastModifiedDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rewardPoints = this.createFromForm();
    if (rewardPoints.id !== undefined) {
      this.subscribeToSaveResponse(this.rewardPointsService.update(rewardPoints));
    } else {
      this.subscribeToSaveResponse(this.rewardPointsService.create(rewardPoints));
    }
  }

  private createFromForm(): IRewardPoints {
    return {
      ...new RewardPoints(),
      id: this.editForm.get(['id'])!.value,
      memberName: this.editForm.get(['memberName'])!.value,
      memberSN: this.editForm.get(['memberSN'])!.value,
      balance: this.editForm.get(['balance'])!.value,
      lastModifiedDate: this.editForm.get(['lastModifiedDate'])!.value
        ? moment(this.editForm.get(['lastModifiedDate'])!.value, DATE_TIME_FORMAT)
        : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRewardPoints>>): void {
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
