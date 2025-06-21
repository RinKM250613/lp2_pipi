package com.exament2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exament2.models.Inventario;

public interface IInventarioRepository extends JpaRepository<Inventario, Integer>{

	List<Inventario> findAllByOrderByIdInventarioDesc();

}
