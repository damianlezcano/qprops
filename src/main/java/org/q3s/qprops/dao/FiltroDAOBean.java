package org.q3s.qprops.dao;

import java.util.List;

import org.q3s.qprops.model.Filtro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FiltroDAOBean implements FiltroDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Override
	public void save(Filtro filtro) {
        jdbcTemplate.update(
                "insert into FILTROS (campo) values(?)",
              filtro.getCampo());
	}
	
	@Override
	public List<Filtro> findAll() {
        return jdbcTemplate.query("select * from FILTROS",
            (rs, rowNum) ->
                new Filtro(
                        rs.getString("CAMPO")
                )
        );
	}
	
}
