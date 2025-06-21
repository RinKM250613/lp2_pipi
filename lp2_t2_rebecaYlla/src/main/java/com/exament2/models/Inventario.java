package com.exament2.models;

import java.time.LocalDate;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@DynamicInsert
@Entity
@Table(name = "tbl_inventario")
public class Inventario {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numero")
	private int idInventario;
	
	@Column(name = "fecha_vencimiento")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaVenci;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	@Column(name = "costo_ingreso")
	private Double costoIng;

	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "lote")
	private String lote;
	
	@Column(name = "cod_estado")
	private String codEstado;
	
	public String getNomEstado() {
		switch (codEstado) {
		case "A":
			return "Activo";
		case "V":
			return "Vencido";
		case "T":
			return "En tránsito";
		case "B":
			return "Bloqueado";
		default:
			return "Desconocido";
		}
	}
}
