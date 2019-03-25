import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ClarityModule } from '@clr/angular';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent, AvaliacaoComponent, AvaliacaoListaComponent, HomeComponent } from './components';
import { AppRoutingModule } from './app-routing.module';
import { AppAuthGuard } from './app.authguard';
import { initializer } from './utils/app-init';

import { SolicitacaoService } from './services/solicitacao/solicitacao.service';
import { AvaliacaoService } from './services/avaliacao/avaliacao.service';

@NgModule({
  declarations: [AppComponent, HomeComponent, AvaliacaoComponent, AvaliacaoListaComponent],
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
    AvaliacaoService,
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
