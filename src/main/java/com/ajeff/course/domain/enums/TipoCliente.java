package com.ajeff.course.domain.enums;

public enum TipoCliente {

	PESSOAFISICA (1, "Pessoa Física"),
	PESSOAJURIDICA (2, "Pessoa Jurídica");
	
	private int codigo;
	private String descricao;
	
	private TipoCliente (Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return this.codigo;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		if (codigo == null) {
			return null;
		}
		for(TipoCliente t : TipoCliente.values()) {
			if(codigo.equals(t.getCodigo())) {
				return t;
			}
		}
		throw new IllegalArgumentException("Código inválido: " + codigo);
	}
}
