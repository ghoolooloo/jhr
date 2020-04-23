import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { SalesComponent } from './sales.component';
import { SalesDetailComponent } from './sales-detail.component';
import { SalesUpdateComponent } from './sales-update.component';
import { SalesDeleteDialogComponent } from './sales-delete-dialog.component';
import { salesRoute } from './sales.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(salesRoute)],
  declarations: [SalesComponent, SalesDetailComponent, SalesUpdateComponent, SalesDeleteDialogComponent],
  entryComponents: [SalesDeleteDialogComponent]
})
export class JhrSalesModule {}
