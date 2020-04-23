import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { RewardPointsRecordDetailComponent } from 'app/entities/reward-points-record/reward-points-record-detail.component';
import { RewardPointsRecord } from 'app/shared/model/reward-points-record.model';

describe('Component Tests', () => {
  describe('RewardPointsRecord Management Detail Component', () => {
    let comp: RewardPointsRecordDetailComponent;
    let fixture: ComponentFixture<RewardPointsRecordDetailComponent>;
    const route = ({ data: of({ rewardPointsRecord: new RewardPointsRecord(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [RewardPointsRecordDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RewardPointsRecordDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RewardPointsRecordDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rewardPointsRecord on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rewardPointsRecord).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
