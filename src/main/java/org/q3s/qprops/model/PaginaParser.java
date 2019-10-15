package org.q3s.qprops.model;

import java.io.Serializable;

public class PaginaParser implements Serializable {

	private static final long serialVersionUID = -526854602934646523L;
	
	private String pagina;
	private String parser;
	private String tipoBusqueda;
	
	public PaginaParser(String pagina, String parser) {
		this.pagina = pagina;
		this.parser = parser;
	}
	public PaginaParser(String pagina, String parser,String tipoBusqueda) {
		this.pagina = pagina;
		this.parser = parser;
		this.setTipoBusqueda(tipoBusqueda);
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
	public String getParser() {
		return parser;
	}
	public void setParser(String parser) {
		this.parser = parser;
	}
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	
}