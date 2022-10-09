package com.curso.springangular.cursospringangular.repositories;

import java.util.ArrayList;

import com.curso.springangular.cursospringangular.models.EmpleadoModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/* 3. El Repositorio es quien hace las conexiones a la base de datos y éste depende del Modelo */

/** INFO:
 *! CrudRepository:
 ** Proporciona principalmente funciones CRUD
 *! JpaRepository:
 ** Proporciona algunos métodos relacionados con JPA
 ** Cuenta con todas las funciones de CrudRepository y PagingAndSortingRepository */
@Repository
/* En Java 'interface' indica que implementa ciertos métodos, ya incluidos en CrudRepository */
public interface EmpleadoRepository extends CrudRepository<EmpleadoModel, Long> {

    /* Se crea una consulta automática que obtiene los datos que coincidan en la edad */
    public abstract ArrayList<EmpleadoModel>findByEdad(Integer edad);    
}
