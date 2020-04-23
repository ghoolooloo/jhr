import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { DeliveryAddressComponent } from './delivery-address.component';
import { DeliveryAddressDetailComponent } from './delivery-address-detail.component';
import { DeliveryAddressUpdateComponent } from './delivery-address-update.component';
import { DeliveryAddressDeleteDialogComponent } from './delivery-address-delete-dialog.component';
import { deliveryAddressRoute } from './delivery-address.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(deliveryAddressRoute)],
  declarations: [
    DeliveryAddressComponent,
    DeliveryAddressDetailComponent,
    DeliveryAddressUpdateComponent,
    DeliveryAddressDeleteDialogComponent
  ],
  entryComponents: [DeliveryAddressDeleteDialogComponent]
})
export class JhrDeliveryAddressModule {}
