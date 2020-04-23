import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { RewardPointsDetailComponent } from 'app/entities/reward-points/reward-points-detail.component';
import { RewardPoints } from 'app/shared/model/reward-points.model';

describe('Component Tests', () => {
  describe('RewardPoints Management Detail Component', () => {
    let comp: RewardPointsDetailComponent;
    let fixture: ComponentFixture<RewardPointsDetailComponent>;
    const route = ({ data: of({ rewardPoints: new RewardPoints(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [RewardPointsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(RewardPointsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RewardPointsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load rewardPoints on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.rewardPoints).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
