import { Moment } from 'moment';
import { OrderType } from 'app/shared/model/enumerations/order-type.model';
import { DistributionPlatform } from 'app/shared/model/enumerations/distribution-platform.model';
import { Sex } from 'app/shared/model/enumerations/sex.model';
import { OffsetType } from 'app/shared/model/enumerations/offset-type.model';
import { PaymentMethod } from 'app/shared/model/enumerations/payment-method.model';
import { OrderStatus } from 'app/shared/model/enumerations/order-status.model';

export interface IOrder {
  id?: number;
  sn?: string;
  memberName?: string;
  memberSN?: string;
  orderType?: OrderType;
  shopSN?: string;
  shopName?: string;
  priceTotal?: number;
  cardReduce?: number;
  cards?: string;
  rewardPointsReduce?: number;
  paymentTotal?: number;
  distributionPlatform?: DistributionPlatform;
  deliveryNo?: string;
  deliveryFee?: number;
  contact?: string;
  sex?: Sex;
  mobile?: string;
  country?: string;
  province?: string;
  city?: string;
  district?: string;
  offsetType?: OffsetType;
  longitude?: number;
  latitude?: number;
  address?: string;
  packingFee?: number;
  paymentMode?: PaymentMethod;
  diningDate?: Moment;
  remark?: string;
  status?: OrderStatus;
  createdDate?: Moment;
  paidDate?: Moment;
  expiredDate?: Moment;
}

export class Order implements IOrder {
  constructor(
    public id?: number,
    public sn?: string,
    public memberName?: string,
    public memberSN?: string,
    public orderType?: OrderType,
    public shopSN?: string,
    public shopName?: string,
    public priceTotal?: number,
    public cardReduce?: number,
    public cards?: string,
    public rewardPointsReduce?: number,
    public paymentTotal?: number,
    public distributionPlatform?: DistributionPlatform,
    public deliveryNo?: string,
    public deliveryFee?: number,
    public contact?: string,
    public sex?: Sex,
    public mobile?: string,
    public country?: string,
    public province?: string,
    public city?: string,
    public district?: string,
    public offsetType?: OffsetType,
    public longitude?: number,
    public latitude?: number,
    public address?: string,
    public packingFee?: number,
    public paymentMode?: PaymentMethod,
    public diningDate?: Moment,
    public remark?: string,
    public status?: OrderStatus,
    public createdDate?: Moment,
    public paidDate?: Moment,
    public expiredDate?: Moment
  ) {}
}
