import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cliente } from '../model/Cliente';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private url = `http://localhost:8080/clientes`;
  mensajeCambio = new Subject<string>();

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<any>(`${this.url}/listar`);
  }  

  obtener(id:any){
    return this.http.get<any>(`${this.url}/obtener/${id}`);
  }
  
  guardar(cliente: Cliente){
    return this.http.post(`${this.url}/insertar`, cliente);
  }

  editar(cliente: Cliente){
    return this.http.put(`${this.url}/editar`, cliente);
  }

  eliminar(id:any){
    return this.http.delete<any>(`${this.url}/eliminar/${id}`);
  }
}
