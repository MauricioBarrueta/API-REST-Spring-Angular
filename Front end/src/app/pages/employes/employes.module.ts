import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { EmployesRoutingModule } from "./employes-routing.module";
import { EmployesComponent } from "./employes.component";
import { EmployeComponent } from './employe/employe.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";


@NgModule({
    declarations: [
        EmployesComponent,
        EmployeComponent
        
    ],
    imports: [
        CommonModule,
        EmployesRoutingModule, 
        FormsModule,
        ReactiveFormsModule,         
    ]
})

export class EmployesModule { }