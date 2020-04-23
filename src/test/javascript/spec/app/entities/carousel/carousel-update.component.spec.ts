import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { CarouselUpdateComponent } from 'app/entities/carousel/carousel-update.component';
import { CarouselService } from 'app/entities/carousel/carousel.service';
import { Carousel } from 'app/shared/model/carousel.model';

describe('Component Tests', () => {
  describe('Carousel Management Update Component', () => {
    let comp: CarouselUpdateComponent;
    let fixture: ComponentFixture<CarouselUpdateComponent>;
    let service: CarouselService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [CarouselUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CarouselUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CarouselUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CarouselService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Carousel(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Carousel();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
