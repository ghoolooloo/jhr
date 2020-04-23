import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { RewardPointsUpdateComponent } from 'app/entities/reward-points/reward-points-update.component';
import { RewardPointsService } from 'app/entities/reward-points/reward-points.service';
import { RewardPoints } from 'app/shared/model/reward-points.model';

describe('Component Tests', () => {
  describe('RewardPoints Management Update Component', () => {
    let comp: RewardPointsUpdateComponent;
    let fixture: ComponentFixture<RewardPointsUpdateComponent>;
    let service: RewardPointsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [RewardPointsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RewardPointsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RewardPointsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RewardPointsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RewardPoints(123);
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
        const entity = new RewardPoints();
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
