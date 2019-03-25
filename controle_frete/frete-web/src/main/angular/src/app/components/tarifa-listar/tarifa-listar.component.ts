import {Component, OnInit, ViewChild} from '@angular/core';
import {ClrAlerts} from '@clr/angular';
import {Router} from "@angular/router";
import {TarifaService} from '../../services/tarifa/tarifa.service';

@Component({
  selector: 'app-tarifa-listar',
  templateUrl: './tarifa-listar.component.html',
  styleUrls: ['./tarifa-listar.component.css']
})
export class TarifaFreteListaComponent implements OnInit {
  @ViewChild(ClrAlerts) alertsContainer: ClrAlerts;
  alerts: any[] = [];

  tarifas: any[] = [];
  selected: any = {};
  isModalVisible = false;
  taxaAjuste: number;
  isModalVisibleAjuste = false;

  constructor(private service: TarifaService,
              protected router: Router){
  }

  ngOnInit() {
    this.selected= {};
    this.selected.id= null;
    this.consultar();
  }

  selectedChanged(selected) {
    this.selected = selected;
  }

  consultar() {
    this.service.consultar().subscribe(result => {
      this.tarifas = result;
    });
  }

  onDelete(){
    this.isModalVisible = true;
  }

  excluir(){
    this.service.delete(this.selected).subscribe(
      data  => {
        this.isModalVisible = false;
        this.alerts.push({
          type: 'success',
          text: 'Exclusão realizada com sucesso!'
        });
        this.tarifas.splice(this.tarifas.indexOf(this.selected), 1);
      },
      error  => {
        this.isModalVisible = false;
        this.alerts.push({
          type: 'danger',
          text: 'Falha ao excluir Tarifa!'
        });
      }
    );
  }

  reajustar(){
    this.service.reajustar(this.taxaAjuste).subscribe(
      data  => {
        this.isModalVisibleAjuste = false;

        this.service.consultar().subscribe(result => {
          this.tarifas = result;
          this.alerts.push({
            type: 'success',
            text: 'Ajuste de tarifas realizada com sucesso!'
          });
        });

      },
      error  => {
        this.isModalVisibleAjuste = false;
        this.alerts.push({
          type: 'danger',
          text: 'Falha ao ajustar Tarifas!'
        });
      }
    );
  }

}
