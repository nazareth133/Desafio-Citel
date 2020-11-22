export interface IDoador {
  id?: number;
  nome?: string;
  cpf?: string;
  rg?: string;
  dataNasc?: string;
  sexo?: string;
  mae?: string;
  pai?: string;
  email?: string;
  cep?: string;
  endereco?: string;
  numero?: number;
  bairro?: string;
  cidade?: string;
  estado?: string;
  telefoneFixo?: string;
  celular?: string;
  altura?: number;
  peso?: number;
  tipoSanguineo?: string;
}

export class Doador implements IDoador {
  constructor(
    public id?: number,
    public nome?: string,
    public cpf?: string,
    public rg?: string,
    public dataNasc?: string,
    public sexo?: string,
    public mae?: string,
    public pai?: string,
    public email?: string,
    public cep?: string,
    public endereco?: string,
    public numero?: number,
    public bairro?: string,
    public cidade?: string,
    public estado?: string,
    public telefoneFixo?: string,
    public celular?: string,
    public altura?: number,
    public peso?: number,
    public tipoSanguineo?: string
  ) {}
}
