import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { RewardPointsRecordComponent } from './reward-points-record.component';
import { RewardPointsRecordDetailComponent } from './reward-points-record-detail.component';
import { RewardPointsRecordUpdateComponent } from './reward-points-record-update.component';
import { RewardPointsRecordDeleteDialogComponent } from './reward-points-record-delete-dialog.component';
import { rewardPointsRecordRoute } from './reward-points-record.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(rewardPointsRecordRoute)],
  declarations: [
    RewardPointsRecordComponent,
    RewardPointsRecordDetailComponent,
    RewardPointsRecordUpdateComponent,
    RewardPointsRecordDeleteDialogComponent
  ],
  entryComponents: [RewardPointsRecordDeleteDialogComponent]
})
export class JhrRewardPointsRecordModule {}
