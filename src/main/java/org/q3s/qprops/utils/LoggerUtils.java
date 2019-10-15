package org.q3s.qprops.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.q3s.qprops.model.Publicacion;

public class LoggerUtils {
	
	static DateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
	
	public static void print(Publicacion pub) {
		System.err.println("ID: " + pub.getUuid());
		System.err.println("Descripcion: " + pub.getDescripcion());
		System.err.println("Ubicacion: " + pub.getUbicacion());
		System.err.print("m2: " + pub.getM2() + " - ");
		System.err.println("Ambientes: " + pub.getAmbientes());
		System.err.println("Precio: " + pub.getPrecio());
		System.err.println("Img: " + pub.getImg());
		System.err.println("Link:" + pub.getLink());
		System.err.println("----------------------------------------");
	}
	
	public static void info(String txt, Object ... args) {
		String fecha = dateFormat.format(new Date());
		System.out.println(String.format("# "  + fecha + " - " + txt, args));
	}
	
	public static void sysout(String txt, Object ... args) {
		String fecha = dateFormat.format(new Date());
		System.out.println(String.format("# "  + fecha + " - " + txt, args));
	}
	
	public static void err(String txt, Object ... args) {
		String fecha = dateFormat.format(new Date());
		System.err.println(String.format("# "  + fecha + " - " + txt, args));
	}

}
