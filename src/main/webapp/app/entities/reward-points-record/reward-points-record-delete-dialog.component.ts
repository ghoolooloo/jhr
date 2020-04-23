import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRewardPointsRecord } from 'app/shared/model/reward-points-record.model';
import { RewardPointsRecordService } from './reward-points-record.service';

@Component({
  templateUrl: './reward-points-record-delete-dialog.component.html'
})
export class RewardPointsRecordDeleteDialogComponent {
  rewardPointsRecord?: IRewardPointsRecord;

  constructor(
    protected rewardPointsRecordService: RewardPointsRecordService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rewardPointsRecordService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rewardPointsRecordListModification');
      this.activeModal.close();
    });
  }
}
