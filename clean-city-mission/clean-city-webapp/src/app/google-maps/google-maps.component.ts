import { Component, OnInit, ViewChild, ElementRef, EventEmitter, Output } from '@angular/core';
import { google } from '@agm/core/services/google-maps-types';
import { CommonService } from '../common.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CreateBinComponent } from '../create-bin/create-bin.component';
import { WasteBin } from '../models/WasteBin';

@Component({
  selector: 'app-google-maps',
  templateUrl: './google-maps.component.html',
  styleUrls: ['./google-maps.component.css']
})
export class GoogleMapsComponent implements OnInit {

  @ViewChild('myGoogleMapEle') myGoogleMapEle: any;
  binLocations = Array<WasteBin>();

  lat: Number = 10.001240;
  lng: number = 76.683964;

  constructor(
    private commonService: CommonService,
    private modalService: NgbModal
  ) { }

  ngOnInit() {
    this.commonService.getAllBinDetails().subscribe(allBins => {
      allBins.forEach(element => {
        this.binLocations.push(element);
      });
    });
  }

  leftButtonClick(event: Event) {
    this.myGoogleMapEle
  }

  rightButtonClick(event: any) {
    let binData = new WasteBin();
    binData.longitude = event.coords.lng;
    binData.latitude = event.coords.lat;
    this.commonService.createBinData = binData;
    this.openAddBinModal();
    return false;
  }

  openAddBinModal() {
    this.modalService.open(
      CreateBinComponent,
      {
        ariaLabelledBy: 'modal-basic-title',
        size: 'lg'
      }).result.then((result) => {
        if (result == 'save') {
          this.binLocations.push(this.commonService.createBinData);
        }
        this.modalService.dismissAll();
      }, (reason) => {
        this.binLocations.push(this.commonService.createBinData);
        this.modalService.dismissAll();
      });
  }

}
