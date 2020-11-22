import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DoadorService } from 'app/entities/doador/doador.service';
import { IDoador, Doador } from 'app/shared/model/doador.model';

describe('Service Tests', () => {
  describe('Doador Service', () => {
    let injector: TestBed;
    let service: DoadorService;
    let httpMock: HttpTestingController;
    let elemDefault: IDoador;
    let expectedResult: IDoador | IDoador[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DoadorService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Doador(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should return a list of Doador', () => {
        const returnedFromService = Object.assign(
          {
            nome: 'BBBBBB',
            cpf: 'BBBBBB',
            rg: 'BBBBBB',
            dataNasc: 'BBBBBB',
            sexo: 'BBBBBB',
            mae: 'BBBBBB',
            pai: 'BBBBBB',
            email: 'BBBBBB',
            cep: 'BBBBBB',
            endereco: 'BBBBBB',
            numero: 1,
            bairro: 'BBBBBB',
            cidade: 'BBBBBB',
            estado: 'BBBBBB',
            telefoneFixo: 'BBBBBB',
            celular: 'BBBBBB',
            altura: 1,
            peso: 1,
            tipoSanguineo: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
