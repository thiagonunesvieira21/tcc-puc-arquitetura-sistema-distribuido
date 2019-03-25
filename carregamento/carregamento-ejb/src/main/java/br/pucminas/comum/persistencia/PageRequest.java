package br.pucminas.comum.persistencia;

import java.io.Serializable;

public class PageRequest implements Serializable {
	private static final long serialVersionUID = 8803252196704616563L;
	
	private int pageNumber;
    private int pageSize;

    public PageRequest() {

    }

    public PageRequest(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
