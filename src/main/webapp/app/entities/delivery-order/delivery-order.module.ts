import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { DeliveryOrderComponent } from './delivery-order.component';
import { DeliveryOrderDetailComponent } from './delivery-order-detail.component';
import { DeliveryOrderUpdateComponent } from './delivery-order-update.component';
import { DeliveryOrderDeleteDialogComponent } from './delivery-order-delete-dialog.component';
import { deliveryOrderRoute } from './delivery-order.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(deliveryOrderRoute)],
  declarations: [DeliveryOrderComponent, DeliveryOrderDetailComponent, DeliveryOrderUpdateComponent, DeliveryOrderDeleteDialogComponent],
  entryComponents: [DeliveryOrderDeleteDialogComponent]
})
export class JhrDeliveryOrderModule {}
