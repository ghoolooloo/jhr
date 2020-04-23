import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { FinalCardUpdateComponent } from 'app/entities/final-card/final-card-update.component';
import { FinalCardService } from 'app/entities/final-card/final-card.service';
import { FinalCard } from 'app/shared/model/final-card.model';

describe('Component Tests', () => {
  describe('FinalCard Management Update Component', () => {
    let comp: FinalCardUpdateComponent;
    let fixture: ComponentFixture<FinalCardUpdateComponent>;
    let service: FinalCardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [FinalCardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FinalCardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FinalCardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FinalCardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FinalCard(123);
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
        const entity = new FinalCard();
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
