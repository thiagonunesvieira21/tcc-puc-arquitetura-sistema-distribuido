import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Routes } from '@angular/router';
import { TarifaFreteComponent, HomeComponent, TarifaFreteListaComponent, SimularTarifaFreteListaComponent } from './components';
import { AppAuthGuard } from './app.authguard';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [AppAuthGuard]
  },
  {
  	path: 'tarifa',
  	component: TarifaFreteComponent,
    canActivate: [AppAuthGuard]
  },
  {
    path: 'tarifa/:id',
    component: TarifaFreteComponent,
    canActivate: [AppAuthGuard]
  },
  {
  	path: 'tarifa-listar',
  	component: TarifaFreteListaComponent,
    canActivate: [AppAuthGuard]
  },
  {
  	path: 'tarifa-simular',
  	component: SimularTarifaFreteListaComponent,
    canActivate: [AppAuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { enableTracing: false })],
  exports: [RouterModule],
  providers: [AppAuthGuard]
})
export class AppRoutingModule {}
