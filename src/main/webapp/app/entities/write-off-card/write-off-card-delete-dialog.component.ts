import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWriteOffCard } from 'app/shared/model/write-off-card.model';
import { WriteOffCardService } from './write-off-card.service';

@Component({
  templateUrl: './write-off-card-delete-dialog.component.html'
})
export class WriteOffCardDeleteDialogComponent {
  writeOffCard?: IWriteOffCard;

  constructor(
    protected writeOffCardService: WriteOffCardService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.writeOffCardService.delete(id).subscribe(() => {
      this.eventManager.broadcast('writeOffCardListModification');
      this.activeModal.close();
    });
  }
}
