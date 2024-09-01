import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MaterialModule } from './_material/material/material.module';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ClientComponent } from './views/client/client.component';
import { InsertClientComponent } from './views/client/insert-client/insert-client.component';
import { DeleteClientComponent } from './views/client/delete-client/delete-client.component';
import { CuentaComponent } from './views/cuenta/cuenta.component';
import { DeleteCuentaComponent } from './views/cuenta/delete-cuenta/delete-cuenta.component';
import { InsertCuentaComponent } from './views/cuenta/insert-cuenta/insert-cuenta.component';
import { MovimientoComponent } from './views/movimiento/movimiento.component';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    InsertClientComponent,
    DeleteClientComponent,
    CuentaComponent,
    DeleteCuentaComponent,
    InsertCuentaComponent,
    MovimientoComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatNativeDateModule 
  ],
  providers: [
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
