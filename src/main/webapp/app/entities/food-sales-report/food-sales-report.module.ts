import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { FoodSalesReportComponent } from './food-sales-report.component';
import { FoodSalesReportDetailComponent } from './food-sales-report-detail.component';
import { FoodSalesReportUpdateComponent } from './food-sales-report-update.component';
import { FoodSalesReportDeleteDialogComponent } from './food-sales-report-delete-dialog.component';
import { foodSalesReportRoute } from './food-sales-report.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(foodSalesReportRoute)],
  declarations: [
    FoodSalesReportComponent,
    FoodSalesReportDetailComponent,
    FoodSalesReportUpdateComponent,
    FoodSalesReportDeleteDialogComponent
  ],
  entryComponents: [FoodSalesReportDeleteDialogComponent]
})
export class JhrFoodSalesReportModule {}
