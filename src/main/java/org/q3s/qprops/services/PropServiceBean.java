package org.q3s.qprops.services;

import java.util.ArrayList;
import java.util.List;

import org.q3s.qprops.dao.EstadoDAO;
import org.q3s.qprops.dao.FiltroDAO;
import org.q3s.qprops.dao.PaginaParserDAO;
import org.q3s.qprops.dao.PublicacionDAO;
import org.q3s.qprops.model.Estado;
import org.q3s.qprops.model.Filtro;
import org.q3s.qprops.model.PaginaParser;
import org.q3s.qprops.model.Publicacion;
import org.q3s.qprops.parser.Parser;
import org.q3s.qprops.parser.ParserFactoryBean;
import org.q3s.qprops.utils.LoggerUtils;

public class PropServiceBean implements PropService {

	private EstadoDAO estadoDAO;
	private FiltroDAO filtroDAO;
	private PublicacionDAO publicacionDAO;
	private PaginaParserDAO paginaParserDAO;

	@Override
	public void save(Publicacion publicacion) {
		publicacionDAO.save(publicacion);
	}

	@Override
	public List<Publicacion> findAll() {
		List<Publicacion> nlist = new ArrayList<Publicacion>();
		List<Publicacion> rlist = publicacionDAO.findAll();
		LoggerUtils.info("Recuperando %s publicaciones de la base", rlist.size());
		for (Publicacion p : rlist) {
			String uuid = p.getUuid();
			Estado e = estadoDAO.findById(uuid);
			p.setEstado(e);
			int i = nlist.indexOf(p);
			if(i != -1){
				Publicacion o = nlist.get(i);
				nlist.remove(o);
				p.setPrecioAnterior(o.getPrecio());
				nlist.add(p);
			}else{
				nlist.add(p);
			}
		}
		LoggerUtils.info("Devolvemos %s publicaciones", nlist.size());
		return nlist;
	}

	@Override
	public Publicacion findById(String id) {
		Publicacion pub = publicacionDAO.findById(id);
		Estado estado = estadoDAO.findById(id);
		pub.setEstado(estado);
		return pub;
	}

	@Override
	public void updateStatus(String id, String tipo) {
		Estado estado = estadoDAO.findById(id);
		if(estado == null) {
			estado = new Estado(id,null,tipo);
			estadoDAO.save(estado);
		}else{
			estado.setTipo(tipo);
			estadoDAO.update(estado);
		}
	}

	@Override
	public void updateNote(String id, String nota) {
		Estado estado = estadoDAO.findById(id);
		if(estado == null) {
			estado = new Estado(id,nota,null);
			estadoDAO.save(estado);
		}else{
			estado.setNotas(nota);
			estadoDAO.update(estado);
		}
	}
	
	@Override
	public void process() throws Exception {

		List<Parser> listParser = new ArrayList<Parser>();
		for (PaginaParser paginaParser : paginaParserDAO.findAll()) {
			Parser parser = ParserFactoryBean.get(paginaParser);
			listParser.add(parser);
		}
		
		LoggerUtils.info("[1/5] Se cargaron %s parser", listParser.size());
		
		//-------------------------------------------------------------------

		List<Publicacion> publicacionesGuardadas = publicacionDAO.findAll();
		
		LoggerUtils.info("[2/5] Existian %s publicaciones guardadas en la base de datos", publicacionesGuardadas.size());
		
		//-------------------------------------------------------------------
//		List<Publicacion> publicacionesDescargadas = publicacionesGuardadas;
		List<Publicacion> publicacionesDescargadas = new ArrayList<Publicacion>();
		for (Parser parser : listParser) {
			List<Publicacion> list = parser.getAds();
			publicacionesDescargadas.addAll(list);
		}
		
		LoggerUtils.info("[3/5] Se analizaron %s publicaciones remotas", publicacionesDescargadas.size());

		//-------------------------------------------------------------------

		List<Publicacion> publicacionesDescargadasFiltradas = filtrar(publicacionesDescargadas);
		
		LoggerUtils.info("[4/5] Se filtraron %s publicaciones remotas", publicacionesDescargadas.size() - publicacionesDescargadasFiltradas.size());
		
		//-------------------------------------------------------------------
		
		List<Publicacion> publicacionesNuevas = new ArrayList<Publicacion>();
		int pn = 0;
		for (Publicacion pf : publicacionesDescargadasFiltradas) {
			Publicacion pg = buscar(pf,publicacionesGuardadas,publicacionesNuevas);
			if(pg != null){
				if(pf.getPrecio().getValor() != pg.getPrecio().getValor()) {
					publicacionesNuevas.add(pf);
				}else{
					pn++;
				}
			}else{
				publicacionesNuevas.add(pf);
			}
		}
		
		LoggerUtils.info("[5/5] Existen %s publicaciones nuevas (se descartan %s duplicadas)", publicacionesNuevas.size(),pn);
		
		//-------------------------------------------------------------------
		
		for (Publicacion publicacion : publicacionesNuevas) {
			publicacionDAO.save(publicacion);
		}
		
	}

	private Publicacion buscar(Publicacion pf, List<Publicacion> publicacionesGuardadas, List<Publicacion> publicacionesNuevas) {
		Publicacion res = null;
		for (Publicacion pub : publicacionesGuardadas) {
			if(pub.getUuid().equals(pf.getUuid()) && pub.getPrecio().getValor() == pf.getPrecio().getValor()) {
				res = pub;
				break;
			}
		}
		for (Publicacion pub : publicacionesNuevas) {
			if(pub.getUuid().equals(pf.getUuid()) && pub.getPrecio().getValor() == pf.getPrecio().getValor()) {
				res = pub;
				break;
			}
		}
		return res;
	}

	private List<Publicacion> filtrar(List<Publicacion> ads) {
		List<Filtro> filtros = filtroDAO.findAll();
		List<Publicacion> list = new ArrayList<Publicacion>();
		for (Publicacion publicacion : ads) {
			boolean ignorar = true;
			for (Filtro filtro : filtros) {
				if(publicacion.toString().toLowerCase().indexOf(filtro.getCampo().toLowerCase()) != -1){
					ignorar = false;
					break;
				}
			}
			if(ignorar) {
				list.add(publicacion);				
			}
		}
		return list;
	}

	public EstadoDAO getEstadoDAO() {
		return estadoDAO;
	}

	public void setEstadoDAO(EstadoDAO estadoDAO) {
		this.estadoDAO = estadoDAO;
	}

	public PublicacionDAO getPublicacionDAO() {
		return publicacionDAO;
	}

	public void setPublicacionDAO(PublicacionDAO publicacionDAO) {
		this.publicacionDAO = publicacionDAO;
	}

	public FiltroDAO getFiltroDAO() {
		return filtroDAO;
	}

	public void setFiltroDAO(FiltroDAO filtroDAO) {
		this.filtroDAO = filtroDAO;
	}

	public PaginaParserDAO getPaginaParserDAO() {
		return paginaParserDAO;
	}

	public void setPaginaParserDAO(PaginaParserDAO paginaParserDAO) {
		this.paginaParserDAO = paginaParserDAO;
	}

}