import {Component, OnInit, ViewChild} from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ClrAlerts, ClrForm} from '@clr/angular';
import {TarifaService} from '../../services/tarifa/tarifa.service';

@Component({
  selector: 'app-tarifa',
  templateUrl: './tarifa.component.html',
  styleUrls: ['./tarifa.component.css']
})
export class TarifaFreteComponent implements OnInit {
  @ViewChild(ClrAlerts) alertsContainer: ClrAlerts;
  alerts: any[] = [];
  id: number;
  isAlteracao: boolean = false;
  tarifa: any;

  constructor(private service: TarifaService,
              private route: ActivatedRoute,
              protected router: Router
            ){
      this.clear();
  }

  ngOnInit() {
     this.route.params.subscribe(params => {
       this.id = +params.id;
       this.isAlteracao = this.id > 0;
       if(this.isAlteracao){
         this.service.consultarPorId(this.id).subscribe(result => {
           this.tarifa = result;
         });
       }
      }
    );
  }

  clear(){
    this.tarifa = {
      "id":null,
      "vrTarifaFrete": null,
      "qtDistanciaConcorrente": null,
      "qtDistancia": null,
      "tempoEntrega": null,
      "valor":null,
      "cepOrigem": "",
      "cepDestino": ""
    };
  }

  salvar(){
    this.service.salvar(this.tarifa).subscribe(
      data  => {
        this.clear();
        this.alerts.push({
          type: "success",
          text: 'Tarifa registrada com sucesso!'
        });
      },
      error  => {
        this.alerts.push({
          type: "danger",
          text: 'Falha ao registrar tarifa!'
        });
      }
    );
  }

  alterar(){
    this.service.alterar(this.tarifa).subscribe(
      data  => {
        this.clear();
        this.alerts.push({
          type: "success",
          text: 'Tarifa alterada com sucesso!'
        });
      },
      error  => {
        this.alerts.push({
          type: "danger",
          text: 'Falha ao alterar tarifa!'
        });
      }
    );
  }

}
