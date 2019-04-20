import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AgmCoreModule } from '@agm/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { CleanCityHomeComponent } from './clean-city-home/clean-city-home.component';
import { CommonService } from './common.service';
import { CityGraphComponent } from './city-graph/city-graph.component';
import { DatePipe } from '@angular/common';
import { UsersComponent } from './users/users.component';
import { GoogleMapsComponent } from './google-maps/google-maps.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    CleanCityHomeComponent,
    CityGraphComponent,
    UsersComponent,
    GoogleMapsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyCr8qgQC_waV6aPSgqM52YQ-1wdExSFeLY'
    })
  ],
  providers: [
    CommonService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
