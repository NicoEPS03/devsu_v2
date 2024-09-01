import { Component, Inject } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CuentasService } from '../../../_service/cuentas.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { Cuenta } from '../../../model/Cuenta';

@Component({
  selector: 'app-insert-cuenta',
  templateUrl: './insert-cuenta.component.html',
  styleUrl: './insert-cuenta.component.css'
})
export class InsertCuentaComponent {
  form!: FormGroup;
  id!: number;
  edicion!: boolean;
  hide = true;
  tipo = "Seleccione el genero";

  constructor(private cuentaService: CuentasService, public dialogRef: MatDialogRef<InsertCuentaComponent>,
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
        'numeroCuenta': new FormControl(0, [Validators.required]),
        'tipoCuenta': new FormControl('', [Validators.required]),
        'saldo': new FormControl(0, [Validators.required]),
        'clienteId': new FormControl(0, [Validators.required]),
      });
    } else {
      this.form = new FormGroup({
        'numeroCuenta': new FormControl(0, [Validators.required]),
        'tipoCuenta': new FormControl('', [Validators.required]),
        'saldo': new FormControl(0, [Validators.required]),
        'clienteId': new FormControl(0, [Validators.required]),
      });
    }
  }

  cargarDatos() {
    this.cuentaService.obtener(this.data.id).subscribe(data => {
      this.form.get("numeroCuenta")!.setValue(data.numeroCuenta);
      this.form.get("tipoCuenta")!.setValue(data.tipoCuenta);
      this.tipo = data.tipoCuenta;
      this.form.get("saldo")!.setValue(data.saldo);
      this.form.get("clienteId")!.setValue(data.clienteId);
    });
  }

  guardar() {
    let cuenta = new Cuenta();
    cuenta.numeroCuenta = this.form.value['numeroCuenta'];
    cuenta.tipoCuenta = this.form.value['tipoCuenta'];
    cuenta.saldo = this.form.value['saldo'];
    cuenta.clienteId = this.form.value['clienteId'];

    if (this.edicion === true) {

      this.cuentaService.editar(cuenta).subscribe(() => {
        this.form.reset();
        this.dialogRef.close();
        this.cuentaService.mensajeCambio.next('Cuenta editada satisfactoreamente');
      });
    } else {
      this.cuentaService.guardar(cuenta).subscribe(() => {
        this.form.reset();
        this.dialogRef.close();
        this.cuentaService.mensajeCambio.next('Cuenta agregada satisfactoreamente');
      });
    }
  }
}
