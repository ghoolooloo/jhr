import { Moment } from 'moment';
import { IRewardPointsRecord } from 'app/shared/model/reward-points-record.model';

export interface IRewardPoints {
  id?: number;
  memberName?: string;
  memberSN?: string;
  balance?: number;
  lastModifiedDate?: Moment;
  records?: IRewardPointsRecord[];
}

export class RewardPoints implements IRewardPoints {
  constructor(
    public id?: number,
    public memberName?: string,
    public memberSN?: string,
    public balance?: number,
    public lastModifiedDate?: Moment,
    public records?: IRewardPointsRecord[]
  ) {}
}
