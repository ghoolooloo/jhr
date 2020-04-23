import { Moment } from 'moment';

export interface IRewardPointsRecord {
  id?: number;
  amount?: number;
  remark?: string;
  createdDate?: Moment;
  rewardPointsId?: number;
}

export class RewardPointsRecord implements IRewardPointsRecord {
  constructor(
    public id?: number,
    public amount?: number,
    public remark?: string,
    public createdDate?: Moment,
    public rewardPointsId?: number
  ) {}
}
