import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRewardPoints } from 'app/shared/model/reward-points.model';

@Component({
  selector: 'jhi-reward-points-detail',
  templateUrl: './reward-points-detail.component.html'
})
export class RewardPointsDetailComponent implements OnInit {
  rewardPoints: IRewardPoints | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rewardPoints }) => (this.rewardPoints = rewardPoints));
  }

  previousState(): void {
    window.history.back();
  }
}
