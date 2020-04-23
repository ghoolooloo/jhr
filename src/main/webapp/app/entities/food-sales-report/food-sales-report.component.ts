import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFoodSalesReport } from 'app/shared/model/food-sales-report.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { FoodSalesReportService } from './food-sales-report.service';
import { FoodSalesReportDeleteDialogComponent } from './food-sales-report-delete-dialog.component';

@Component({
  selector: 'jhi-food-sales-report',
  templateUrl: './food-sales-report.component.html'
})
export class FoodSalesReportComponent implements OnInit, OnDestroy {
  foodSalesReports?: IFoodSalesReport[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected foodSalesReportService: FoodSalesReportService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.foodSalesReportService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IFoodSalesReport[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInFoodSalesReports();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFoodSalesReport): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFoodSalesReports(): void {
    this.eventSubscriber = this.eventManager.subscribe('foodSalesReportListModification', () => this.loadPage());
  }

  delete(foodSalesReport: IFoodSalesReport): void {
    const modalRef = this.modalService.open(FoodSalesReportDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.foodSalesReport = foodSalesReport;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IFoodSalesReport[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/food-sales-report'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.foodSalesReports = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
