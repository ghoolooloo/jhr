import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhrTestModule } from '../../../test.module';
import { CarouselComponent } from 'app/entities/carousel/carousel.component';
import { CarouselService } from 'app/entities/carousel/carousel.service';
import { Carousel } from 'app/shared/model/carousel.model';

describe('Component Tests', () => {
  describe('Carousel Management Component', () => {
    let comp: CarouselComponent;
    let fixture: ComponentFixture<CarouselComponent>;
    let service: CarouselService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [CarouselComponent]
      })
        .overrideTemplate(CarouselComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CarouselComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CarouselService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Carousel(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.carousels && comp.carousels[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
