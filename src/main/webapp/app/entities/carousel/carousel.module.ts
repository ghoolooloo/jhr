import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhrSharedModule } from 'app/shared/shared.module';
import { CarouselComponent } from './carousel.component';
import { CarouselDetailComponent } from './carousel-detail.component';
import { CarouselUpdateComponent } from './carousel-update.component';
import { CarouselDeleteDialogComponent } from './carousel-delete-dialog.component';
import { carouselRoute } from './carousel.route';

@NgModule({
  imports: [JhrSharedModule, RouterModule.forChild(carouselRoute)],
  declarations: [CarouselComponent, CarouselDetailComponent, CarouselUpdateComponent, CarouselDeleteDialogComponent],
  entryComponents: [CarouselDeleteDialogComponent]
})
export class JhrCarouselModule {}
