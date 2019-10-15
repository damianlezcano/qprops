package org.q3s.qprops.dao;

import java.util.List;
import java.util.Optional;

import org.q3s.qprops.model.Publicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PublicacionDAOBean implements PublicacionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public void save(Publicacion publicacion) {
        jdbcTemplate.update(
                "insert into PUBLICACION "
              + "(UUID,AMBIENTES,BARRIO,CALLE,CIUDAD,DESCRIPCION,FECHA_CREACION,HOST,IMG,LINK,M2,PRECIO_TIPO_MONEDA,PRECIO_VALOR) "
              + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
              publicacion.getUuid(),
              publicacion.getAmbientes(),
              publicacion.getBarrio(),
              publicacion.getCalle(),
              publicacion.getCiudad(),
              publicacion.getDescripcion(),
              publicacion.getFechaCreacion(),
              publicacion.getHost(),
              publicacion.getImg(),
              publicacion.getLink(),
              publicacion.getM2(),
              publicacion.getPrecio().getTipoMoneda(),
              publicacion.getPrecio().getValor());
	}
	
	@Override
	public List<Publicacion> findAll() {
        return jdbcTemplate.query("select * from PUBLICACION",
                (rs, rowNum) ->
                        new Publicacion(
                                rs.getString("UUID"),
                                rs.getInt("AMBIENTES"),
                                rs.getString("BARRIO"),
                                rs.getString("CALLE"),
                                rs.getString("CIUDAD"),
                                rs.getString("DESCRIPCION"),
                                rs.getDate("FECHA_CREACION"),
                                rs.getString("HOST"),
                                rs.getString("IMG"),
                                rs.getString("LINK"),
                                rs.getInt("M2"),
                                rs.getString("PRECIO_TIPO_MONEDA"),
                                rs.getDouble("PRECIO_VALOR")
                        )
        );
	}

	@Override
	public Publicacion findById(String id) {
		try {
			return jdbcTemplate.query(
					"select * from PUBLICACION where uuid = ?",
					new Object[]{id}, (rs, rowNum) ->
					Optional.of(new Publicacion(
							rs.getString("UUID"),
							rs.getInt("AMBIENTES"),
							rs.getString("BARRIO"),
							rs.getString("CALLE"),
							rs.getString("CIUDAD"),
							rs.getString("DESCRIPCION"),
							rs.getDate("FECHA_CREACION"),
							rs.getString("HOST"),
							rs.getString("IMG"),
							rs.getString("LINK"),
							rs.getInt("M2"),
							rs.getString("PRECIO_TIPO_MONEDA"),
							rs.getDouble("PRECIO_VALOR")
							))
					).get(0).get();			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
