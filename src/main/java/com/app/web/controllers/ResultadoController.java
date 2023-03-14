package com.app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.modelos.Resultado;
import com.app.web.servicios.ResultadoServicio;

@Controller
public class ResultadoController {
	@Autowired
	private ResultadoServicio servicio;
	
	@GetMapping("/Resultado")
	public String ListaResultado(Model modelo) {
		modelo.addAttribute("Resultado", servicio.ListaTodoLosResultados());
		return "/Resultado";
	}
	
	@GetMapping("/Resultado/eliminar/{IdResultado}")
	public String eliminarResultado(@PathVariable long IdResultado) {
		servicio.eliminarResultado(IdResultado);
		return "redirect:/Resultado";
	}
		//experimentos//
		
	@GetMapping("/Resultado/Registrar")
	public String ResultadoFormulario(Model modelo) {
		Resultado resultado = new Resultado();
		modelo.addAttribute("Resultado", resultado);
		return "/crearResultado";
	}

	@PostMapping("/Guardar")
	public String guardarResultado(@ModelAttribute("Resultado") Resultado resultado) {
		servicio.guardarResultado(resultado);
		return "redirect:/Resultado";

	}

	@GetMapping("/Resultado/editar/{IdResultado}")
	public String EditarResultadoFomulario(@PathVariable long IdResultado, Model modelo) {
		modelo.addAttribute("Resultado", servicio.obtenerResultadoPorId(IdResultado));
		return "/editarResultado";
	}

	@PostMapping("/Resultado/{IdResultado}")
	public String EditarResultado(@PathVariable long IdResultado, @ModelAttribute("Resultado") Resultado resultado,
			Model modelo) {

		Resultado ResultadoExistente = servicio.obtenerResultadoPorId(IdResultado);
		ResultadoExistente.setIdResultado(IdResultado);
		ResultadoExistente.setResultado_Examen(resultado.getResultado_Examen());
		ResultadoExistente.setResultado_entrevista(resultado.getResultado_entrevista());
		ResultadoExistente.setCita(resultado.getCita());
		

		servicio.actualizarResultado(ResultadoExistente);
		return "redirect:/Resultado";
	}
}
