package org.q3s.qprops.model;

import java.io.Serializable;

public class Filtro implements Serializable {

	private static final long serialVersionUID = 8197032789146392258L;
	
	private String campo;

	public Filtro(String campo) {
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

}
