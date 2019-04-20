import { Component, OnInit, ViewChild, ElementRef, EventEmitter, Output } from '@angular/core';
import { google } from '@agm/core/services/google-maps-types';
import { CommonService } from '../common.service';

@Component({
  selector: 'app-google-maps',
  templateUrl: './google-maps.component.html',
  styleUrls: ['./google-maps.component.css']
})
export class GoogleMapsComponent implements OnInit {

  @ViewChild('myGoogleMapEle') myGoogleMapEle: any;

  lat: Number = 10.001240;
  lng: number = 76.683964;

  constructor(
    private commonService: CommonService
  ) { }

  ngOnInit() {
    this.commonService.getAllBinDetails().subscribe(allBins => {
      console.log(allBins)
    });
  }

  leftButtonClick(event: Event) {
    this.myGoogleMapEle
  }

  rightButtonClick(event: Event) {
    let newBinData = {
      lat: this.myGoogleMapEle.latitude,
      lng: this.myGoogleMapEle.longitude
    }
  }

}
