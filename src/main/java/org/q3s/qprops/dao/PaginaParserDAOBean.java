package org.q3s.qprops.dao;

import java.util.List;

import org.q3s.qprops.model.PaginaParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaginaParserDAOBean implements PaginaParserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Override
	public void save(PaginaParser pp) {
        jdbcTemplate.update(
                "insert into PAGINAS_PARSER (PAGINA,PARSER,DESCRIPCICON) values(?,?)",
              pp.getPagina(),
              pp.getParser(),
              pp.getTipoBusqueda());
	}
	
	@Override
	public List<PaginaParser> findAll() {
        return jdbcTemplate.query("select * from PAGINAS_PARSER",
            (rs, rowNum) ->
                new PaginaParser(
                        rs.getString("PAGINA"),
                        rs.getString("PARSER"),
                        rs.getString("DESCRIPCION")
                )
        );
	}
	
}
