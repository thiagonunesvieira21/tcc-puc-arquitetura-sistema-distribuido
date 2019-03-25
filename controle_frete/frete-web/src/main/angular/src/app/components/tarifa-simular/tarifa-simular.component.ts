import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import { ClrAlerts, ClrForm } from '@clr/angular';
import {TarifaService} from '../../services/tarifa/tarifa.service';

@Component({
  selector: 'app-tarifa-simular',
  templateUrl: './tarifa-simular.component.html',
  styleUrls: ['./tarifa-simular.component.css']
})
export class SimularTarifaFreteListaComponent implements OnInit {
  @ViewChild(ClrAlerts) alertsContainer: ClrAlerts;
  alerts: any[] = [];

  tarifas: Object[];

  cepOrigem: string;
  cepDestino: string;
  qtDistancia: number;

  constructor(private service: TarifaService){
  }

  ngOnInit() {
  }

  simular() {
    this.service.simular(this.cepOrigem, this.cepDestino, this.qtDistancia).subscribe(
        result => {
          this.tarifas = result;

          if(this.tarifas.length>0){
            this.alerts.push({
              type: "success",
              text: 'Simulação realizada com sucesso!'
            });
          } else{
            this.alerts.push({
              type: "warning",
              text: 'Não foi possível realizar a simulação'
            });
          }
        }
    );
  }

}
