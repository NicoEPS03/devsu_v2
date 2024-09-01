import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Cuenta } from '../model/Cuenta';

@Injectable({
  providedIn: 'root'
})
export class CuentasService {

  private url = `http://localhost:8080/cuentas`;
  mensajeCambio = new Subject<string>();

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<any>(`${this.url}/listar`);
  }  

  obtener(id:any){
    return this.http.get<any>(`${this.url}/obtener/${id}`);
  }
  
  guardar(cuenta: Cuenta){
    return this.http.post(`${this.url}/insertar`, cuenta);
  }

  editar(cuenta: Cuenta){
    return this.http.put(`${this.url}/editar`, cuenta);
  }

  eliminar(id:any){
    return this.http.delete<any>(`${this.url}/eliminar/${id}`);
  }
}
