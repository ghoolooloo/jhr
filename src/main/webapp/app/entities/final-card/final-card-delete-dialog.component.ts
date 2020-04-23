import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFinalCard } from 'app/shared/model/final-card.model';
import { FinalCardService } from './final-card.service';

@Component({
  templateUrl: './final-card-delete-dialog.component.html'
})
export class FinalCardDeleteDialogComponent {
  finalCard?: IFinalCard;

  constructor(protected finalCardService: FinalCardService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.finalCardService.delete(id).subscribe(() => {
      this.eventManager.broadcast('finalCardListModification');
      this.activeModal.close();
    });
  }
}
