import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { Employes } from 'src/app/pages/employes/interface/employes';
import { EmployesService } from './service/employes.service';

@Component({
  selector: 'app-employes',
  templateUrl: './employes.component.html',
  styleUrls: ['./employes.component.css']
})
export class EmployesComponent implements OnInit {

  empleados!: Employes[];  

  /* Para consumir un service se inyecta en el constructor: */
  constructor(private employeService: EmployesService, private router: Router) { }

  ngOnInit(): void { 
    //* Se llama al método para que los registros sean mostrados en la tabla llamando a la variable 'empleados'
    this.getListEmployes();      
  }  

  //* Método que obtiene la lista de todos los registros de la tabla 'empleados'
  getListEmployes() {
    this.employeService.getEmployesList()
    .pipe(tap((employesList: Employes[]) => this.empleados = employesList))
    .subscribe();       
  }
  
  //* Método que obtiene el 'id' del registro seleccionado y lo pasa como parametro a la ruta especificada
  getEmployeToUpdate(id: number) {
    this.router.navigate(['/editarEmpleado/', id]);    
  }

  //* Método que obtiene el 'id' del registro seleccionado y lo elimina   
  deleteEmploye(id: number) {  
    if(confirm('¿Está seguro que desea eliminar el registro con el id: ' + id + '?')) {
      this.employeService.deleteEmploye(id)
      .subscribe({
        next: () => {
          this.getListEmployes();
          this.router.navigate(['/empleados']);             
        },
        error: error => { console.log(error); }        
      });
    }   
  }
}
