import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { pathValues } from '../../utils';
import { PageRequest } from "../../shared/components/page-request";

@Injectable()
  export class TarifaService {
  constructor(private http: HttpClient) {}

  consultar(): Observable<any[]> {
    return this.http.get<any[]>(pathValues.tarifaFreteApi);
  }

  consultarPorId(id:number): Observable<any> {
    return this.http.get<any>(pathValues.tarifaFreteApi+"/"+id);
  }

  simular(origem:string, destino:string, qtDistancia:number): Observable<any[]> {
    return this.http.get<any[]>(pathValues.tarifaFreteSimularApi+"/"+origem+"/"+destino+"/"+qtDistancia);
  }

  salvar(tarifa: any){
      return this.http.post(pathValues.tarifaFreteApi, tarifa);
  }

  delete(tarifa: any){
      return this.http.delete(pathValues.tarifaFreteApi+"/"+tarifa.id, tarifa);
  }

  alterar(tarifa: any){
      return this.http.put(pathValues.tarifaFreteApi+"/"+tarifa.id, tarifa);
  }

  reajustar(valor: any){
      return this.http.put(pathValues.ajustarTarifaFreteApi+"/"+valor, null);
  }

}
