package org.q3s.qprops.dao;

import java.util.List;

import org.q3s.qprops.model.Filtro;

public interface FiltroDAO {

	void save(Filtro filtro);

	List<Filtro> findAll();

}
