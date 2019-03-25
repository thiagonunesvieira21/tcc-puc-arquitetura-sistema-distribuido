package br.pucminas.logistica.config;

/**
 * Classe SecuritySettings que representa as definições de segurança
 * recuperadas das propriedades de sistema definidas no JBoss
 *
 */
public class SecuritySettings extends BaseSettings {
    
	public static final String REALM = "master";
	/**
     * url do servidor de autorização
     */
    private String urlServAuth;
    /**
     * tempo de vida do token (access token lifespan)
     */
    private Integer tempoVidaToken;
    /**
     * tempo máximo de idle permitido
     */
    private Integer tempoMaximoIdle;

    /**
     * Construtor padrão
     */
    public SecuritySettings() {
        urlServAuth = getSystemProperty("SEC_URL_SERV_AUTH");
        try {
            String valor = getSystemProperty("SEC_TEMPO_VIDA_TOKEN");
            tempoVidaToken = Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            tempoVidaToken = 5;
        }
        try {
            String valor = getSystemProperty("SEC_TEMPO_MAX_IDLE");
            tempoMaximoIdle = Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            tempoMaximoIdle = -1;
        }
    }

    /**
     * Obtém a url do servidor de autorização
     *
     * @return a url do servidor de autorização
     */
    public String getUrlServAuth() {
        return urlServAuth;
    }

    /**
     * Obtém o tempo de vida do (access) token
     *
     * @return o tempo de vida do (access) token
     */
    public Integer getTempoVidaToken() {
        return tempoVidaToken;
    }

    /**
     * Obtém o tempo máximo de idle permitido
     *
     * @return o tempo máximo de idle permitido
     */
    public Integer getTempoMaximoIdle() {
        return tempoMaximoIdle;
    }
}
