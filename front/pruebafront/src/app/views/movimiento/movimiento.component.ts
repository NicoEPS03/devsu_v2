import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { MovimientosService } from '../../_service/movimientos.service';
import { Movimiento } from '../../model/Movimiento';
import autoTable from 'jspdf-autotable';
import { jsPDF } from 'jspdf';


const today = new Date();
const month = today.getMonth();
const year = today.getFullYear();

@Component({
  selector: 'app-movimiento',
  templateUrl: './movimiento.component.html',
  styleUrl: './movimiento.component.css'
})
export class MovimientoComponent {
  form!: FormGroup;
  displayedColumns: string[] = ['fecha', 'cliente', 'numeroCuenta', 'tipo', 'saldoInicial', 'estado', 'movimiento', 'saldoDisponible'];
  dataSource = new MatTableDataSource<any>();
  

  constructor(private movimientoService: MovimientosService){};

  ngOnInit(): void {
    this.form = new FormGroup({
      'start': new FormControl(new Date()),
      'end': new FormControl(new Date()),
      'clientId': new FormControl(null),
    });
  }

  fecha(fecha:Date): string{
    let date = new Date(fecha);
    
    return `${date.getFullYear()}-${String(date.getMonth() + 1).padStart(2, '0')}-${String(date.getDate()).padStart(2, '0')}`;
  }

  buscar(){
    let movimiento = new Movimiento();
    movimiento.fecha = this.fecha(this.form.value['start']);
    movimiento.fechaRango = this.fecha(this.form.value['end']);
    movimiento.clienteId = this.form.value['clientId'];
    this.movimientoService.listar(movimiento).subscribe(data =>{
      this.dataSource = new MatTableDataSource(data);
    });
  }

  generarPDF() {
    const doc = new jsPDF();

    const columnas = [
      { title: "Fecha", dataKey: "fecha" },
      { title: "Cliente", dataKey: "cliente" },
      { title: "Numero Cuenta", dataKey: "numeroCuenta" },
      { title: "Tipo", dataKey: "tipo" },
      { title: "Saldo Inicial", dataKey: "saldoInicial" },
      { title: "Movimiento", dataKey: "movimiento" },
      { title: "Saldo Disponible", dataKey: "saldoDisponible" }
    ];

    const filas = this.dataSource.data.map((fila: any) => ({
      fecha: fila.fecha,
      cliente: fila.cliente,
      numeroCuenta: fila.numeroCuenta,
      tipo: fila.tipo,
      saldoInicial: fila.saldoInicial,
      movimiento: fila.movimiento,
      saldoDisponible: fila.saldoDisponible
    }));

    autoTable(doc, {
      columns: columnas,
      body: filas,
      didDrawPage: (dataArg) => { 
        doc.text('Reporte de Movimientos', dataArg.settings.margin.left, 10);
      }
    });

    doc.save('reporte.pdf');
  }
}
