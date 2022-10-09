package com.curso.springangular.cursospringangular.services;

import java.util.ArrayList;
import java.util.Optional;

import com.curso.springangular.cursospringangular.models.EmpleadoModel;
import com.curso.springangular.cursospringangular.repositories.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

/* 2. El Servicio es el encargado de ejecutar la lógica de la aplicación, éste hace uso del Repositorio */

@Service
public class EmpleadoService {    
    @Autowired // Anotación que instancia automáticamente el repositorio u otro fichero
    EmpleadoRepository empleadoRepository;
    
    /* Método que recupera todos los registros de la tabla */
    public ArrayList<EmpleadoModel> listEmployes() { 
        /* Se usa el método del repositorio Crud, y se castea a un array de tipo Model */
        return (ArrayList<EmpleadoModel>)empleadoRepository.findAll();
    }
    
    /* Método que agrega un nuevo registro */
    public EmpleadoModel saveEmploye(EmpleadoModel empleado) { 
        /* Se usa el método 'save' del repositorio Crud, recibiendo como parámetro el nuevo empleado */
        return empleadoRepository.save(empleado);
    }

    /* Método que busca un registro con un id en especifico */
    /** INFO
     *! Optional:
     ** Si no existe el registro no genera ningún problema en la ejecución */
    public Optional<EmpleadoModel> getEmployeById(Long id) { 
        return empleadoRepository.findById(id);
    }

    /* Método que busca los registros que coincidan en la 'edad' mediante query */
    public ArrayList<EmpleadoModel> getEmployeByEdad(int edad) { 
    /* Se hace uso del método automático creado en el Repositorio */    
    return empleadoRepository.findByEdad(edad);
    }
  
    /* Método que borra un registro tomando como referencia su id */
   /* public boolean deleteEmploye(Long id) {
        try {
            empleadoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }*/
    public void deleteEmploye(Long id) {
        empleadoRepository.deleteById(id);
    }
}