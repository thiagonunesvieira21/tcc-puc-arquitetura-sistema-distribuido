import { KeycloakConfig } from 'keycloak-angular';

// Add here your keycloak setup infos
let keycloakConfig: KeycloakConfig = {
  url: 'http://10.0.75.2:8080/auth/',
  realm: 'master',
  clientId: 'cli-web-log'
};

export const environment = {
  production: true,
  assets: {
    dotaImages:
      'https://cdn-keycloak-angular.herokuapp.com/assets/images/dota-heroes/'
  },
  apis: { dota: 'http://10.0.75.2' },
  keycloak: keycloakConfig
};
