import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { CarouselDetailComponent } from 'app/entities/carousel/carousel-detail.component';
import { Carousel } from 'app/shared/model/carousel.model';

describe('Component Tests', () => {
  describe('Carousel Management Detail Component', () => {
    let comp: CarouselDetailComponent;
    let fixture: ComponentFixture<CarouselDetailComponent>;
    const route = ({ data: of({ carousel: new Carousel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [CarouselDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CarouselDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CarouselDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load carousel on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.carousel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
