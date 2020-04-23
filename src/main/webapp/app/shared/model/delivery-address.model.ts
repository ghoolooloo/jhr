import { Sex } from 'app/shared/model/enumerations/sex.model';
import { OffsetType } from 'app/shared/model/enumerations/offset-type.model';

export interface IDeliveryAddress {
  id?: number;
  memberName?: string;
  memberSN?: string;
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
}

export class DeliveryAddress implements IDeliveryAddress {
  constructor(
    public id?: number,
    public memberName?: string,
    public memberSN?: string,
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
    public address?: string
  ) {}
}
