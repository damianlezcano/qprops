package org.q3s.qprops.dao;

import java.util.Optional;

import org.q3s.qprops.model.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstadoDAOBean implements EstadoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Override
	public void save(Estado estado) {
        jdbcTemplate.update(
                "insert into ESTADO (UUID,NOTAS,TIPO) values(?,?,?)",
              estado.getUuid(),
              estado.getNotas(),
              estado.getTipo());
	}

	@Override
	public void update(Estado estado) {
        jdbcTemplate.update(
                "update ESTADO set NOTAS=?,TIPO=? WHERE UUID=?",
              estado.getNotas(),
              estado.getTipo(),
              estado.getUuid());
	}
	
	@Override
	public Estado findById(String id) {
		try {
			return jdbcTemplate.queryForObject(
					"select * from ESTADO where uuid = ?",
					new Object[]{id}, (rs, rowNum) ->
					Optional.of(new Estado(
							rs.getString("UUID"),
							rs.getString("NOTAS"),
							rs.getString("TIPO")
							))
					).get();			
		} catch (Exception e) {
			return null;
		}
	}
	
}
