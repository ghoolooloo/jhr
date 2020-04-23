import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRewardPointsRecord, RewardPointsRecord } from 'app/shared/model/reward-points-record.model';
import { RewardPointsRecordService } from './reward-points-record.service';
import { IRewardPoints } from 'app/shared/model/reward-points.model';
import { RewardPointsService } from 'app/entities/reward-points/reward-points.service';

@Component({
  selector: 'jhi-reward-points-record-update',
  templateUrl: './reward-points-record-update.component.html'
})
export class RewardPointsRecordUpdateComponent implements OnInit {
  isSaving = false;
  rewardpoints: IRewardPoints[] = [];

  editForm = this.fb.group({
    id: [],
    amount: [null, [Validators.required]],
    remark: [null, [Validators.maxLength(100)]],
    createdDate: [null, [Validators.required]],
    rewardPointsId: []
  });

  constructor(
    protected rewardPointsRecordService: RewardPointsRecordService,
    protected rewardPointsService: RewardPointsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rewardPointsRecord }) => {
      if (!rewardPointsRecord.id) {
        const today = moment().startOf('day');
        rewardPointsRecord.createdDate = today;
      }

      this.updateForm(rewardPointsRecord);

      this.rewardPointsService.query().subscribe((res: HttpResponse<IRewardPoints[]>) => (this.rewardpoints = res.body || []));
    });
  }

  updateForm(rewardPointsRecord: IRewardPointsRecord): void {
    this.editForm.patchValue({
      id: rewardPointsRecord.id,
      amount: rewardPointsRecord.amount,
      remark: rewardPointsRecord.remark,
      createdDate: rewardPointsRecord.createdDate ? rewardPointsRecord.createdDate.format(DATE_TIME_FORMAT) : null,
      rewardPointsId: rewardPointsRecord.rewardPointsId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rewardPointsRecord = this.createFromForm();
    if (rewardPointsRecord.id !== undefined) {
      this.subscribeToSaveResponse(this.rewardPointsRecordService.update(rewardPointsRecord));
    } else {
      this.subscribeToSaveResponse(this.rewardPointsRecordService.create(rewardPointsRecord));
    }
  }

  private createFromForm(): IRewardPointsRecord {
    return {
      ...new RewardPointsRecord(),
      id: this.editForm.get(['id'])!.value,
      amount: this.editForm.get(['amount'])!.value,
      remark: this.editForm.get(['remark'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value
        ? moment(this.editForm.get(['createdDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      rewardPointsId: this.editForm.get(['rewardPointsId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRewardPointsRecord>>): void {
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

  trackById(index: number, item: IRewardPoints): any {
    return item.id;
  }
}
