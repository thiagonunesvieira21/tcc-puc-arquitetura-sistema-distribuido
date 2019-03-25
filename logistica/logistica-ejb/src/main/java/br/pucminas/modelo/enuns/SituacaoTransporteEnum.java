package br.pucminas.modelo.enuns;

public enum SituacaoTransporteEnum {
	PENDENTE(1, "Pedente"),
	COLETANDO(2, "Coletando"),
	COLETADA(3, "Coletada"),
	ACAMINHO(4, "Postada para Entrega"),
	ENTREGUE(5, "Entrege"),
	TRANSFERIDA(6, "Transferida");
	
	public int value;
	public String desc;
	
	private SituacaoTransporteEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public int getValue() {
		return value;
	}

	public String getDesc() {
		return desc;
	}
	
	public static String getDes(int value) {
		String desc = null;
		
		for(SituacaoTransporteEnum e : SituacaoTransporteEnum.values()) {
			if(value==e.value) {
				desc = e.desc;
			}
		}
		
		return desc;
	}
}