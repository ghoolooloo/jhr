import { Moment } from 'moment';

export interface ISales {
  id?: number;
  day?: Moment;
  shopSN?: string;
  shopName?: string;
  categorySN?: string;
  categoryName?: string;
  foodSN?: string;
  foodName?: string;
  foodOriginalPrice?: number;
  initQuantity?: number;
  salesQuantity?: number;
  lastModifiedDate?: Moment;
}

export class Sales implements ISales {
  constructor(
    public id?: number,
    public day?: Moment,
    public shopSN?: string,
    public shopName?: string,
    public categorySN?: string,
    public categoryName?: string,
    public foodSN?: string,
    public foodName?: string,
    public foodOriginalPrice?: number,
    public initQuantity?: number,
    public salesQuantity?: number,
    public lastModifiedDate?: Moment
  ) {}
}
