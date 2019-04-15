import { Component, OnInit } from '@angular/core';
import { CommonService } from '../common.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  usersList = Array<any>();

  constructor(
    private commonService: CommonService
  ) { }

  ngOnInit() {
    this.commonService.getAllUserDetails().subscribe((list) => {
      this.usersList = list;
    });
  }

}
