export interface IOrderItem {
  id?: number;
  orderSN?: string;
  foodCategoryName?: string;
  foodCategorySN?: string;
  foodName?: string;
  foodSN?: string;
  foodThumbnail?: string;
  foodPicture?: string;
  price?: number;
  originalPrice?: number;
  cost?: number;
  packingFee?: number;
  desc?: string;
  quantity?: number;
  coupon?: string;
}

export class OrderItem implements IOrderItem {
  constructor(
    public id?: number,
    public orderSN?: string,
    public foodCategoryName?: string,
    public foodCategorySN?: string,
    public foodName?: string,
    public foodSN?: string,
    public foodThumbnail?: string,
    public foodPicture?: string,
    public price?: number,
    public originalPrice?: number,
    public cost?: number,
    public packingFee?: number,
    public desc?: string,
    public quantity?: number,
    public coupon?: string
  ) {}
}
