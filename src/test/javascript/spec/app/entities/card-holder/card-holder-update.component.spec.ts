import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { CardHolderUpdateComponent } from 'app/entities/card-holder/card-holder-update.component';
import { CardHolderService } from 'app/entities/card-holder/card-holder.service';
import { CardHolder } from 'app/shared/model/card-holder.model';

describe('Component Tests', () => {
  describe('CardHolder Management Update Component', () => {
    let comp: CardHolderUpdateComponent;
    let fixture: ComponentFixture<CardHolderUpdateComponent>;
    let service: CardHolderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [CardHolderUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CardHolderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CardHolderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CardHolderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CardHolder(123);
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
        const entity = new CardHolder();
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
