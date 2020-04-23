import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { CardHolderComponent } from './card-holder.component';
import { CardHolderDetailComponent } from './card-holder-detail.component';
import { CardHolderUpdateComponent } from './card-holder-update.component';
import { CardHolderDeleteDialogComponent } from './card-holder-delete-dialog.component';
import { cardHolderRoute } from './card-holder.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(cardHolderRoute)],
  declarations: [CardHolderComponent, CardHolderDetailComponent, CardHolderUpdateComponent, CardHolderDeleteDialogComponent],
  entryComponents: [CardHolderDeleteDialogComponent]
})
export class JhrCardHolderModule {}
