package com.exament2.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.exament2.models.Inventario;
import com.exament2.repositories.IInventarioRepository;
import com.exament2.repositories.IProductoRepository;
import com.exament2.utils.Alert;

@Controller
@RequestMapping("/inventarioReb")
public class InventarioController {

	@Autowired
	IInventarioRepository _IInventarioRepository;
	
	@Autowired
	IProductoRepository _IProductoRepository;
	
	
	@GetMapping("/listadoYllanes")
	public String listar(Model model) {
		List<Inventario> lstInvs = _IInventarioRepository.findAllByOrderByIdInventarioDesc();
		model.addAttribute("lstInvs", lstInvs);
		return  "/inventarioReb/listadoYllanes";
	}
	
	@GetMapping("/nuevoYllanes")
	public String nuevo(Model model) {
		
		model.addAttribute("lstProds",_IProductoRepository.findAllByOrderByNombre());
		
		Inventario inventario = new Inventario();
		inventario.setFechaVenci(LocalDate.now());
		model.addAttribute("invu", inventario);
		
		return "inventarioReb/nuevoYllanes";
	}
	
	@PostMapping("/registrar")
	public String registrar(@ModelAttribute Inventario invu, Model model, RedirectAttributes flash) {
		
		model.addAttribute("lstProds",_IProductoRepository.findAllByOrderByNombre());
		
		Inventario registrado = _IInventarioRepository.save(invu);
		
		String mensaje = Alert.sweetAlertSuccess("Inventario con código " + registrado.getIdInventario() + " registrado");
		
		flash.addFlashAttribute("alert",mensaje);

		return "redirect:/inventarioReb/listadoYllanes";
	}

	
	@GetMapping("/edicionYllanes/{id}")
	public String edicion(@PathVariable Integer id, Model model) {
		
		model.addAttribute("lstProds",_IProductoRepository.findAllByOrderByNombre());
		
		Inventario inventario = _IInventarioRepository.findById(id).orElseThrow();
		model.addAttribute("invu", inventario);
		
		return "inventarioReb/edicionYllanes";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Inventario inventario, Model model, RedirectAttributes flash) {
		
		model.addAttribute("lstProds",_IProductoRepository.findAllByOrderByNombre());
		Inventario registrado = _IInventarioRepository.save(inventario);
		String mensaje = Alert.sweetAlertSuccess("Inventario con código " + registrado.getIdInventario() + " actualizado");
		flash.addFlashAttribute("alert",mensaje);
		
		return "redirect:/inventarioReb/listadoYllanes";
	}
	
	@GetMapping("/eliminarYllanes/{id}")
	public String eliminar(@PathVariable Integer id, Model model) {
	    Inventario inventario = _IInventarioRepository.findById(id).orElseThrow();
	    model.addAttribute("invu", inventario);
	    return "inventarioReb/eliminarYllanes";
	}

	@PostMapping("/eliminar/{id}")
	public String eliminarInventario(@PathVariable Integer id, RedirectAttributes flash) {
		_IInventarioRepository.deleteById(id);
	    String mensaje = Alert.sweetAlertSuccess("Inventario con código " + id + " eliminado");
	    flash.addFlashAttribute("alert", mensaje);
	    return "redirect:/inventarioReb/listadoYllanes";
	}
	
	
}
