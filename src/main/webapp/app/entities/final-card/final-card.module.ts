import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { FinalCardComponent } from './final-card.component';
import { FinalCardDetailComponent } from './final-card-detail.component';
import { FinalCardUpdateComponent } from './final-card-update.component';
import { FinalCardDeleteDialogComponent } from './final-card-delete-dialog.component';
import { finalCardRoute } from './final-card.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(finalCardRoute)],
  declarations: [FinalCardComponent, FinalCardDetailComponent, FinalCardUpdateComponent, FinalCardDeleteDialogComponent],
  entryComponents: [FinalCardDeleteDialogComponent]
})
export class JhrFinalCardModule {}
