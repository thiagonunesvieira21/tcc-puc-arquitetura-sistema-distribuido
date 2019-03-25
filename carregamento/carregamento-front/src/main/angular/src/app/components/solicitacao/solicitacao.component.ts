import {Component, OnInit, ViewChild} from '@angular/core';
import { ClrAlerts } from '@clr/angular';
import {Router} from "@angular/router";
import {SolicitacaoService} from '../../services/solicitacao/solicitacao.service';
import {ConcorrenteService} from '../../services/concorrente/concorrente.service';

@Component({
  selector: 'app-solicitacao',
  templateUrl: './solicitacao.component.html',
  styleUrls: ['./solicitacao.component.css']
})
export class SolicitacaoComponent implements OnInit {

  @ViewChild(ClrAlerts) alertsContainer: ClrAlerts;
  alerts: any[] = [];

  solicitacaos: Object[];
  concorrentes: Object[];
  selected: any;
  empresa: any;
  isModalVisible = false;

  constructor(private service: SolicitacaoService,
              private service2: ConcorrenteService
            ){
  }

  ngOnInit() {
    this.selected= {};
    this.empresa= {};

    this.consultaSolicitacao();
    this.carregarConcorrentes();
  }

  selectedChanged(selected) {
    this.selected = selected;
  }

  consultaSolicitacao() {
    this.service.consultarPendentesPaginado().subscribe(result => {
      this.solicitacaos = result;
    });
  }

  carregarConcorrentes() {
    this.service2.consultar().subscribe(result => {
      this.concorrentes = result;
    });
  }

  transferirOpenModal(){
    if(this.selected.id==null || this.empresa.id==null){
      this.isModalVisible = false;

      this.alerts.push({
        type: 'danger',
        text: 'Informe a solicitação e a empresa concorrente.'
      });

    } else{
      this.isModalVisible = true;
    }
  }

  transferir(){
    let transferencia = {
      "idSolicitacao": this.selected.id,
      "idConcorrente": this.empresa.id
    };
    this.service.transferir(transferencia).subscribe(
      data  => {
        this.isModalVisible = false;
        this.alerts.push({
          type: 'success',
          text: 'Solicitação transferida com sucesso!'
        });
      },
      error  => {
        this.isModalVisible = false;
        this.alerts.push({
          type: 'danger',
          text: 'Falha ao transferir solicitação!'
        });
      }
    );
  }

}
