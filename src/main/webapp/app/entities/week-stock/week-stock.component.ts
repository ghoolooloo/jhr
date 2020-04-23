import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWeekStock } from 'app/shared/model/week-stock.model';
import { WeekStockService } from './week-stock.service';
import { WeekStockDeleteDialogComponent } from './week-stock-delete-dialog.component';

@Component({
  selector: 'jhi-week-stock',
  templateUrl: './week-stock.component.html'
})
export class WeekStockComponent implements OnInit, OnDestroy {
  weekStocks?: IWeekStock[];
  eventSubscriber?: Subscription;

  constructor(protected weekStockService: WeekStockService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.weekStockService.query().subscribe((res: HttpResponse<IWeekStock[]>) => (this.weekStocks = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWeekStocks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWeekStock): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWeekStocks(): void {
    this.eventSubscriber = this.eventManager.subscribe('weekStockListModification', () => this.loadAll());
  }

  delete(weekStock: IWeekStock): void {
    const modalRef = this.modalService.open(WeekStockDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.weekStock = weekStock;
  }
}
