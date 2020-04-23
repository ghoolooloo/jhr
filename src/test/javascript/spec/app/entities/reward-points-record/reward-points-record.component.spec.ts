import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { JhrTestModule } from '../../../test.module';
import { RewardPointsRecordComponent } from 'app/entities/reward-points-record/reward-points-record.component';
import { RewardPointsRecordService } from 'app/entities/reward-points-record/reward-points-record.service';
import { RewardPointsRecord } from 'app/shared/model/reward-points-record.model';

describe('Component Tests', () => {
  describe('RewardPointsRecord Management Component', () => {
    let comp: RewardPointsRecordComponent;
    let fixture: ComponentFixture<RewardPointsRecordComponent>;
    let service: RewardPointsRecordService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [RewardPointsRecordComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(RewardPointsRecordComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RewardPointsRecordComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RewardPointsRecordService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RewardPointsRecord(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.rewardPointsRecords && comp.rewardPointsRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RewardPointsRecord(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.rewardPointsRecords && comp.rewardPointsRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
