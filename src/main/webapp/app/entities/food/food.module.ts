import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { FoodComponent } from './food.component';
import { FoodDetailComponent } from './food-detail.component';
import { FoodUpdateComponent } from './food-update.component';
import { FoodDeleteDialogComponent } from './food-delete-dialog.component';
import { foodRoute } from './food.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(foodRoute)],
  declarations: [FoodComponent, FoodDetailComponent, FoodUpdateComponent, FoodDeleteDialogComponent],
  entryComponents: [FoodDeleteDialogComponent]
})
export class JhrFoodModule {}
