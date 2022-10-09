import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeComponent } from './pages/employes/employe/employe.component';
/**
 *! Aquí se insertan todas las rutas de los modulos de la aplicación */

/* El listado de rutas se ejecutan en el orden en que se anotaron */
const routes: Routes = [  
    { path: '', redirectTo: '/empleados', pathMatch: 'full' },
    { path: 'empleados', loadChildren: () => 
                        import('./pages/employes/employes.module').then(m => m.EmployesModule) },
    { path: 'nuevoEmpleado', component: EmployeComponent },
    { path: 'editarEmpleado/:id', component: EmployeComponent }, 
     
    { path: '**', redirectTo: '', pathMatch: 'full'} /* En caso del error 404 */ 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }