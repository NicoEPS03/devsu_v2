import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { ClientService } from '../../_service/client.service';
import { MatDialog } from '@angular/material/dialog';
import { InsertClientComponent } from './insert-client/insert-client.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteClientComponent } from './delete-client/delete-client.component';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrl: './client.component.css'
})
export class ClientComponent implements OnInit{
  form!: FormGroup;
  displayedColumns: string[] = ['idPersona', 'nombre', 'genero', 'edad', 'identificacion', 'direccion', 'telefono', 'password', 'estado', 'accion'];
  dataSource = new MatTableDataSource<any>();

  constructor(private clienteService: ClientService, private dialog: MatDialog, private snackBar: MatSnackBar){};

  ngOnInit(): void {
    this.listar();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  listar(){
    this.clienteService.listar().subscribe(data =>{
      this.dataSource = new MatTableDataSource(data);
    });
  }

  openSnackBar(message: string) {
    this.snackBar.open(message, 'Información', {
      duration: 2000,
    });
  }

  insertModal(){
    const dialogRef = this.dialog.open(InsertClientComponent, { data: { edit: false} });
    this.clienteService.mensajeCambio.subscribe(data => {
      dialogRef.afterClosed().subscribe(result => {
        this.listar();
        this.openSnackBar(data);
      });
    });
  }

  editModal(id: any){
    const dialogRef = this.dialog.open(InsertClientComponent, { data: {id: id, edit: true} });
    this.clienteService.mensajeCambio.subscribe(data => {
      dialogRef.afterClosed().subscribe(result => {
        this.listar();
        this.openSnackBar(data);
      });
    });
  }

  deleteModal(id: any){
    const dialogRef = this.dialog.open(DeleteClientComponent, { data: {id: id} });
    this.clienteService.mensajeCambio.subscribe(data => {
      dialogRef.afterClosed().subscribe(result => {
        this.listar();
        this.openSnackBar(data);
      });
    });
  }
}
