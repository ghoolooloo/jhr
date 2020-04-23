import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'food-category',
        loadChildren: () => import('./food-category/food-category.module').then(m => m.JhrFoodCategoryModule)
      },
      {
        path: 'food',
        loadChildren: () => import('./food/food.module').then(m => m.JhrFoodModule)
      },
      {
        path: 'week-menu',
        loadChildren: () => import('./week-menu/week-menu.module').then(m => m.JhrWeekMenuModule)
      },
      {
        path: 'week-stock',
        loadChildren: () => import('./week-stock/week-stock.module').then(m => m.JhrWeekStockModule)
      },
      {
        path: 'shop',
        loadChildren: () => import('./shop/shop.module').then(m => m.JhrShopModule)
      },
      {
        path: 'merchant',
        loadChildren: () => import('./merchant/merchant.module').then(m => m.JhrMerchantModule)
      },
      {
        path: 'member',
        loadChildren: () => import('./member/member.module').then(m => m.JhrMemberModule)
      },
      {
        path: 'delivery-address',
        loadChildren: () => import('./delivery-address/delivery-address.module').then(m => m.JhrDeliveryAddressModule)
      },
      {
        path: 'order',
        loadChildren: () => import('./order/order.module').then(m => m.JhrOrderModule)
      },
      {
        path: 'order-item',
        loadChildren: () => import('./order-item/order-item.module').then(m => m.JhrOrderItemModule)
      },
      {
        path: 'delivery-order',
        loadChildren: () => import('./delivery-order/delivery-order.module').then(m => m.JhrDeliveryOrderModule)
      },
      {
        path: 'exception-order',
        loadChildren: () => import('./exception-order/exception-order.module').then(m => m.JhrExceptionOrderModule)
      },
      {
        path: 'refund-order',
        loadChildren: () => import('./refund-order/refund-order.module').then(m => m.JhrRefundOrderModule)
      },
      {
        path: 'closed-order',
        loadChildren: () => import('./closed-order/closed-order.module').then(m => m.JhrClosedOrderModule)
      },
      {
        path: 'sales',
        loadChildren: () => import('./sales/sales.module').then(m => m.JhrSalesModule)
      },
      {
        path: 'food-sales-report',
        loadChildren: () => import('./food-sales-report/food-sales-report.module').then(m => m.JhrFoodSalesReportModule)
      },
      {
        path: 'reward-points',
        loadChildren: () => import('./reward-points/reward-points.module').then(m => m.JhrRewardPointsModule)
      },
      {
        path: 'reward-points-record',
        loadChildren: () => import('./reward-points-record/reward-points-record.module').then(m => m.JhrRewardPointsRecordModule)
      },
      {
        path: 'card',
        loadChildren: () => import('./card/card.module').then(m => m.JhrCardModule)
      },
      {
        path: 'final-card',
        loadChildren: () => import('./final-card/final-card.module').then(m => m.JhrFinalCardModule)
      },
      {
        path: 'card-holder',
        loadChildren: () => import('./card-holder/card-holder.module').then(m => m.JhrCardHolderModule)
      },
      {
        path: 'write-off-card',
        loadChildren: () => import('./write-off-card/write-off-card.module').then(m => m.JhrWriteOffCardModule)
      },
      {
        path: 'carousel',
        loadChildren: () => import('./carousel/carousel.module').then(m => m.JhrCarouselModule)
      },
      {
        path: 'notice',
        loadChildren: () => import('./notice/notice.module').then(m => m.JhrNoticeModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class JhrEntityModule {}
