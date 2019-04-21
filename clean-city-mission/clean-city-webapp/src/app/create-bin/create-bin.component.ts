import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CommonService } from '../common.service';

@Component({
  selector: 'app-create-bin',
  templateUrl: './create-bin.component.html',
  styleUrls: ['./create-bin.component.css']
})
export class CreateBinComponent implements OnInit {

  @ViewChild('contentAddBin') modalElement: ElementRef;

  elRef: ElementRef;

  formGroup: FormGroup;
  id: FormControl;
  binCurrentCapacity: FormControl;
  addressLine1: FormControl;
  addressLine2: FormControl;
  addressLine3: FormControl;
  pincode: FormControl;
  latitude: FormControl;
  longitude: FormControl;
  flag: FormControl;

  constructor(
    private modalService: NgbModal,
    private router: Router,
    private commonService: CommonService
  ) {
  }

  ngOnInit() {
    this.createFormControls();
    this.createFormGroup();
    if (this.commonService.createBinData) {
      this.id.setValue(this.commonService.createBinData.id);
      this.binCurrentCapacity.setValue(this.commonService.createBinData.binCurrentCapacity);
      this.addressLine1.setValue(this.commonService.createBinData.addressLine1);
      this.addressLine2.setValue(this.commonService.createBinData.addressLine2);
      this.addressLine3.setValue(this.commonService.createBinData.addressLine3);
      this.pincode.setValue(this.commonService.createBinData.pincode);
      this.latitude.setValue(this.commonService.createBinData.latitude);
      this.longitude.setValue(this.commonService.createBinData.longitude);
      this.flag.setValue(this.commonService.createBinData.flag);
    }

  }

  createFormControls() {
    this.id = new FormControl('');
    this.binCurrentCapacity = new FormControl('');
    this.addressLine1 = new FormControl('');
    this.addressLine2 = new FormControl('');
    this.addressLine3 = new FormControl('');
    this.pincode = new FormControl('');
    this.latitude = new FormControl('');
    this.longitude = new FormControl('');
    this.flag = new FormControl('');
  }

  createFormGroup() {
    this.formGroup = new FormGroup({
      id: this.id,
      binCurrentCapacity: this.binCurrentCapacity,
      addressLine1: this.addressLine1,
      addressLine2: this.addressLine2,
      addressLine3: this.addressLine3,
      pincode: this.pincode,
      latitude: this.latitude,
      longitude: this.longitude,
      flag: this.flag
    });
  }

  addBinFormSubmit() {
    if (this.formGroup.valid) {
      this.commonService.createNewBin(this.formGroup.value).subscribe((data) => {
        this.commonService.createBinData = data;
        this.modalService.dismissAll();
      });
    }
  }

}
