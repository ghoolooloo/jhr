import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { FinalCardDetailComponent } from 'app/entities/final-card/final-card-detail.component';
import { FinalCard } from 'app/shared/model/final-card.model';

describe('Component Tests', () => {
  describe('FinalCard Management Detail Component', () => {
    let comp: FinalCardDetailComponent;
    let fixture: ComponentFixture<FinalCardDetailComponent>;
    const route = ({ data: of({ finalCard: new FinalCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [FinalCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FinalCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FinalCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load finalCard on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.finalCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
