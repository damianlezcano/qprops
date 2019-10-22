package org.q3s.qprops.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.q3s.qprops.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Value;

public class BateriaServiceBean implements BateriaService {

	@Value("${bateria.linux.command}")
	private String command;
	
	@Override
	public int estado() {
		int b0 = check(0);
		int b1 = check(1);
		int b = (b0 + b1 ) / 2;
		return b;
	}
	
	private int check(int num) {
		
		String out = "-1";
		
		ProcessBuilder processBuilder = new ProcessBuilder();

		// Run a shell command
		
		processBuilder.command("bash", "-c", String.format(command, num));
		
		try {

			Process process = processBuilder.start();

			StringBuilder output = new StringBuilder();

			BufferedReader reader = new BufferedReader(
					new InputStreamReader(process.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line);
			}
			
			out = new String(output).replaceAll("\\%","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return out.isEmpty() ? -1 : Integer.valueOf(out);
	}
	

}