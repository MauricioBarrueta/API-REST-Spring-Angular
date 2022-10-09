package com.curso.springangular.cursospringangular.controller;

import java.util.ArrayList;
import java.util.Optional;

import com.curso.springangular.cursospringangular.models.EmpleadoModel;
import com.curso.springangular.cursospringangular.services.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/* 1. El Controlador recibe la petición Web e inmediatamente llama al Servicio */

/** @CrossOrigin:
 *! Esta anotación se usa tanto a nivel de clase como de método para habilitar solicitudes de origen cruzado. 
 *! Permite todo el origen, todos los encabezados, los métodos HTTP especificados en la anotación @RequestMapping y una 
 *! Duración máxima de 30 min. Puede personalizar el comportamiento especificando los valores de atributo correspondientes. 
 ** En pocas palabras, permite la comunicación backend - frontend
 */
@CrossOrigin(origins = "http://localhost:4200") /* La URL creo es opcional */

/** INFO
 *! @RestController:
 *! Marca la clase como un controlador donde cada método devuelve un objeto de dominio en lugar de una vista.
 ** Significa que ya no usa resuelve vistas ni envía HTML en respuesta. Simplemente envía el objeto de dominio 
 ** como una respuesta HTTP en el formato que entienden los consumidores, como JSON */
@RestController
@RequestMapping("/empleados")

public class EmpleadoController {   
    //! @Autowired: Inyecta la dependencia del objeto explícitamente
    @Autowired
    EmpleadoService empleadoService;   
  
    /** INFO
     *! @GetMapping:
     ** Esta anotación se utiliza para asignar solicitudes HTTP GET a métodos de controlador específicos. 
     **     Es una anotación compuesta que actúa como un acceso directo para @RequestMapping */
    @GetMapping()
    public ArrayList<EmpleadoModel> getEmployes() { // Muestra todos los registros
        return empleadoService.listEmployes();
    }    
    
    /** INFO
    ** @RequestBody: Indica que un parámetro de método debe estar vinculado al valor del cuerpo de la solicitud HTTP 
    *! @PostMapping:
    ** Se utiliza para asignar solicitudes HTTP POST a métodos de controlador específicos.
    **     Es una anotación compuesta que actúa como un acceso directo para @RequestMapping. */
    @PostMapping()
    public EmpleadoModel saveEmploye(@RequestBody EmpleadoModel empleado) { 
        return this.empleadoService.saveEmploye(empleado);
    }

    /** INFO
     *! @PathVariable:
     ** Se declaran los parámetros que seran usados por @RequestMapping */
    @GetMapping(path = "/{id}") 
    /* Se pasa como parámetro (@PathVariable) y se añade el 'id' a la ruta '/empleados' */
    public Optional<EmpleadoModel> GetEmployeById(@PathVariable("id") Long id) {
        return this.empleadoService.getEmployeById(id);
    } 
    
    //! @RequestParam: Se usa para recuperar el parámetro URL y asignarlo al argumento del método en su controlador
    //* Ejemplo: 'http://localhost:8080/empleados/query?edad=24'
    @GetMapping("/query") /* Se pasan los parámetros como 'Query Params'. Ejemplo arriba en INFO*/
    public ArrayList<EmpleadoModel> GetEmployeByEdad(@RequestParam("edad") Integer edad) {
        return this.empleadoService.getEmployeByEdad(edad);
    }
    
    //! @DeleteMapping: Asigna solicitudes HTTP DELETE a métodos de Controlador específicos   
    @DeleteMapping(path = "/{id}")
    public void deleteEmployeById(@PathVariable("id") Long id) {
        empleadoService.deleteEmploye(id);        
    }
}
