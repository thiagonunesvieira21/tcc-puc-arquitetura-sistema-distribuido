import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ClarityModule } from '@clr/angular';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent, SolicitacaoComponent, HomeComponent } from './components';
import { AppRoutingModule } from './app-routing.module';
import { AppAuthGuard } from './app.authguard';
import { initializer } from './utils/app-init';

import { SolicitacaoService } from './services/solicitacao/solicitacao.service';
import { ConcorrenteService } from './services/concorrente/concorrente.service';

@NgModule({
  declarations: [AppComponent, HomeComponent, SolicitacaoComponent],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ClarityModule,
    BrowserAnimationsModule,
    KeycloakAngularModule,
    AppRoutingModule
  ],
  providers: [
    SolicitacaoService,
    ConcorrenteService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
