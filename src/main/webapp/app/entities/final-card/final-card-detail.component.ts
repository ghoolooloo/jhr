import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFinalCard } from 'app/shared/model/final-card.model';

@Component({
  selector: 'jhi-final-card-detail',
  templateUrl: './final-card-detail.component.html'
})
export class FinalCardDetailComponent implements OnInit {
  finalCard: IFinalCard | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ finalCard }) => (this.finalCard = finalCard));
  }

  previousState(): void {
    window.history.back();
  }
}
