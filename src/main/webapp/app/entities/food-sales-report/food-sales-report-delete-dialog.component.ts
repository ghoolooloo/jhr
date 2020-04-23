import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFoodSalesReport } from 'app/shared/model/food-sales-report.model';
import { FoodSalesReportService } from './food-sales-report.service';

@Component({
  templateUrl: './food-sales-report-delete-dialog.component.html'
})
export class FoodSalesReportDeleteDialogComponent {
  foodSalesReport?: IFoodSalesReport;

  constructor(
    protected foodSalesReportService: FoodSalesReportService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.foodSalesReportService.delete(id).subscribe(() => {
      this.eventManager.broadcast('foodSalesReportListModification');
      this.activeModal.close();
    });
  }
}
