package br.pucminas.logistica.config;

/**
 * Classe AppSettings que representa as definições do sistema
 *
 * @author pablo.cast
 */
public class AppSettings {


    private AppSettings() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * o nome da aplicação ou do projeto
     */
    public static final String NOME_APLICACAO = "SISLOG";

    /**
     * o numero da versão da aplicação
     */
    public static final String VERSAO_APLICACAO = "0.0.0.1";
}
