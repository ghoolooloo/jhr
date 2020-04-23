import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICarousel } from 'app/shared/model/carousel.model';

@Component({
  selector: 'jhi-carousel-detail',
  templateUrl: './carousel-detail.component.html'
})
export class CarouselDetailComponent implements OnInit {
  carousel: ICarousel | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ carousel }) => (this.carousel = carousel));
  }

  previousState(): void {
    window.history.back();
  }
}
