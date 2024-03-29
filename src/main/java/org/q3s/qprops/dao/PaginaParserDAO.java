package org.q3s.qprops.dao;

import java.util.List;

import org.q3s.qprops.model.PaginaParser;

public interface PaginaParserDAO {

	void save(PaginaParser pp);

	PaginaParser findById(int id);
	List<PaginaParser> findAll();

}
