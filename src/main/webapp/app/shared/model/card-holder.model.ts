import { Moment } from 'moment';
import { CardType } from 'app/shared/model/enumerations/card-type.model';

export interface ICardHolder {
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
  held?: string;
}

export class CardHolder implements ICardHolder {
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
    public held?: string
  ) {
    this.canUseWithOtherCard = this.canUseWithOtherCard || false;
  }
}
