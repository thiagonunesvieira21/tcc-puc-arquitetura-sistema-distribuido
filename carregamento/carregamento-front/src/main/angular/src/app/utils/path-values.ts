import { environment } from '../../environments';

const dotaApi = environment.apis.dota;
const solicitacaoApi = `${dotaApi}/carregamento-web-api/api/solicitacao/consultar`;
const transferirApi  = `${dotaApi}/carregamento-web-api/api/solicitacao/transferir`;
const concorrenteApi = `${dotaApi}/carregamento-web-api/api/concorrente/consultar`;

const pathValues = {
  dotaApi,
  solicitacaoApi,
  concorrenteApi,
  transferirApi
};

export { pathValues };
