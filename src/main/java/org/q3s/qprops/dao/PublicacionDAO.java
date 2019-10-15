package org.q3s.qprops.dao;

import java.util.List;

import org.q3s.qprops.model.Publicacion;

public interface PublicacionDAO {

	void save(Publicacion publicacion);

	List<Publicacion> findAll();

	Publicacion findById(String id);

}
