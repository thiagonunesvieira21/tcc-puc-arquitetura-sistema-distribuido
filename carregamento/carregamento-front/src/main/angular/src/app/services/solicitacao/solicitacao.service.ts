import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { pathValues } from '../../utils';
import { PageRequest } from "../../shared/components/page-request";

@Injectable()
export class SolicitacaoService {
  constructor(private http: HttpClient) {}

  consultarPendentesPaginado(): Observable<any[]> {
    return this.http.get<any[]>(pathValues.solicitacaoApi);
  }

  transferir(transferencia: any){
      return this.http.post(pathValues.transferirApi, transferencia);
  }
}
