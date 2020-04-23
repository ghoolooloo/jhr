import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICardHolder } from 'app/shared/model/card-holder.model';
import { CardHolderService } from './card-holder.service';

@Component({
  templateUrl: './card-holder-delete-dialog.component.html'
})
export class CardHolderDeleteDialogComponent {
  cardHolder?: ICardHolder;

  constructor(
    protected cardHolderService: CardHolderService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cardHolderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cardHolderListModification');
      this.activeModal.close();
    });
  }
}
