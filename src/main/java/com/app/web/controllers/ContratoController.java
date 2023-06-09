package com.app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.modelos.Contrato;
import com.app.web.servicios.ContratoServicios;

@Controller
public class ContratoController {
	@Autowired
	private ContratoServicios servicio;
	
	@GetMapping("/Contrato")
	public String ListaTodosLosContratos(Model modelo) {
		modelo.addAttribute("Contrato", servicio.ListaTodoLosContratos());
		return "/Contrato";
	}
	
	@GetMapping("/Contrato/eliminar/{IdContrato}")
	public String eliminarContrato(@PathVariable long IdContrato) {
		servicio.eliminarContrato(IdContrato);
		return "redirect:/Contrato";
	}
		//experimentos//
		
	@GetMapping("/Contrato/Registrar")
	public String ContratoFormulario(Model modelo) {
		Contrato contrato = new Contrato();
		modelo.addAttribute("Contrato", contrato);
		return "/Generar_Contrato";
	}

	@PostMapping("/Contrato/Guardar")
	public String guardarContrato(@ModelAttribute("Contrato") Contrato contrato) {
		servicio.guardarContrato(contrato);
		return "redirect:/Contrato";

	}

	@GetMapping("/Contrato/editar/{IdContrato}")
	public String EditarContratoFomulario(@PathVariable long IdContrato, Model modelo) {
		modelo.addAttribute("Contrato", servicio.obtenerContratoPorId(IdContrato));
		return "/Editar_Contrato";
	}

	@PostMapping("/Contrato/{IdContrato}")
	public String EditarContrato(@PathVariable long IdContrato, @ModelAttribute("Contrato") Contrato contrato,
			Model modelo) {

		Contrato ContratoExistente = servicio.obtenerContratoPorId(IdContrato);
		ContratoExistente.setIdContrato(IdContrato);
		ContratoExistente.setNombreContrato(contrato.getNombreContrato());
		ContratoExistente.setFecha_inicio(contrato.getFecha_inicio());
		ContratoExistente.setFecha_final(contrato.getFecha_final());
		ContratoExistente.setUsuario(contrato.getUsuario());

		servicio.actualizarContrato(ContratoExistente);
		return "redirect:/Contrato";
	}
}
