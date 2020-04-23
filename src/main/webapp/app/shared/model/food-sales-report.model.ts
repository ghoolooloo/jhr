import { Moment } from 'moment';

export interface IFoodSalesReport {
  id?: number;
  day?: Moment;
  shopSN?: string;
  shopName?: string;
  categorySN?: string;
  categoryName?: string;
  foodSN?: string;
  foodName?: string;
  cost?: number;
  price?: number;
  originalPrice?: number;
  salesQuantity?: number;
  salesAmount?: number;
  refundOrders?: number;
  refundAmount?: number;
  grossProfit?: number;
  initQuantity?: number;
  remainder?: number;
}

export class FoodSalesReport implements IFoodSalesReport {
  constructor(
    public id?: number,
    public day?: Moment,
    public shopSN?: string,
    public shopName?: string,
    public categorySN?: string,
    public categoryName?: string,
    public foodSN?: string,
    public foodName?: string,
    public cost?: number,
    public price?: number,
    public originalPrice?: number,
    public salesQuantity?: number,
    public salesAmount?: number,
    public refundOrders?: number,
    public refundAmount?: number,
    public grossProfit?: number,
    public initQuantity?: number,
    public remainder?: number
  ) {}
}
