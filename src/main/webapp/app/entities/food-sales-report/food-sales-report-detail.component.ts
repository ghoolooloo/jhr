import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFoodSalesReport } from 'app/shared/model/food-sales-report.model';

@Component({
  selector: 'jhi-food-sales-report-detail',
  templateUrl: './food-sales-report-detail.component.html'
})
export class FoodSalesReportDetailComponent implements OnInit {
  foodSalesReport: IFoodSalesReport | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ foodSalesReport }) => (this.foodSalesReport = foodSalesReport));
  }

  previousState(): void {
    window.history.back();
  }
}
