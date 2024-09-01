import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientComponent } from './views/client/client.component';
import { InsertClientComponent } from './views/client/insert-client/insert-client.component';
import { CuentaComponent } from './views/cuenta/cuenta.component';
import { InsertCuentaComponent } from './views/cuenta/insert-cuenta/insert-cuenta.component';
import { MovimientoComponent } from './views/movimiento/movimiento.component';

const routes: Routes = [
  {path: 'clientes', component: ClientComponent,
    children: [
      { path: "insertar", component: InsertClientComponent},
    ],
  },
  {path: 'cuentas', component: CuentaComponent,
    children: [
      { path: "insertar", component: InsertCuentaComponent},
    ],
  },
  {path: 'movimientos', component: MovimientoComponent,
    children: [
      { path: "insertar", component: InsertCuentaComponent},
    ],
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
