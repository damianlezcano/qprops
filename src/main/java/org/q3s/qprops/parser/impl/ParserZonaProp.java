package org.q3s.qprops.parser.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.q3s.qprops.model.PaginaParser;
import org.q3s.qprops.model.Precio;
import org.q3s.qprops.model.Publicacion;
import org.q3s.qprops.parser.Parser;

public class ParserZonaProp extends Parser {

	private static String HOST = "https://www.zonaprop.com.ar";
	
	public ParserZonaProp(PaginaParser pp) throws Exception {
		super(pp);
	}

	public ParserZonaProp(String url, PaginaParser pp) {
		super(url,pp);
	}

	@Override
	public List<Publicacion> getAds() throws Exception{
		init();
		//------------------------------------
		List<Publicacion> ads = new ArrayList<Publicacion>();
		
		//root		
		Elements parent = doc.select("div.list-card-container");
		Elements articles = parent.select(".posting-card");
		
		for(Element elem : articles.toArray(new Element[]{})){
			Publicacion ad = new Publicacion();
			
			buildDescripcion(ad, elem);
			buildImg(ad, elem);
			buildUbicacion(ad,elem);
			buildLink(ad,elem);
			buildPrecio(ad,elem);
			buildM2(ad,elem);
			buildAmbientes(ad,elem);
			buifFechaCreacion(ad);
			buildUuid(ad);
			buildPaginaParser(ad);
			
			ads.add(ad);
		}
		
		//de forma recursuva recorro todas las paginas
		Elements pager = doc.select("div.paging li.pag-go-next a");
		if(!pager.isEmpty()){
			String next = buildURL(pager.attr("href"));
			Parser parser = new ParserZonaProp(next,pp);
			ads.addAll(parser.getAds());
		}

		return ads;
	}

	private void buildUuid(Publicacion ad) {
		ad.setUuid(generateUuid(ad));
	}

	private void buildPaginaParser(Publicacion ad) {
		ad.setPaginaParserId(pp.getId());
	}

	private void buifFechaCreacion(Publicacion ad) {
		ad.setFechaCreacion(new Date());
	}

	private void buildAmbientes(Publicacion ad, Element elem) {
		try {
			Elements t = elem.select("div.gallery-placeholder img");
			String[] alt = t.attr("alt").split(" ");
			int idx = buscarNAmbientes(alt);
			ad.setAmbientes(Integer.valueOf(alt[idx]));
		} catch (Exception e1) {
			try {
				Elements t = elem.select("div.posting-card");
				String[] data = t.attr("data-to-posting").replaceAll("/", "-").split("-");
				int idx = buscarNAmbientes(data);
				ad.setAmbientes(Integer.valueOf(data[idx]));
			} catch (Exception e2) {
				try {
					Elements t = elem.select("h3.posting-title");
					String[] title = t.text().split(" ");
					int idx = buscarNAmbientes(title);
					ad.setAmbientes(Integer.valueOf(title[idx]));
				} catch (Exception e3) {
					try {
						Elements t = elem.select("div.posting-description");
						String[] descripcion = t.text().split(" ");
						int idx = buscarNAmbientes(descripcion);
						ad.setAmbientes(Integer.valueOf(descripcion[idx]));
					} catch (Exception e4) {
						try {
							Elements t = elem.select("img.lazy-loading");
							String[] lazy = t.attr("alt").split("-");
							int idx = buscarNAmbientes(lazy);
							ad.setAmbientes(Integer.valueOf(lazy[idx]));
						} catch (Exception e5) {
							System.err.println("No se puede parsear los ambientes " + ad);
							ad.setAmbientes(0);
						}
					}
				}
			}
		}
	}

	private int buscarNAmbientes(String[] datos) {
		int idx = 0;
		for (int i = 0; i < datos.length; i++) {
			if(datos[i].toLowerCase().indexOf("ambiente") != -1) {
				idx = i - 1;
				break;
			}
		}
		return idx;
	}

	private void buildM2(Publicacion ad, Element elem) {
		try {
			String cubierto = "cubierto";
			Elements li = elem.select("div.posting-info ul.main-features li");
			String m2 = null;
			for (Element eLi : li) {
				if(eLi.text().indexOf(cubierto) != -1) {
					m2 = eLi.text().replaceAll("cubiertos","").replaceAll(cubierto,"").replaceAll("m²", "").trim();
					break;
				}
			}
			ad.setM2(Integer.valueOf(m2));			
		} catch (Exception e) {
			System.err.println("No se puede parsear los m2 " + ad);
			ad.setM2(0);
		}
	}

	private void buildPrecio(Publicacion ad, Element elem) {
		try {
			String price[] = elem.select("div.prices span.first-price").text().split(" ");
			String price_symbol = price[0];
			String price_fraction = price[1];
			Precio precio = new Precio(price_symbol, Double.valueOf(price_fraction.replace(".", "")));
			ad.setPrecio(precio);
		} catch (Exception e) {
			System.err.println("No se puede parsear el precio para " + ad);
			ad.setPrecio(new Precio("S/N",0d));
		}
	}

	private void buildLink(Publicacion ad, Element elem) {
		String link = buildURL(elem.select("a.go-to-posting").attr("href"));
		ad.setLink(link);
	}
	
	private String buildURL(String url) {
		return HOST + url;
	}

	private void buildUbicacion(Publicacion ad, Element elem) {
		String ubicacion = elem.select("span.posting-location").text();
		try {
			String[] bc = ubicacion.split(",");
			String calle = bc[0];
			String barrio = bc[1];
			String ciudad = bc[2];
			ad.setCalle(calle.replaceFirst(" ", ""));
			ad.setBarrio(barrio.replaceFirst(" ", ""));
			ad.setCiudad(ciudad.replaceFirst(" ", ""));
		} catch (Exception e) {
			System.err.println("No se puede parsear la ubicacion para -> " + ubicacion);
			ad.setCalle(ubicacion);
		}
	}

	private void buildImg(Publicacion ad, Element elem) {
		Elements t1 = elem.select("div.gallery-placeholder");
		Elements t2 = t1.select("img");
		String dataSrc = t2.attr("src");
		if(dataSrc.startsWith("data:image")) {
			ad.setImg(t2.attr("data-src"));
		}else{
			ad.setImg(t2.attr("src"));
		}
	}

	private void buildDescripcion(Publicacion ad, Element elem) {
		Elements t = elem.select("h3.posting-title");
		ad.setDescripcion(t.text());
	}
	
}