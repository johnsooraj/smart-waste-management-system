import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { CleanCityHomeComponent } from './clean-city-home/clean-city-home.component';
import { CityGuard } from './city.guard';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
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
