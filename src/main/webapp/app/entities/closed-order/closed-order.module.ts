import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { ClosedOrderComponent } from './closed-order.component';
import { ClosedOrderDetailComponent } from './closed-order-detail.component';
import { ClosedOrderUpdateComponent } from './closed-order-update.component';
import { ClosedOrderDeleteDialogComponent } from './closed-order-delete-dialog.component';
import { closedOrderRoute } from './closed-order.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(closedOrderRoute)],
  declarations: [ClosedOrderComponent, ClosedOrderDetailComponent, ClosedOrderUpdateComponent, ClosedOrderDeleteDialogComponent],
  entryComponents: [ClosedOrderDeleteDialogComponent]
})
export class JhrClosedOrderModule {}
