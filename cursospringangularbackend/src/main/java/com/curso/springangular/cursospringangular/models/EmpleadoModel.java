package com.curso.springangular.cursospringangular.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/* 4. El modelo es la maquetación de la base de datos, y de este depende el Repositorio */

/*** INFO
*! @Data: Anotación de Lombok que agrupa las características de @ToString, @EqualsAndHashCode, @Getter/@Setter y @RequiredArgsConstructor
** @Entity: Anotación que indica que este fichero será quien interactue con la base de datos */

@Entity
/*@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter(value = AccessLevel.PACKAGE)
@Getter*/
@Table(name = "Empleados")
public class EmpleadoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* Se genera automáticamente y se autoincrementa */    
    @Column(unique = true, nullable = false) /* Es un valor único y no puede ser nulo */
    private Long id;

    @Column(nullable = false)      
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;

    @Column(unique = true, nullable = false)
    private String email;    

    public EmpleadoModel() { }

    public EmpleadoModel(Long id, String nombre, String apellido, int edad, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.email = email;
    }

    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    
    public void setApellido(String apellido) { this.apellido = apellido; }
   
    public int getEdad() { return edad; }
    
    public void setEdad(int edad) { this.edad = edad; }
    
    public String getEmail() { return email; }
    
    public void setEmail(String email) { this.email = email; }   
}
