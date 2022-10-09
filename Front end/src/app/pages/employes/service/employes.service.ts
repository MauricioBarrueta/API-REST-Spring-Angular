import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employes } from '../interface/employes';

@Injectable({
  providedIn: 'root'
})
export class EmployesService {

  /* Variable con la ruta de la base de datos (servidor, api, etc)*/
  private APIUrl = "http://localhost:8080";

  constructor(private httpClient: HttpClient) { }

  /* Método que obtiene la lista de registros de la tabla */
  getEmployesList(): Observable<Employes[]> {
    return this.httpClient.get<Employes[]>(`${this.APIUrl}/empleados`);
  }

  //* Método que inserta un nuevo registro
  addNewEmploye(empleado: Employes): Observable<Object> {
    return this.httpClient.post<Employes>(`${this.APIUrl}/empleados`, empleado);
  }

  //* Método que obtiene el registro mediante su id
  getEmployeById(id: number): Observable<Employes> {
    return this.httpClient.get<Employes>(`${this.APIUrl}/empleados/${id}`);
  }

  //* Método que actualiza los datos del registro mediante su id
  updateEmploye(id: number, empleado: Employes): Observable<Employes> {
    return this.httpClient.put<Employes>(`${this.APIUrl}/empleados`, empleado); //! Si marca error cambiar a 'post'
  }

  //* Método que elimina un registro mediante su id
  deleteEmploye(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.APIUrl}/empleados/${id}`);
  }
}
