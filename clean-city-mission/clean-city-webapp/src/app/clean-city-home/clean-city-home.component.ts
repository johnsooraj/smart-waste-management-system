import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../common.service';

@Component({
  selector: 'app-clean-city-home',
  templateUrl: './clean-city-home.component.html',
  styleUrls: ['./clean-city-home.component.css']
})
export class CleanCityHomeComponent implements OnInit {

  binList = Array<any>();
  binForModal: any;

  lat: number = 10.016237;
  lng: number = 76.676647;

  constructor(
    private modalService: NgbModal,
    private commonService: CommonService
  ) { }

  ngOnInit() {
    this.commonService.getAllBinDetails().subscribe(allBins => {
      this.binList = allBins;
    });
  }

  openGraphModal(content: any, binData: any) {
    this.binForModal = binData;
    this.modalService.open(
      content,
      {
        ariaLabelledBy: 'modal-basic-title',
        size: 'lg'
      }).result.then((result) => {

      }, (reason) => {

      });
  }

  openAddBinModal(content: any) {
    this.modalService.open(
      content,
      {
        ariaLabelledBy: 'modal-basic-title',
        size: 'lg'
      }).result.then((result) => {

      }, (reason) => {

      });
  }

}
