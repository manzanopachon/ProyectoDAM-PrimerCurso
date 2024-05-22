package com.dam.airbase.controladores;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/GestionAvion.html")
public class AvionController {

	public String GestionAvion() {
		return "GestionAvion";
	}
}
