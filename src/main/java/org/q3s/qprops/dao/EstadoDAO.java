package org.q3s.qprops.dao;

import org.q3s.qprops.model.Estado;

public interface EstadoDAO {

	void save(Estado estado);

	void update(Estado estado);

	Estado findById(String id);

}
