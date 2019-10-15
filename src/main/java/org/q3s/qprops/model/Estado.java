package org.q3s.qprops.model;

import java.io.Serializable;

public class Estado implements Serializable {

	private static final long serialVersionUID = 860156406906034626L;

	private String uuid;
	private String notas;
	private String tipo;

	//-------------------------
	
	public Estado() {
		super();
	}
	
	public Estado(String uuid, String notas, String tipo) {
		super();
		this.uuid = uuid;
		this.notas = notas;
		this.tipo = tipo;
	}
	
	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}