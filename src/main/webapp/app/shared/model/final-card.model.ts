import { Moment } from 'moment';
import { CardType } from 'app/shared/model/enumerations/card-type.model';
import { CardStatus } from 'app/shared/model/enumerations/card-status.model';

export interface IFinalCard {
  id?: number;
  sn?: string;
  cardType?: CardType;
  title?: string;
  thumbnail?: string;
  details?: string;
  validPeriodBeginAt?: Moment;
  validPeriodEndAt?: Moment;
  quantity?: number;
  receivedQuantity?: number;
  canUseWithOtherCard?: boolean;
  acceptCategories?: string;
  acceptShops?: string;
  leastCost?: number;
  reduceCost?: number;
  discount?: number;
  gift?: string;
  giftQuantity?: number;
  status?: CardStatus;
  createdBy?: string;
  createdDate?: Moment;
  deliveriedBy?: string;
  deliveriedDate?: Moment;
  discontinueBy?: string;
  discontinueDate?: Moment;
  lastModifiedDate?: Moment;
  lastModifiedBy?: string;
}

export class FinalCard implements IFinalCard {
  constructor(
    public id?: number,
    public sn?: string,
    public cardType?: CardType,
    public title?: string,
    public thumbnail?: string,
    public details?: string,
    public validPeriodBeginAt?: Moment,
    public validPeriodEndAt?: Moment,
    public quantity?: number,
    public receivedQuantity?: number,
    public canUseWithOtherCard?: boolean,
    public acceptCategories?: string,
    public acceptShops?: string,
    public leastCost?: number,
    public reduceCost?: number,
    public discount?: number,
    public gift?: string,
    public giftQuantity?: number,
    public status?: CardStatus,
    public createdBy?: string,
    public createdDate?: Moment,
    public deliveriedBy?: string,
    public deliveriedDate?: Moment,
    public discontinueBy?: string,
    public discontinueDate?: Moment,
    public lastModifiedDate?: Moment,
    public lastModifiedBy?: string
  ) {
    this.canUseWithOtherCard = this.canUseWithOtherCard || false;
  }
}
