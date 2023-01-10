package com.platzimarket.persistence.mapper;

import com.platzimarket.domain.Product;
import com.platzimarket.persistence.entity.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}) // indica que es un mapeador
public interface ProductMapper {
    @Mappings({
            //recibe dos parámetros, la fuente y hacia donde lo queremos llevar
            @Mapping(source = "idProducto", target = "productId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precioVenta", target = "price"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "active"),
            @Mapping(source = "categoria", target = "category"),
            //como se esta utilizando Categoria que ya tiene un mapper en el @Mapper se coloca un nuevo parámetro USES
    })
    Product toProduct(Producto producto);

    //    no se define el @Mappings, MappStruc por debajo ya sabe que debe mapearlo
    List<Product> toProducts(List<Producto> productos);

    // esta anotación define que se va a realizar el mapeo inverso a la de arriba
    @InheritInverseConfiguration
    //como no se mapearan el codigo de barras, se le indica que lo ignore
    @Mapping(target = "codigoBarras", ignore = true)
    Producto toProducto(Product product);


}
