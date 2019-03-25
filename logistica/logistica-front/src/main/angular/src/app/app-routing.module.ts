import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Routes } from '@angular/router';
import { AvaliacaoComponent, HomeComponent, AvaliacaoListaComponent } from './components';
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
  	path: 'avaliar',
  	component: AvaliacaoComponent,
    canActivate: [AppAuthGuard]
  },
  {
  	path: 'avaliacao',
  	component: AvaliacaoListaComponent,
    canActivate: [AppAuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AppAuthGuard]
})
export class AppRoutingModule {}
