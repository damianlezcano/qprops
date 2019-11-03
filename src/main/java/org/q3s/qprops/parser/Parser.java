package org.q3s.qprops.parser;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.q3s.qprops.model.PaginaParser;
import org.q3s.qprops.model.Publicacion;

public abstract class Parser {
	
	protected PaginaParser pp;
	protected String url;
	
	protected Document doc;
	
	public Parser(String url, PaginaParser pp) {
		this.url = url;
		this.pp = pp;
	}
	
	public Parser(PaginaParser pp) throws UnsupportedEncodingException{
		this(pp.getPagina(),pp);
	}

	protected void init() throws Exception {
		doc = Jsoup
			.connect(url)
			.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
//				.ignoreContentType(true)
			.timeout(20000)
			.get();
	}
	
	protected String generateUuid(Publicacion pub){
		String id = pub.getM2() + pub.getAmbientes() + pub.getCiudad() + pub.getCalle();
		try {
			return Base64.getEncoder().encodeToString(id.toLowerCase().getBytes());
		} catch (Exception e) {
			return id;
		}
	}
	
	public PaginaParser getPaginaParser() {
		return pp;
	}
	
	public abstract List<Publicacion> getAds() throws Exception;
}