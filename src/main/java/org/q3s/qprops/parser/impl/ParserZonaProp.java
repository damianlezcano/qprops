package org.q3s.qprops.parser.impl;

import java.util.ArrayList;
import java.util.List;

import org.q3s.qprops.model.Publicacion;
import org.q3s.qprops.parser.Parser;

public class ParserZonaProp extends Parser {

	public ParserZonaProp(String url) throws Exception {
		super(url);
	}

	@Override
	public List<Publicacion> getAds() throws Exception{
		init();
		//------------------------------------
		List<Publicacion> ads = new ArrayList<Publicacion>();
		
//		//root
//		Elements parent = doc.select("ul.list-posts.unstyled.clearfix");
//		Elements articles = parent.select(".post");
//		
//		for(Element elem : articles.toArray(new Element[]{})){
//			Ad ad = new Ad();
//			Element descriptionAndLink = elem.select("div.post-text>div.post-text-desc>div>h4.post-title>a[href]").first();
//			ad.setDescription(descriptionAndLink.text());
//			ad.setLocation("-");
//			ad.setHost("http://www.zonaprop.com.ar");
//			ad.setLink(ad.getHost() + descriptionAndLink.attr("href"));
//			ad.setPrice(elem.select("p.price").first().text());
//			Elements m2AndEnv = elem.select("ul.misc.unstyled");
//			ad.setM2(m2AndEnv.select("li.misc-m2cubiertos").text());
//			ad.setEnvironments(m2AndEnv.select("li:not(.misc-m2cubiertos)").text());
//			ads.add(ad);
//		}
//		
//		//de forma recursuva recorro todas las paginas
//		Elements pager = doc.select("div.pagination.pagination-centered>ul>li>a[rel$=next]");
//		//hasta no encontrar mas el boton siguiente
//		if(!pager.isEmpty() && !pager.attr("href").equals("#")){
//			Parser parser = new ParserZonaProp(pager.attr("href"));
//			ads.addAll(parser.getAds());
//		}

		return ads;
	}
	
}