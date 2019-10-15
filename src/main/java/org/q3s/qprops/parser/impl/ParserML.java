package org.q3s.qprops.parser.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.q3s.qprops.model.Precio;
import org.q3s.qprops.model.Publicacion;
import org.q3s.qprops.parser.Parser;

public class ParserML extends Parser {

	public ParserML(String url, String tipoBusqueda) throws Exception {
		super(url,tipoBusqueda);
	}

	@Override
	public List<Publicacion> getAds() throws Exception{
		init();
		//------------------------------------
		List<Publicacion> ads = new ArrayList<Publicacion>();
		
		//root
		Elements parent = doc.select("ol[id$=searchResults]");
		Elements articles = parent.select("li.results-item");
		
		for(Element elem : articles.toArray(new Element[]{})){
			Publicacion ad = new Publicacion();
			
			buildDescripcion(ad, elem);
			buildImg(ad, elem);
			buildUbicacion(ad,elem);
			buildLink(ad,elem);
			buildPrecio(ad,elem);
			buildM2Env(ad,elem);
			
			ad.setHost(tipoBusqueda + " / " + "mercadolibre");
			ad.setFechaCreacion(new Date());
			ad.setUuid(generateUuid(ad));
			
			ads.add(ad);
		}
		
		//de forma recursuva recorro todas las paginas
		Elements pager = doc.select("div.pagination__container").select("li.andes-pagination__button--next");
		//hasta no encontrar mas el boton siguiente
		if(!pager.isEmpty()){
			Parser parser = new ParserML(pager.select("a.andes-pagination__link").attr("href"), tipoBusqueda);
			ads.addAll(parser.getAds());
		}

		return ads;
	}

	private void buildM2Env(Publicacion ad, Element elem) {
		String m2Env = elem.select("div.item__info div.item__attrs").text(); 
		try {
			String[] split = m2Env.split("\\|");
			ad.setM2(buildM2(split[0]));
			ad.setAmbientes(buildAmbientes(split[1]));
		} catch (Exception e) {
			try {
				ad.setM2(0);ad.setAmbientes(0);//valor por default
				if(m2Env.contains("m²") || m2Env.contains("cub") || m2Env.contains("tot")) {
					ad.setM2(buildM2(m2Env));
				}
				if(m2Env.contains("amb")){
					ad.setAmbientes(buildAmbientes(m2Env));
				}				
			} catch (Exception e2) {
				System.err.println("No se puede parsear los M2 y Ambientes para -> " + m2Env);
			}
		}
	}

	private Integer buildM2(String text) {
		return Integer.valueOf(text.replaceAll("m²", "").replaceAll("cubiertos", "").replaceAll("totales", "").trim());
	}
	
	private Integer buildAmbientes(String text) {
		return Integer.valueOf(text.replaceAll("ambs.", "").replaceAll("dorms.", "").replaceAll("amb.", "").replaceAll("dorm.", "").trim());
	}
	
	private void buildPrecio(Publicacion ad, Element elem) {
		try {
			String price__symbol = elem.select("div.item__info div.item__price  span.price__symbol").text();
			String price__fraction = elem.select("div.item__info div.item__price  span.price__fraction").text();
			Precio precio = new Precio(price__symbol, Double.valueOf(price__fraction.replace(".", "")));
			ad.setPrecio(precio);			
		} catch (Exception e) {
			System.err.println("No se puede parsear el precio para " + ad);
			ad.setPrecio(new Precio("S/N",0d));
		}
	}

	private void buildLink(Publicacion ad,Element elem) {
		ad.setLink(elem.select("div.rowItem a").attr("href"));
	}

	private void buildUbicacion(Publicacion ad, Element elem) {
		String ubicacion = elem.select("div.item__info div.item__title").text();
		try {
			String b = " - ";
			String ciudad = ubicacion.substring(ubicacion.lastIndexOf(b));
			ubicacion = ubicacion.substring(0,ubicacion.length() - ciudad.length());
			String barrio = ubicacion.substring(ubicacion.lastIndexOf(b));
			String calle = ubicacion.substring(0,ubicacion.length() - barrio.length());;
			ad.setCalle(calle);
			ad.setBarrio(barrio.replaceFirst(b, ""));
			ad.setCiudad(ciudad.replaceFirst(b, ""));			
		} catch (Exception e) {
			System.err.println("No se puede parsear la ubicacion para -> " + ubicacion);
			ad.setCalle(ubicacion);
		}
	}

	private void buildDescripcion(Publicacion ad, Element elem) {
		Elements t = elem.select("div.item__image div.carousel img");
		ad.setDescripcion(t.attr("alt"));
	}

	private void buildImg(Publicacion ad, Element elem) {
		Elements t = elem.select("div.item__image div.carousel img");
		ad.setImg(t.attr("src"));

		if(ad.getImg().isEmpty()) {
			ad.setImg(t.attr("data-src"));
		}
	}
	
}