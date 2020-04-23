import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWriteOffCard } from 'app/shared/model/write-off-card.model';

@Component({
  selector: 'jhi-write-off-card-detail',
  templateUrl: './write-off-card-detail.component.html'
})
export class WriteOffCardDetailComponent implements OnInit {
  writeOffCard: IWriteOffCard | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ writeOffCard }) => (this.writeOffCard = writeOffCard));
  }

  previousState(): void {
    window.history.back();
  }
}
