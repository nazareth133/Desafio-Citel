import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CitelTestModule } from '../../../test.module';
import { DoadorDetailComponent } from 'app/entities/doador/doador-detail.component';
import { Doador } from 'app/shared/model/doador.model';

describe('Component Tests', () => {
  describe('Doador Management Detail Component', () => {
    let comp: DoadorDetailComponent;
    let fixture: ComponentFixture<DoadorDetailComponent>;
    const route = ({ data: of({ doador: new Doador(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CitelTestModule],
        declarations: [DoadorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DoadorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DoadorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load doador on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.doador).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
