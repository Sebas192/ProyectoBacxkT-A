package com.app.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.app.web.modelos.Cargo;

import com.app.web.servicios.CargoServicio;

@Controller
public class CargoController {
	@Autowired
	private CargoServicio servicio;

	@GetMapping("/Cargo")
	public String ListaTodosLosCargos(Model modelo) {
		modelo.addAttribute("Cargo", servicio.ListaTodoLosCargos());
		return "/Cargo";
	}

	@GetMapping("/Cargo/eliminar/{IdCargo}")
	public String eliminarCargo(@PathVariable long IdCargo) {
		servicio.eliminar(IdCargo);
		return "redirect:/Cargo";
	}
	//experimentos//

	@GetMapping("/Cargo/Registrar")
	public String CargoFormulario(Model modelo) {
		Cargo cargo = new Cargo();
		modelo.addAttribute("Cargo", cargo);
		return "/Generar_cargo";
	}

	@PostMapping("/Cargo/Guardar")
	public String guardarCargo(@ModelAttribute("Cargo") Cargo cargo) {
		servicio.guardarCargo(cargo);
		return "redirect:/Cargo";

	}

	@GetMapping("/Cargo/editar/{IdCargo}")
	public String EditarCargoFomulario(@PathVariable long IdCargo, Model modelo) {
		modelo.addAttribute("Cargo", servicio.obtenerCargoPorId(IdCargo));
		return "/Editar_Cargo";
	}

	@PostMapping("/Cargo/{IdCargo}")
	public String EditarCargo(@PathVariable long IdCargo, @ModelAttribute("Cargo") Cargo cargo,Model modelo) {
		Cargo CargoExistente = servicio.obtenerCargoPorId(IdCargo);
		CargoExistente.setIdCargo(IdCargo);
		CargoExistente.setNombeCargo(cargo.getNombeCargo());
		servicio.actualizarCargo(CargoExistente);
		return "redirect:/Cargo";
	}
}
