import {Component, OnInit, ViewChild} from '@angular/core';
import { ClrAlerts } from '@clr/angular';
import {Router} from "@angular/router";
import {SolicitacaoService} from '../../services/solicitacao/solicitacao.service';
import {AvaliacaoService} from '../../services/avaliacao/avaliacao.service';

@Component({
  selector: 'app-avaliacao',
  templateUrl: './avaliacao.component.html',
  styleUrls: ['./avaliacao.component.css']
})
export class AvaliacaoComponent implements OnInit {

  @ViewChild(ClrAlerts) alertsContainer: ClrAlerts;
  alerts: any [] = [];

  solicitacaos: Object[];
  avaliacaos: Object[];
  selected: any;
  isModalVisible = false;
  avaliacao: any;

  constructor(private service2: SolicitacaoService,
              private service: AvaliacaoService
            ){
      this.avaliacao = {
        "solicitacao": null,
        "nota": null,
        "deAvaliacao": "",
        "emailContatoAvaliador": ""
      };
  }

  ngOnInit() {
    this.selected= {};
    this.consultaSolicitacao();
  }

  selectedChanged(selected) {
    this.selected = selected;
  }

  consultaSolicitacao() {
    this.service2.consultar().subscribe(result => {
      this.solicitacaos = result;
    });
  }

  avaliarOpenModal(){
    if(this.selected.id==null){
      this.isModalVisible = false;

      this.alerts.push({
        type: "danger",
        text: 'Informe a solicitação para avaliar.'
      });

    } else{
      this.isModalVisible = true;
    }
  }

  clear(){
    this.avaliacao = {
      "solicitacao": null,
      "nota": null,
      "deAvaliacao": "",
      "emailContatoAvaliador": ""
    };

    this.consultaSolicitacao();
  }

  salvar(){
    this.avaliacao.solicitacao = this.selected.id;
    this.service.salvar(this.avaliacao).subscribe(
      data  => {
        this.isModalVisible = false;
        this.clear();
        this.alerts.push({
          type: "success",
          text: 'Avaliação registrada com sucesso!'
        });
      },
      error  => {
        this.isModalVisible = false;
        this.alerts.push({
          type: "danger",
          text: 'Falha ao registrar avaliação!'
        });
      }
    );
  }

}
