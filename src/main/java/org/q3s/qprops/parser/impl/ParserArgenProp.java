package org.q3s.qprops.parser.impl;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.q3s.qprops.model.PaginaParser;
import org.q3s.qprops.model.Publicacion;
import org.q3s.qprops.parser.Parser;

public class ParserArgenProp extends Parser {

	public ParserArgenProp(PaginaParser pp) throws Exception {
		super(pp);
	}

	public ParserArgenProp(String url, PaginaParser pp) {
		super(url,pp);
	}

	@Override
	public List<Publicacion> getAds() throws Exception{
		init();
		//------------------------------------
		List<Publicacion> ads = new ArrayList<Publicacion>();
		
//		//root
//		Elements parent = doc.select("ul.box-avisos-listado.clearfix");
//		Elements articles = parent.select(".avisoitem");
//		
//		for(Element elem : articles.toArray(new Element[]{})){
//			Ad ad = new Ad();
//			ad.setDescription(elem.select("p.subtitle").first().attr("title"));
//			Element locationAndLink = elem.select("h2.address>a[href]").first();
//			ad.setLocation(locationAndLink.attr("title"));
//			ad.setHost("http://www.argenprop.com");
//			ad.setLink(ad.getHost() + locationAndLink.attr("href"));
//			ad.setPrice(elem.select("p.price").first().text());
//			ad.setM2(elem.select("p.datocomun-valor-abbr").text());
//			ad.setEnvironments(elem.select("h3").first().attr("title"));
//			ads.add(ad);
//		}
//		
//		//de forma recursuva recorro todas las paginas
//		Elements pager = doc.select("li.paginado").select(".n>a[title$=Siguiente]");
//		//hasta no encontrar mas el boton siguiente
//		if(!pager.isEmpty()){
//			Parser parser = new ParserArgenProp(pager.attr("href"));
//			ads.addAll(parser.getAds());
//		}

		return ads;
	}
	
}