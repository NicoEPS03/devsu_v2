import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CuentasService } from '../../../_service/cuentas.service';

@Component({
  selector: 'app-delete-cuenta',
  templateUrl: './delete-cuenta.component.html',
  styleUrl: './delete-cuenta.component.css'
})
export class DeleteCuentaComponent {
  constructor(public dialogRef: MatDialogRef<DeleteCuentaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private cuentaService: CuentasService,) { }

  ngOnInit(): void {
  }

  eliminar() {
    this.cuentaService.eliminar(this.data.id).subscribe(data => {
      this.dialogRef.close();
      this.cuentaService.mensajeCambio.next('Cuenta eliminada satisfactoreamente');
    });
  }
}
