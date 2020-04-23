import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { WriteOffCardUpdateComponent } from 'app/entities/write-off-card/write-off-card-update.component';
import { WriteOffCardService } from 'app/entities/write-off-card/write-off-card.service';
import { WriteOffCard } from 'app/shared/model/write-off-card.model';

describe('Component Tests', () => {
  describe('WriteOffCard Management Update Component', () => {
    let comp: WriteOffCardUpdateComponent;
    let fixture: ComponentFixture<WriteOffCardUpdateComponent>;
    let service: WriteOffCardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [WriteOffCardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WriteOffCardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WriteOffCardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WriteOffCardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WriteOffCard(123);
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
        const entity = new WriteOffCard();
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
