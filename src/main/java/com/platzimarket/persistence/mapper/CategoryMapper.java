package com.platzimarket.persistence.mapper;

import com.platzimarket.domain.Category;
import com.platzimarket.persistence.entity.Categoria;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") // indica que es un mapeador
public interface CategoryMapper {
    @Mappings({
            //recibe dos parámetros, la fuente y hacia donde lo queremos llevar
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "descripcion", target = "category"),
            @Mapping(source = "estado", target = "active"),
    })
    Category toCategory(Categoria categoria);// vamos a convertir una categoria que recibe por parámetro en una Category

    // esta anotación define que se va a realizar el mapeo inverso a la de arriba
    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    //como no se mapearan los productos, se le indica que lo ignore
    Categoria toCategoria(Category category);
}
