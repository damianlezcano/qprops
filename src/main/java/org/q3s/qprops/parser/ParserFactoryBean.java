package org.q3s.qprops.parser;

import org.q3s.qprops.model.PaginaParser;
import org.q3s.qprops.parser.impl.ParserML;

public class ParserFactoryBean {

	public static Parser get(PaginaParser pp) throws Exception {
		if("mercadolibre".equalsIgnoreCase(pp.getParser())) {
			return new ParserML(pp.getPagina(),pp.getTipoBusqueda());
		}
		return null;
	}
	
}
