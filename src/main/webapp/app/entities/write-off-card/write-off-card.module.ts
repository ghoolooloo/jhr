import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { WriteOffCardComponent } from './write-off-card.component';
import { WriteOffCardDetailComponent } from './write-off-card-detail.component';
import { WriteOffCardUpdateComponent } from './write-off-card-update.component';
import { WriteOffCardDeleteDialogComponent } from './write-off-card-delete-dialog.component';
import { writeOffCardRoute } from './write-off-card.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(writeOffCardRoute)],
  declarations: [WriteOffCardComponent, WriteOffCardDetailComponent, WriteOffCardUpdateComponent, WriteOffCardDeleteDialogComponent],
  entryComponents: [WriteOffCardDeleteDialogComponent]
})
export class JhrWriteOffCardModule {}
