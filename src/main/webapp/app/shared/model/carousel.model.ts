import { Moment } from 'moment';
import { CarouselStatus } from 'app/shared/model/enumerations/carousel-status.model';

export interface ICarousel {
  id?: number;
  picture?: string;
  sort?: number;
  status?: CarouselStatus;
  createdBy?: string;
  createdDate?: Moment;
  lastModifiedDate?: Moment;
  lastModifiedBy?: string;
}

export class Carousel implements ICarousel {
  constructor(
    public id?: number,
    public picture?: string,
    public sort?: number,
    public status?: CarouselStatus,
    public createdBy?: string,
    public createdDate?: Moment,
    public lastModifiedDate?: Moment,
    public lastModifiedBy?: string
  ) {}
}
