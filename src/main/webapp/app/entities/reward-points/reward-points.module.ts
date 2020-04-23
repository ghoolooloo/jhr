import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { RewardPointsComponent } from './reward-points.component';
import { RewardPointsDetailComponent } from './reward-points-detail.component';
import { RewardPointsUpdateComponent } from './reward-points-update.component';
import { RewardPointsDeleteDialogComponent } from './reward-points-delete-dialog.component';
import { rewardPointsRoute } from './reward-points.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(rewardPointsRoute)],
  declarations: [RewardPointsComponent, RewardPointsDetailComponent, RewardPointsUpdateComponent, RewardPointsDeleteDialogComponent],
  entryComponents: [RewardPointsDeleteDialogComponent]
})
export class JhrRewardPointsModule {}
