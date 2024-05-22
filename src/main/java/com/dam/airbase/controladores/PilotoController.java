package com.dam.airbase.controladores;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dam.airbase.entidades.Piloto;

@Controller

public class PilotoController {

	protected ArrayList<Piloto> pilotos = new ArrayList<Piloto>();
		
	@RequestMapping("/GestionPiloto.html")
	public String GestionPiloto() {
		return "GestionPiloto";
	}
	
	@GetMapping("menu")
	public ModelAndView menu() {
		String nombre = "Fali";
		
		ModelAndView maw = new ModelAndView("menu");
		maw.addObject("etiqueta1", nombre);
		return maw;
	}
	@GetMapping("alta")
	public ModelAndView alta(String id, String nombre, int horasVuelo,boolean operativo,boolean admin) {
		
		System.out.println(id + " - " + nombre+" - " + horasVuelo+" - " + operativo+" - " + admin);
		
		ModelAndView maw = new ModelAndView("alta");
		
		Piloto p = new Piloto(id,nombre,horasVuelo, operativo, admin);
		
		pilotos.add(p);
		return maw;
	}
}
