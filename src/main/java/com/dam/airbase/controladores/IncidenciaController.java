package com.dam.airbase.controladores;

import org.springframework.web.bind.annotation.RequestMapping;

public class IncidenciaController {

	@RequestMapping("/GestionIncidencia.html")
	public String GestionIncidencia() {
		return "GestionIncidencia";
	}
}
