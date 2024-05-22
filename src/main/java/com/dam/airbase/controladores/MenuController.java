package com.dam.airbase.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/MenuPrincipal")
public class MenuController {
	@GetMapping("menu")
	public String MenuPrincipal() {
		return "PaginaPrincipal";
	}
}
