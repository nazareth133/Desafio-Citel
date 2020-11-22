import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { IDoador } from 'app/shared/model/doador.model';

type EntityResponseType = HttpResponse<IDoador>;
type EntityArrayResponseType = HttpResponse<IDoador[]>;

@Injectable({ providedIn: 'root' })
export class DoadorService {
  public resourceUrl = SERVER_API_URL + 'api/doadors';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/doadors';

  constructor(protected http: HttpClient) {}

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDoador>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  upload(file: any): Observable<HttpEvent<any>> {
   
    const formData: FormData = new FormData();
    formData.append('file', file);
    
    return this.http.post<IDoador>(`${this.resourceUrl}/upload`, formData, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDoador[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDoador[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
