import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRewardPoints } from 'app/shared/model/reward-points.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { RewardPointsService } from './reward-points.service';
import { RewardPointsDeleteDialogComponent } from './reward-points-delete-dialog.component';

@Component({
  selector: 'jhi-reward-points',
  templateUrl: './reward-points.component.html'
})
export class RewardPointsComponent implements OnInit, OnDestroy {
  rewardPoints?: IRewardPoints[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected rewardPointsService: RewardPointsService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.rewardPointsService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IRewardPoints[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInRewardPoints();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRewardPoints): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRewardPoints(): void {
    this.eventSubscriber = this.eventManager.subscribe('rewardPointsListModification', () => this.loadPage());
  }

  delete(rewardPoints: IRewardPoints): void {
    const modalRef = this.modalService.open(RewardPointsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rewardPoints = rewardPoints;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IRewardPoints[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/reward-points'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.rewardPoints = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
