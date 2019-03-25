import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ClarityModule } from '@clr/angular';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent, TarifaFreteComponent, HomeComponent, TarifaFreteListaComponent, SimularTarifaFreteListaComponent } from './components';
import { AppRoutingModule } from './app-routing.module';
import { AppAuthGuard } from './app.authguard';
import { initializer } from './utils/app-init';

import { TarifaService } from './services/tarifa/tarifa.service';

@NgModule({
  declarations: [AppComponent, TarifaFreteComponent, HomeComponent, TarifaFreteListaComponent, SimularTarifaFreteListaComponent],
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
    TarifaService,
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
