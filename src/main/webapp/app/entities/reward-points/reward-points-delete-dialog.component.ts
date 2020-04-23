import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRewardPoints } from 'app/shared/model/reward-points.model';
import { RewardPointsService } from './reward-points.service';

@Component({
  templateUrl: './reward-points-delete-dialog.component.html'
})
export class RewardPointsDeleteDialogComponent {
  rewardPoints?: IRewardPoints;

  constructor(
    protected rewardPointsService: RewardPointsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rewardPointsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rewardPointsListModification');
      this.activeModal.close();
    });
  }
}
