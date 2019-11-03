package org.q3s.qprops.services;

import java.util.List;

import org.q3s.qprops.model.Publicacion;
import org.springframework.scheduling.annotation.Async;

public interface PropService {

	void save(Publicacion publicacion);

	List<Publicacion> findAll();

	Publicacion findById(String id);

	void updateStatus(String id, String estado);

	void updateNote(String id, String nota);

	@Async
	void process() throws Exception;

}
