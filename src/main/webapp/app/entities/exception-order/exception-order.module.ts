import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { ExceptionOrderComponent } from './exception-order.component';
import { ExceptionOrderDetailComponent } from './exception-order-detail.component';
import { ExceptionOrderUpdateComponent } from './exception-order-update.component';
import { ExceptionOrderDeleteDialogComponent } from './exception-order-delete-dialog.component';
import { exceptionOrderRoute } from './exception-order.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(exceptionOrderRoute)],
  declarations: [
    ExceptionOrderComponent,
    ExceptionOrderDetailComponent,
    ExceptionOrderUpdateComponent,
    ExceptionOrderDeleteDialogComponent
  ],
  entryComponents: [ExceptionOrderDeleteDialogComponent]
})
export class JhrExceptionOrderModule {}
