package org.q3s.qprops.model;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Publicacion implements Serializable {

	private static final long serialVersionUID = -8839551958244070571L;

	private String uuid;
	private String descripcion;
	private Precio precio;
	private Precio precioAnterior;
	private int m2;
	private int ambientes;
	private String calle;
	private String barrio;
	private String ciudad;
	private String link;
	private String img;
	private String host;
	private Estado estado;
	private Date fechaCreacion;

	//-------------------------

	//UUID,AMBIENTES,BARRIO,CALLE,CIUDAD,DESCRIPCION,FECHA_CREACION,HOST,IMG,LINK,M2
	
	public Publicacion() {
		super();
	}
	
	public Publicacion(String uuid, int ambientes, String barrio, String calle, String ciudad, String descripcion, Date fechaCreacion, String host, String img, String link, int m2, String precioTipoMoneda, double precioValor) {
		super();
		this.uuid = uuid;
		this.descripcion = descripcion;
		this.m2 = m2;
		this.ambientes = ambientes;
		this.calle = calle;
		this.barrio = barrio;
		this.ciudad = ciudad;
		this.link = link;
		this.img = img;
		this.host = host;
		this.fechaCreacion = fechaCreacion;
		this.precio = new Precio(precioTipoMoneda,precioValor);
	}	
	
	public long diasDesdeFechaCreacion() {
	    long diff = (new Date().getTime() - fechaCreacion.getTime());
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	
	public boolean existeNota() {
		return (estado.getNotas() == null || (estado.getNotas() != null && estado.getNotas().isEmpty()));
	}
	
	public double calcularPrecio() {
		return precio.getValor();
	}
	
	public double porcentajeAumentoPrecio() {
		if(precio != null && precioAnterior != null) {
			double a = precioAnterior.getValor();
			double b = precio.getValor();
			if(a != 0d) {
				if(a < b) {
					return (1-(a / b))*100;
				} else {
					return (-(1-(b / a)))*100;
				}			
			}			
		}
		return 0d;
	}
	
	//-------------------------
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Precio getPrecio() {
		if(precio == null) {
			precio = new Precio();
		}
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	public Precio getPrecioAnterior() {
		if(precioAnterior == null) {
			precioAnterior = new Precio();
		}
		return precioAnterior;
	}

	public void setPrecioAnterior(Precio precioAnterior) {
		this.precioAnterior = precioAnterior;
	}

	public int getM2() {
		return m2;
	}

	public void setM2(int m2) {
		this.m2 = m2;
	}

	public int getAmbientes() {
		return ambientes;
	}

	public void setAmbientes(int ambientes) {
		this.ambientes = ambientes;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		String text = "";
		if(ciudad != null) {
			text+= ciudad + " - ";
		}
		if(barrio != null) {
			text+= barrio + " - ";
		}
		
		return text + calle;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String value) {
		this.host = value;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Estado getEstado() {
		if(estado == null) {
			estado = new Estado();
		}
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Publicacion) {
        	Publicacion ad = (Publicacion) obj;
        	if(ad.getUuid() == null || this.getUuid() == null){
        		return false;
        	}else{
        		return (ad.getUuid().equals(this.getUuid()));
        	}
        }
        return false;
    }
	
	@Override
	public int hashCode(){
		return 0;
	}
	
	@Override
	public String toString() {
		return this.getDescripcion() + getUbicacion();
	}

}