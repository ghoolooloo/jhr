import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICarousel } from 'app/shared/model/carousel.model';
import { CarouselService } from './carousel.service';
import { CarouselDeleteDialogComponent } from './carousel-delete-dialog.component';

@Component({
  selector: 'jhi-carousel',
  templateUrl: './carousel.component.html'
})
export class CarouselComponent implements OnInit, OnDestroy {
  carousels?: ICarousel[];
  eventSubscriber?: Subscription;

  constructor(protected carouselService: CarouselService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.carouselService.query().subscribe((res: HttpResponse<ICarousel[]>) => (this.carousels = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCarousels();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICarousel): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCarousels(): void {
    this.eventSubscriber = this.eventManager.subscribe('carouselListModification', () => this.loadAll());
  }

  delete(carousel: ICarousel): void {
    const modalRef = this.modalService.open(CarouselDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.carousel = carousel;
  }
}
