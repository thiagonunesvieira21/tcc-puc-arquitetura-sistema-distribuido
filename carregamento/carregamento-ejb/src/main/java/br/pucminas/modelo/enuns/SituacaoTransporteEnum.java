package br.pucminas.modelo.enuns;

public enum SituacaoTransporteEnum {
	PENDENTE(1),
	COLETANDO(2),
	COLETADA(3),
	ACAMINHO(4),
	ENTREGUE(5),
	TRANSFERIDA(6);
	
	public int value;
	
	private SituacaoTransporteEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
