import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { pathValues } from '../../utils';
import { PageRequest } from "../../shared/components/page-request";

@Injectable()
export class SolicitacaoService {
  constructor(private http: HttpClient) {}

  consultar(): Observable<any[]> {
    return this.http.get<any[]>(pathValues.solicitacaoApi);
  }

}
