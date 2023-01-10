package com.platzimarket.persistence.crud;

import com.platzimarket.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    //QUERY NATIVO ->@Query(value = "SELECT * FROM productos WHERE id_categoria = ?", nativeQuery = true)
    // QUERY METHODS
    List<Producto> findByIdCategoriaOrderByNombreAsc(Integer id);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(Integer cantidadStock, Boolean estado);
}
