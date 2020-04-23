import { Moment } from 'moment';

export interface IMerchant {
  id?: number;
  name?: string;
  logo?: string;
  tel?: string;
  email?: string;
  website?: string;
  icp?: string;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
  lastModifiedBy?: string;
}

export class Merchant implements IMerchant {
  constructor(
    public id?: number,
    public name?: string,
    public logo?: string,
    public tel?: string,
    public email?: string,
    public website?: string,
    public icp?: string,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedDate?: Moment,
    public lastModifiedBy?: string
  ) {}
}
