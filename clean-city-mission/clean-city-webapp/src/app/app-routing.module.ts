import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CleanCityHomeComponent } from './clean-city-home/clean-city-home.component';
import { CityGuard } from './city.guard';
import { UsersComponent } from './users/users.component';
import { GoogleMapsComponent } from './google-maps/google-maps.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/maps',
    pathMatch: 'full'
  },
  {
    path: 'maps',
    component: GoogleMapsComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: CleanCityHomeComponent,
    canActivate: [CityGuard]
  },
  {
    path: 'users',
    component: UsersComponent,
    canActivate: [CityGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
