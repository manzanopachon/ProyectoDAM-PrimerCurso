package com.dam.airbase.controladores;

import org.springframework.web.bind.annotation.RequestMapping;

public class RutaController {

	@RequestMapping("/GestionRuta.html")
	public String GestionRuta() {
		return "GestionRuta";
	}
}
