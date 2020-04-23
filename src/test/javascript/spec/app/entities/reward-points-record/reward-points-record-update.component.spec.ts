import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { RewardPointsRecordUpdateComponent } from 'app/entities/reward-points-record/reward-points-record-update.component';
import { RewardPointsRecordService } from 'app/entities/reward-points-record/reward-points-record.service';
import { RewardPointsRecord } from 'app/shared/model/reward-points-record.model';

describe('Component Tests', () => {
  describe('RewardPointsRecord Management Update Component', () => {
    let comp: RewardPointsRecordUpdateComponent;
    let fixture: ComponentFixture<RewardPointsRecordUpdateComponent>;
    let service: RewardPointsRecordService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [RewardPointsRecordUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(RewardPointsRecordUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RewardPointsRecordUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RewardPointsRecordService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RewardPointsRecord(123);
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
        const entity = new RewardPointsRecord();
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
