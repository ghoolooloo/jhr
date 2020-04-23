import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRewardPointsRecord } from 'app/shared/model/reward-points-record.model';

@Component({
  selector: 'jhi-reward-points-record-detail',
  templateUrl: './reward-points-record-detail.component.html'
})
export class RewardPointsRecordDetailComponent implements OnInit {
  rewardPointsRecord: IRewardPointsRecord | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rewardPointsRecord }) => (this.rewardPointsRecord = rewardPointsRecord));
  }

  previousState(): void {
    window.history.back();
  }
}
