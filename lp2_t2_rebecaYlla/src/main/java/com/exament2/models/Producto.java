package com.exament2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "tbl_producto")	
public class Producto {

	@Id
	@Column(name = "id")
	private int idProducto;
	
	@Column(name = "nombre")
	private String nombre;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	
	@Column(name = "stock_maximo")
	private int stockMax;
	
	@Column(name = "stock_actual")
	private int stockAct;
	
	
	public int getDiferencia() {
		return stockMax - stockAct;
	}
	
}
