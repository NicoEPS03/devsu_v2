import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ClientService } from '../../../_service/client.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { Cliente } from '../../../model/Cliente';

@Component({
  selector: 'app-insert-client',
  templateUrl: './insert-client.component.html',
  styleUrl: './insert-client.component.css'
})
export class InsertClientComponent implements OnInit{
  form!: FormGroup;
  id!: number;
  edicion!: boolean;
  hide = true;
  genero = "Seleccione el genero";

  constructor(private clienteService: ClientService, public dialogRef: MatDialogRef<InsertClientComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, private infoSnackBar: MatSnackBarModule) { }

  ngOnInit() {
    this.edicion = this.data.edit;
    this.inicializarFormularioVacio();
    if (this.edicion == true) {
      this.cargarDatos();
    }
  }
  
  inicializarFormularioVacio(){
    if (this.edicion == true) {
      this.form = new FormGroup({
        'idPersona': new FormControl(0, [Validators.required]),
        'nombre': new FormControl('', [Validators.required]),
        'genero': new FormControl('', [Validators.required]),
        'edad': new FormControl(0, [Validators.required]),
        'identificacion': new FormControl(DocumentType, [Validators.required]),
        'direccion': new FormControl('', [Validators.required]),
        'telefono': new FormControl(0, [Validators.required]),
        'password': new FormControl('', [Validators.required]),

      });
    } else {
      this.form = new FormGroup({
        'idPersona': new FormControl(0, [Validators.required]),
        'nombre': new FormControl('', [Validators.required]),
        'genero': new FormControl('', [Validators.required]),
        'edad': new FormControl(0, [Validators.required]),
        'identificacion': new FormControl('', [Validators.required]),
        'direccion': new FormControl('', [Validators.required]),
        'telefono': new FormControl('', [Validators.required]),
        'password': new FormControl('', [Validators.required]),
      });
    }
  }

  cargarDatos() {
    this.clienteService.obtener(this.data.id).subscribe(data => {
      this.form.get("nombre")!.setValue(data.nombre);
      this.form.get("genero")!.setValue(data.genero);
      this.genero = data.genero;
      this.form.get("edad")!.setValue(data.edad);
      this.form.get("identificacion")!.setValue(data.identificacion);
      this.form.get("direccion")!.setValue(data.direccion);
      this.form.get("telefono")!.setValue(data.telefono);
      this.form.get("password")!.setValue(data.password);
      this.id = data.idPersona;
    });
  }

  guardar() {
    let cliente = new Cliente();
    cliente.nombre = this.form.value['nombre'];
    cliente.genero = this.form.value['genero'];
    cliente.edad = this.form.value['edad'];
    cliente.identificacion = this.form.value['identificacion'];
    cliente.direccion = this.form.value['direccion'];
    cliente.telefono = this.form.value['telefono'];
    cliente.password = this.form.value['password'];

    if (this.edicion === true) {
      cliente.idPersona = this.id;

      this.clienteService.editar(cliente).subscribe(() => {
        this.form.reset();
        this.dialogRef.close();
        this.clienteService.mensajeCambio.next('Cliente editado satisfactoreamente');
      });
    } else {
      this.clienteService.guardar(cliente).subscribe(() => {
        this.form.reset();
        this.dialogRef.close();
        this.clienteService.mensajeCambio.next('Cliente agregado satisfactoreamente');
      });
    }
  }
}
