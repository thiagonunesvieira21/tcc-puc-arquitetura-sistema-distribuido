package br.pucminas.comum.persistencia;

import java.io.Serializable;
import java.util.List;

public class Pageable implements Serializable {
	private static final long serialVersionUID = 1460912827685640699L;

	private Long total;

    private List<?> dados;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getDados() {
        return dados;
    }

    public void setDados(List<?> dados) {
        this.dados = dados;
    }
}
