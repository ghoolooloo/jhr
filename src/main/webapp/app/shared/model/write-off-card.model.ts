import { Moment } from 'moment';
import { CardType } from 'app/shared/model/enumerations/card-type.model';
import { WriteOffStatus } from 'app/shared/model/enumerations/write-off-status.model';

export interface IWriteOffCard {
  id?: number;
  code?: string;
  cardSN?: string;
  cardType?: CardType;
  title?: string;
  thumbnail?: string;
  details?: string;
  validPeriodBeginAt?: Moment;
  validPeriodEndAt?: Moment;
  canUseWithOtherCard?: boolean;
  acceptCategories?: string;
  acceptShops?: string;
  leastCost?: number;
  reduceCost?: number;
  discount?: number;
  gift?: string;
  giftQuantity?: number;
  receivedBy?: string;
  receivedDate?: Moment;
  orderSN?: string;
  status?: WriteOffStatus;
  writeOffDate?: Moment;
}

export class WriteOffCard implements IWriteOffCard {
  constructor(
    public id?: number,
    public code?: string,
    public cardSN?: string,
    public cardType?: CardType,
    public title?: string,
    public thumbnail?: string,
    public details?: string,
    public validPeriodBeginAt?: Moment,
    public validPeriodEndAt?: Moment,
    public canUseWithOtherCard?: boolean,
    public acceptCategories?: string,
    public acceptShops?: string,
    public leastCost?: number,
    public reduceCost?: number,
    public discount?: number,
    public gift?: string,
    public giftQuantity?: number,
    public receivedBy?: string,
    public receivedDate?: Moment,
    public orderSN?: string,
    public status?: WriteOffStatus,
    public writeOffDate?: Moment
  ) {
    this.canUseWithOtherCard = this.canUseWithOtherCard || false;
  }
}
