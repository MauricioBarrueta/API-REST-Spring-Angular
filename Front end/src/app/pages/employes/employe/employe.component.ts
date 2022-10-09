import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { Employes } from '../interface/employes';
import { EmployesService } from '../service/employes.service';

@Component({
  selector: 'app-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['./employe.component.css']
})
export class EmployeComponent implements OnInit {  

  empleado: Employes = new Employes();

  public NewEmployeForm: FormGroup; /* Variable de tipo 'FormGroup' a la que debe hacer referencia <form> */
  
  id: number; /* Variable a la que se le asigna el 'id' recibido de la ruta */  
  isAddNewEmployeMode: boolean; /* Variable usada para saber si el formulario esta en modo 'agregar' o 'editar' */
  loading = false; /* Variable que controla si se muestra el spinner al hacer clic o no */
  submited = false; /* Variable que indica cuando se a hecho submit */

  constructor(private formBuilder: FormBuilder, private employesService: EmployesService, 
    private activatedRoute: ActivatedRoute, private route: Router) { }

  ngOnInit(): void {
    /* Se le asigna el parametro (id) de la ruta a la variable local 'id' */
    this.id = this.activatedRoute.snapshot.params['id'];

    //* Y comprueba si no hay ningún parametro (id), la variable booleana es true, lo que significa que se registrará un nuevo empleado
    this.isAddNewEmployeMode = !this.id;    
   
    //* Se carga el formulario y se le asignan las validaciones a cada control
    this.NewEmployeForm = this.formBuilder.group({ 
      id: [''],     
      nombre: ['', [Validators.required, Validators.minLength(3)]],
      apellido: ['', [Validators.required, Validators.minLength(3)]],
      edad: ['', [Validators.required, Validators.maxLength(2)]],
      email: ['', [Validators.email, Validators.required]]
    }); 

    /* Valida si la variable booleana es falsa, significa que esta en modo 'editar' y ejecuta el método del service que obtiene al empleado */
    if(!this.isAddNewEmployeMode) {
      this.employesService.getEmployeById(this.id)
          .pipe(first())
          //* Se cargan todos los elementos obtenidos mediante 'patchValue' y se cargan en los input del formulario
          .subscribe(x => this.NewEmployeForm.patchValue(x));
    }
  }

  //* Método llamado en el HTML que verifica el modo en el que está (agregar o editar) y ejecuta el método correspondiente
  onSubmitForm() {
    this.submited = true;
    if(this.NewEmployeForm.invalid) { return }
   
    this.loading = true; /* Activa el Spinner, el cual se desactiva en los métodos de abajo */

    //* Verifica si la variable booleana corresponde al modo 'agregar' o 'editar' posteriormente ejecuta el método correspondiente
    this.isAddNewEmployeMode ? this.saveNewEmploye() : this.updateEmploye();
  }

  /**
   *! FALTA UNA VENTANA O ALERTA QUE INDIQUE QUE SE REGISTRO O EDITO EL DATO
   */

  //* Método que guarda un nuevo registro
  private saveNewEmploye() {
    /* .value: Objeto que muestra la estructura de los datos obtenidos del formulario */
    this.employesService.addNewEmploye(this.NewEmployeForm.value)
        .pipe(first()) //* first(): Devuelve el primer valor que recive y cierra el Observable
        .subscribe({
          next: () => { this.route.navigate(['/empleados']); }, //* next: Se usa para cada elemento emitido por el Observable
          error: error => { //* error: Muestra el error de la secuencia en caso de que exista 
            console.log(error);
            this.loading = false; // Se desactiva el Spinner 
          }
        });         
        this.NewEmployeForm.reset();
  }

  /* Método que actualiza el registro que coincida con el id de ruta */
  private updateEmploye() {
  /* patchValue(): Establece un nuevo valor para los controles del formulario. El objeto debe coincidir exactamente con la estructura del grupo */
    this.NewEmployeForm.patchValue({id: this.id}); //* Se pasa el valor de la variable local 'id' al control del formulario de nombre 'id'

    let index = this.NewEmployeForm.value;    
    let idToUpdate = index.id; //* Se crea una variable que obtendra el parámetro 'id' del index del formulario
    
    //* Se pasan las 2 variables como parámetros del método del service
    this.employesService.updateEmploye(idToUpdate, index)
        .pipe(first())
        .subscribe({
          next: () => { this.route.navigate(['/empleados']); },
          error: error => { 
            console.log(error);
            this.loading = false; // Se desactiva el Spinner
          }
        });         
        this.NewEmployeForm.reset(); 
  } 
}
