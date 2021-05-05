import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './componentes/login/login.component';
import { InicioComponent } from './componentes/inicio/inicio.component';
import { InvalidoComponent } from './componentes/invalido/invalido.component';

const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'invalido', component: InvalidoComponent },
    { path: 'inicio', component: InicioComponent },
    { path: '', redirectTo: '/login', pathMatch: 'full' },
    { path: '**', redirectTo: '/login', pathMatch: 'full' },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {}
