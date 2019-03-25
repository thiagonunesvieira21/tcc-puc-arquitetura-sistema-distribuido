package br.pucminas.logistica.config;

import javax.enterprise.context.ApplicationScoped;

/**
 * Classe BaseSettings que representa um conjunto de definições
 * a respeito de algum assunto
 *
 * @author pablo.cast
 */
@ApplicationScoped
public abstract class BaseSettings {
    /**
     * Recupera uma propriedade de sistema (possivelmente configurada
     * no container JBoss)
     *
     * @return a propriedade
     */
    protected static String getSystemProperty(String propertyName) {
        String res = System.getProperty(AppSettings.NOME_APLICACAO + "-" + propertyName);
        if (res == null)
            return "";

        return res;
    }
}
