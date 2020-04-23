import { Moment } from 'moment';

export interface IFoodCategory {
  id?: number;
  name?: string;
  sn?: string;
  icon?: string;
  sort?: number;
  disabled?: boolean;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
  lastModifiedBy?: string;
}

export class FoodCategory implements IFoodCategory {
  constructor(
    public id?: number,
    public name?: string,
    public sn?: string,
    public icon?: string,
    public sort?: number,
    public disabled?: boolean,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedDate?: Moment,
    public lastModifiedBy?: string
  ) {
    this.disabled = this.disabled || false;
  }
}
