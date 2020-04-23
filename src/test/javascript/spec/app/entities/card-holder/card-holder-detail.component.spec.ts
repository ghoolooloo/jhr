import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhrTestModule } from '../../../test.module';
import { CardHolderDetailComponent } from 'app/entities/card-holder/card-holder-detail.component';
import { CardHolder } from 'app/shared/model/card-holder.model';

describe('Component Tests', () => {
  describe('CardHolder Management Detail Component', () => {
    let comp: CardHolderDetailComponent;
    let fixture: ComponentFixture<CardHolderDetailComponent>;
    const route = ({ data: of({ cardHolder: new CardHolder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhrTestModule],
        declarations: [CardHolderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CardHolderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CardHolderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load cardHolder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.cardHolder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
