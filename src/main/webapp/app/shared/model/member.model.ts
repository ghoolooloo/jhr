import { Moment } from 'moment';
import { Sex } from 'app/shared/model/enumerations/sex.model';

export interface IMember {
  id?: number;
  name?: string;
  sn?: string;
  openID?: string;
  unionID?: string;
  profilePicture?: string;
  sex?: Sex;
  country?: string;
  province?: string;
  city?: string;
  joinDate?: Moment;
  lastLoginDate?: Moment;
}

export class Member implements IMember {
  constructor(
    public id?: number,
    public name?: string,
    public sn?: string,
    public openID?: string,
    public unionID?: string,
    public profilePicture?: string,
    public sex?: Sex,
    public country?: string,
    public province?: string,
    public city?: string,
    public joinDate?: Moment,
    public lastLoginDate?: Moment
  ) {}
}
