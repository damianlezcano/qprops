package org.q3s.qprops.parser;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.q3s.qprops.model.Publicacion;

public abstract class Parser {
	
	protected String tipoBusqueda;
	protected String url;
	
	protected Document doc;
	
	public Parser(String url) throws UnsupportedEncodingException{
		this.url = url;
	}

	public Parser(String url, String tipoBusqueda) throws UnsupportedEncodingException{
		this(url);
		this.tipoBusqueda = tipoBusqueda;
	}
	
	protected void init() throws Exception {
		doc = Jsoup
				.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36")
//				.ignoreContentType(true)
				.timeout(10000)
				.get();
	}
	
	public static String generateUuid(Publicacion pub){
		String id = pub.getM2() + pub.getAmbientes() + pub.getCiudad() + pub.getCalle();
		try {
			return Base64.getEncoder().encodeToString(id.toLowerCase().getBytes());
		} catch (Exception e) {
			return id;
		}
	}

	public abstract List<Publicacion> getAds() throws Exception;
}