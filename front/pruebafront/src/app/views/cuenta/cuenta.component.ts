import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CuentasService } from '../../_service/cuentas.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormGroup } from '@angular/forms';
import { DeleteCuentaComponent } from './delete-cuenta/delete-cuenta.component';
import { InsertCuentaComponent } from './insert-cuenta/insert-cuenta.component';

@Component({
  selector: 'app-cuenta',
  templateUrl: './cuenta.component.html',
  styleUrl: './cuenta.component.css'
})
export class CuentaComponent {
  form!: FormGroup;
  displayedColumns: string[] = ['numeroCuenta', 'tipoCuenta', 'saldo', 'estado', 'clienteId', 'accion'];
  dataSource = new MatTableDataSource<any>();

  constructor(private cuentaService: CuentasService, private dialog: MatDialog, private snackBar: MatSnackBar){};

  ngOnInit(): void {
    this.listar();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  listar(){
    this.cuentaService.listar().subscribe(data =>{
      this.dataSource = new MatTableDataSource(data);
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Información', {
      duration: 2000,
    });
  }

  insertModal(){
    const dialogRef = this.dialog.open(InsertCuentaComponent, { data: { edit: false} });
    this.cuentaService.mensajeCambio.subscribe(data => {
      dialogRef.afterClosed().subscribe(result => {
        this.listar();
        this.openSnackBar(data);
      });
    });
  }

  editModal(id: any){
    const dialogRef = this.dialog.open(InsertCuentaComponent, { data: {id: id, edit: true} });
    this.cuentaService.mensajeCambio.subscribe(data => {
      dialogRef.afterClosed().subscribe(result => {
        this.listar();
        this.openSnackBar(data);
      });
    });
  }

  deleteModal(id: any){
    const dialogRef = this.dialog.open(DeleteCuentaComponent, { data: {id: id} });
    this.cuentaService.mensajeCambio.subscribe(data => {
      dialogRef.afterClosed().subscribe(result => {
        this.listar();
        this.openSnackBar(data);
      });
    });
  }
}
