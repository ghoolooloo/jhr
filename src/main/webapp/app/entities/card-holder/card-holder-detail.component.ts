import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICardHolder } from 'app/shared/model/card-holder.model';

@Component({
  selector: 'jhi-card-holder-detail',
  templateUrl: './card-holder-detail.component.html'
})
export class CardHolderDetailComponent implements OnInit {
  cardHolder: ICardHolder | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ cardHolder }) => (this.cardHolder = cardHolder));
  }

  previousState(): void {
    window.history.back();
  }
}
