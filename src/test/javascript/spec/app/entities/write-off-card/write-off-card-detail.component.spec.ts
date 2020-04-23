import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { WriteOffCardDetailComponent } from 'app/entities/write-off-card/write-off-card-detail.component';
import { WriteOffCard } from 'app/shared/model/write-off-card.model';

describe('Component Tests', () => {
  describe('WriteOffCard Management Detail Component', () => {
    let comp: WriteOffCardDetailComponent;
    let fixture: ComponentFixture<WriteOffCardDetailComponent>;
    const route = ({ data: of({ writeOffCard: new WriteOffCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [WriteOffCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WriteOffCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WriteOffCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load writeOffCard on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.writeOffCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
