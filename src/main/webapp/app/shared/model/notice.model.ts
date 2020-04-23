import { Moment } from 'moment';
import { NoticeStatus } from 'app/shared/model/enumerations/notice-status.model';

export interface INotice {
  id?: number;
  content?: string;
  sort?: number;
  status?: NoticeStatus;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
  lastModifiedBy?: string;
}

export class Notice implements INotice {
  constructor(
    public id?: number,
    public content?: string,
    public sort?: number,
    public status?: NoticeStatus,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedDate?: Moment,
    public lastModifiedBy?: string
  ) {}
}
