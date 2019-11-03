package org.q3s.qprops.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.q3s.qprops.model.Publicacion;
import org.q3s.qprops.services.BateriaService;
import org.q3s.qprops.services.PropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PublicacionController {

	@Autowired
	private PropService service;

	@Autowired
	private BateriaService bateriaService;
	
	@RequestMapping("/")
	public String index(@RequestParam(required = false, value = "orden",  defaultValue = "fechaRegistracion") String orden, Model modelo) {
		
		List<Publicacion> publicaciones = service.findAll();
		
		List<Publicacion> nuevos = new ArrayList<Publicacion>();
		List<Publicacion> enprocesos = new ArrayList<Publicacion>();
		List<Publicacion> candidatos = new ArrayList<Publicacion>();
		List<Publicacion> descartados = new ArrayList<Publicacion>();
		
		for (Publicacion publicacion : publicaciones) {
			if(publicacion.getEstado().getTipo() == null || (publicacion.getEstado().getTipo() != null && "nuevo".equalsIgnoreCase(publicacion.getEstado().getTipo()))) {
				nuevos.add(publicacion);
			}else if("enproceso".equalsIgnoreCase(publicacion.getEstado().getTipo())) {
				enprocesos.add(publicacion);
			}else if("candidato".equalsIgnoreCase(publicacion.getEstado().getTipo())) {
				candidatos.add(publicacion);
			}else if("descartado".equalsIgnoreCase(publicacion.getEstado().getTipo()) || "descartadoDesdeEnProceso".equalsIgnoreCase(publicacion.getEstado().getTipo()) || "descartadoDesdeCandidato".equalsIgnoreCase(publicacion.getEstado().getTipo())) {
				descartados.add(publicacion);
			}
		}
		
		modelo.addAttribute("orden", orden);
				
		modelo.addAttribute("nuevos", sort(nuevos,orden));
		modelo.addAttribute("enprocesos", sort(enprocesos,orden));
		modelo.addAttribute("candidatos", sort(candidatos,orden));
		modelo.addAttribute("descartados", sort(descartados,orden));
		
		int bateria = bateriaService.estado();
		modelo.addAttribute("bateria",bateria);
		
		return "/index";
	}

	@ResponseBody
	@RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
	public void updateStatus(@PathVariable("id") String id, @RequestParam("status") String estado) {
		service.updateStatus(id,estado);
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateNote/{id}", method = RequestMethod.POST)
	public void updateNote(@PathVariable("id") String id, @RequestParam("note") String nota) throws UnsupportedEncodingException {
		String idDecode = URLDecoder.decode(id, StandardCharsets.UTF_8.toString());
		service.updateNote(idDecode,nota);
	}
	
	@ResponseBody
	@RequestMapping(value = "/getNote/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getNote(@PathVariable("id") String id) {
		Publicacion publicacion = service.findById(id);
		return publicacion.getEstado().getNotas();
	}
	
	//----------------------------------------------------

	private List<Publicacion> sort(List<Publicacion> publicaciones, String orden) {
		if("precio".equalsIgnoreCase(orden)) {
			publicaciones.sort(Comparator.comparing(Publicacion::calcularPrecio,Comparator.nullsFirst(Comparator.naturalOrder())));
		}else if("fechaRegistracion".equalsIgnoreCase(orden)) {
			publicaciones.sort(Comparator.comparing(Publicacion::getFechaCreacion,Comparator.nullsFirst(Comparator.reverseOrder())).thenComparing(Publicacion::calcularPrecio));
		}else if("ubicacion".equalsIgnoreCase(orden)) {
			publicaciones.sort(Comparator.comparing(Publicacion::getUbicacion,Comparator.nullsFirst(Comparator.naturalOrder())).thenComparing(Publicacion::calcularPrecio));
		}else if("descuento".equalsIgnoreCase(orden)) {
			publicaciones.sort(Comparator.comparing(Publicacion::porcentajeAumentoPrecio,Comparator.nullsFirst(Comparator.naturalOrder())).thenComparing(Publicacion::calcularPrecio));
		}else if("m2".equalsIgnoreCase(orden)) {
			publicaciones.sort(Comparator.comparing(Publicacion::getM2,Comparator.nullsLast(Comparator.reverseOrder())).thenComparing(Publicacion::calcularPrecio));
		}else if("ambientes".equalsIgnoreCase(orden)) {
			publicaciones.sort(Comparator.comparing(Publicacion::getAmbientes,Comparator.nullsLast(Comparator.reverseOrder())).thenComparing(Publicacion::calcularPrecio));
		}else{
			publicaciones.sort(Comparator.comparing(Publicacion::getFechaCreacion,Comparator.nullsFirst(Comparator.reverseOrder())).thenComparing(Publicacion::calcularPrecio));
		}
		return publicaciones;
	}
	
	public PropService getService() {
		return service;
	}

	public void setService(PropService service) {
		this.service = service;
	}

}
