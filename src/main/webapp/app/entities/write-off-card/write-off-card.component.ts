import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWriteOffCard } from 'app/shared/model/write-off-card.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { WriteOffCardService } from './write-off-card.service';
import { WriteOffCardDeleteDialogComponent } from './write-off-card-delete-dialog.component';

@Component({
  selector: 'jhi-write-off-card',
  templateUrl: './write-off-card.component.html'
})
export class WriteOffCardComponent implements OnInit, OnDestroy {
  writeOffCards?: IWriteOffCard[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected writeOffCardService: WriteOffCardService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.writeOffCardService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IWriteOffCard[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInWriteOffCards();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWriteOffCard): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWriteOffCards(): void {
    this.eventSubscriber = this.eventManager.subscribe('writeOffCardListModification', () => this.loadPage());
  }

  delete(writeOffCard: IWriteOffCard): void {
    const modalRef = this.modalService.open(WriteOffCardDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.writeOffCard = writeOffCard;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IWriteOffCard[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/write-off-card'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.writeOffCards = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
