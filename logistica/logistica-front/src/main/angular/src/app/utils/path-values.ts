import { environment } from '../../environments';

const dotaApi = environment.apis.dota;
const solicitacaoApi = `${dotaApi}/logistica-web-api/api/avaliacao/solicitacao/consultar`;
const avaliacaoApi  = `${dotaApi}/logistica-web-api/api/avaliacao`;

const pathValues = {
  dotaApi,
  solicitacaoApi,
  avaliacaoApi
};

export { pathValues };
