package org.q3s.qprops.parser;

import org.q3s.qprops.model.PaginaParser;
import org.q3s.qprops.parser.impl.ParserML;
import org.q3s.qprops.parser.impl.ParserZonaProp;

public class ParserFactoryBean {

	public static Parser get(PaginaParser parser) throws Exception {
		if("mercadolibre".equalsIgnoreCase(parser.getParser())) {
			return new ParserML(parser);
		}else if("zonaprop".equalsIgnoreCase(parser.getParser())) {
			return new ParserZonaProp(parser);
		}
		return null;
	}
	
}
