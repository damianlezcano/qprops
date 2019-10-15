package org.q3s.qprops.model;

import java.io.Serializable;

public class Precio implements Serializable {

	private static final long serialVersionUID = -5356663773591219134L;

	private String tipoMoneda;
	private double valor;
	
	//-------------------------
	
	public Precio(String tipoMoneda, double valor) {
		super();
		this.tipoMoneda = tipoMoneda;
		this.valor = valor;
	}
	public Precio() {
		super();
	}
	public String getTipoMoneda() {
		return tipoMoneda;
	}
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return tipoMoneda + valor;
	}
}