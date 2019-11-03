package org.q3s.qprops.model;

import java.io.Serializable;

public class PaginaParser implements Serializable {

	private static final long serialVersionUID = -526854602934646523L;
	
	private int id;
	private String pagina;
	private String parser;
	private String descripcion;
	
	public PaginaParser(int id, String pagina, String parser,String descripcion) {
		this.id = id;
		this.pagina = pagina;
		this.parser = parser;
		this.descripcion = descripcion;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return descripcion + " / " + parser;
	}
	
}