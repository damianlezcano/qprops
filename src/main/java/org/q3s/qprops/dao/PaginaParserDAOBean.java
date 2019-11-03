package org.q3s.qprops.dao;

import java.util.List;
import java.util.Optional;

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
          "insert into PAGINAS_PARSER (PAGINA,PARSER,DESCRIPCICON) values(?,?,?)",
          pp.getPagina(),
          pp.getParser(),
          pp.getDescripcion());
	}
	
	@Override
	public List<PaginaParser> findAll() {
        return jdbcTemplate.query("select * from PAGINAS_PARSER",
            (rs, rowNum) ->
                new PaginaParser(
            		rs.getInt("ID"),
                    rs.getString("PAGINA"),
                    rs.getString("PARSER"),
                    rs.getString("DESCRIPCION")
                )
        );
	}

	@Override
	public PaginaParser findById(int id) {
		try {
			return jdbcTemplate.query(
				"select * from PAGINAS_PARSER where uuid = ?",
				new Object[]{id}, (rs, rowNum) ->
				Optional.of(new PaginaParser(
            		rs.getInt("ID"),
                    rs.getString("PAGINA"),
                    rs.getString("PARSER"),
                    rs.getString("DESCRIPCION")
					))
				).get(0).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
