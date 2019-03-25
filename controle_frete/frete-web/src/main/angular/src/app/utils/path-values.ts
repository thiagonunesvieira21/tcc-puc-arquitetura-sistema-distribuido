import { environment } from '../../environments';

const dotaApi = environment.apis.dota;
const tarifaFreteApi = `${dotaApi}/frete-api/api/tarifa`;
const tarifaFreteSimularApi = `${dotaApi}/frete-api/api/tarifa/simular`;
const ajustarTarifaFreteApi = `${dotaApi}/frete-api/api/tarifa/reajustar`;
const pathValues = {
  dotaApi,
  tarifaFreteApi,
  tarifaFreteSimularApi,
  ajustarTarifaFreteApi
};

export { pathValues };
