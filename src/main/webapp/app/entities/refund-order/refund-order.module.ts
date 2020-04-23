import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { RefundOrderComponent } from './refund-order.component';
import { RefundOrderDetailComponent } from './refund-order-detail.component';
import { RefundOrderUpdateComponent } from './refund-order-update.component';
import { RefundOrderDeleteDialogComponent } from './refund-order-delete-dialog.component';
import { refundOrderRoute } from './refund-order.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(refundOrderRoute)],
  declarations: [RefundOrderComponent, RefundOrderDetailComponent, RefundOrderUpdateComponent, RefundOrderDeleteDialogComponent],
  entryComponents: [RefundOrderDeleteDialogComponent]
})
export class JhrRefundOrderModule {}
