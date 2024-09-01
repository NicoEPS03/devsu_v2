import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Movimiento } from '../model/Movimiento';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MovimientosService {

  private url = `http://localhost:8080/movimientos`;
  mensajeCambio = new Subject<string>();

  constructor(private http: HttpClient) { }

  listar(movimiento: Movimiento): Observable<any> {
    return this.http.post(`${this.url}/reportes`,movimiento).pipe(
      map((response: any) => {
        return response;
      })
    );
  }  
  
  guardar(movimiento: Movimiento){
    return this.http.post(`${this.url}/realizarMovimiento`, movimiento);
  }
}
