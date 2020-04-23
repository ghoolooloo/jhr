import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICarousel } from 'app/shared/model/carousel.model';
import { CarouselService } from './carousel.service';

@Component({
  templateUrl: './carousel-delete-dialog.component.html'
})
export class CarouselDeleteDialogComponent {
  carousel?: ICarousel;

  constructor(protected carouselService: CarouselService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.carouselService.delete(id).subscribe(() => {
      this.eventManager.broadcast('carouselListModification');
      this.activeModal.close();
    });
  }
}
