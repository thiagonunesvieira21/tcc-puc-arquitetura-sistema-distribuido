import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {AvaliacaoService} from '../../services/avaliacao/avaliacao.service';

@Component({
  selector: 'app-avaliacao-lista',
  templateUrl: './avaliacao-lista.component.html',
  styleUrls: ['./avaliacao-lista.component.css']
})
export class AvaliacaoListaComponent implements OnInit {

  avaliacaos: Object[];

  constructor(private service: AvaliacaoService){
  }

  ngOnInit() {
    this.consultar();
  }

  consultar() {
    this.service.consultar().subscribe(result => {
      this.avaliacaos = result;
    });
  }

}
