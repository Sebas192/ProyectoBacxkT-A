package com.app.web.controllers;

import com.app.web.modelos.Venta;
import com.app.web.servicios.VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.modelos.Usuario;
import com.app.web.servicios.UsuarioServicio;

import java.util.List;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioServicio servicio;

	@GetMapping("/Usuario")
	public String ListaUsuario(Model modelo) {
		modelo.addAttribute("Usuario", servicio.listarUsuarios());
		return "/Usuario";
	}
	
	@GetMapping("/Usuario/eliminar/{IdUsuario}")
	public String eliminarUsuario(@PathVariable long IdUsuario) {
		servicio.eliminarUsuarios(IdUsuario);
		return "redirect:/Usuario";
	}
		//experimentos//
		
	@GetMapping("/Usuario/Registrarse")
	public String UsuarioFormulario(Model modelo) {
		Usuario usuario = new Usuario();
		modelo.addAttribute("Usuario", usuario);
		return "/Registrarse";
	}

	@PostMapping("/Usuario/Guardar")
	public String guardarUsuario(@ModelAttribute("Usuario") Usuario usuario) {
		servicio.guardarUsuarios(usuario);
		return "redirect:/Usuario";

	}

	@GetMapping("/Usuario/editar/{IdUsuario}")
	public String EditarUsuarioFomulario(@PathVariable long IdUsuario, Model modelo) {
		modelo.addAttribute("Usuario", servicio.obtenerUsuariosPorId(IdUsuario));
		return "/editarUsuario";
	}

	@PostMapping("/Usuario/{IdUsuario}")
	public String EditarUsuario(@PathVariable long IdUsuario, @ModelAttribute("Usuario") Usuario usuario,
			Model modelo) {

		Usuario UsuarioExistente = servicio.obtenerUsuariosPorId(IdUsuario);
		UsuarioExistente.setIdUsuario(IdUsuario);
		UsuarioExistente.setNombreCompleto(usuario.getNombreCompleto());
		UsuarioExistente.setDocumento(usuario.getDocumento());
		UsuarioExistente.setTelefono(usuario.getTelefono());
		UsuarioExistente.setEmail(usuario.getEmail());
		UsuarioExistente.setRecidencia(usuario.getRecidencia());
		UsuarioExistente.setPassword(usuario.getPassword());
		UsuarioExistente.setHojaDeVida(usuario.getHojaDeVida());
		UsuarioExistente.setCotizacion(usuario.getCotizacion());
		UsuarioExistente.setRol(usuario.getRol());
		UsuarioExistente.setPostulacion(usuario.getPostulacion());
		UsuarioExistente.setContrato(usuario.getContrato());
		UsuarioExistente.setCotizacion(usuario.getCotizacion());
		servicio.actualizarUsuarios(UsuarioExistente);
		return "redirect:/Usuario";
	}
}
