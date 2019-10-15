package org.q3s.qprops.services;

import java.util.List;

import org.q3s.qprops.model.Publicacion;

public interface PropService {

	void save(Publicacion publicacion);

	List<Publicacion> findAll();

	Publicacion findById(String id);

	void updateStatus(String id, String estado);

	void updateNote(String id, String nota);

	void process() throws Exception;

}
