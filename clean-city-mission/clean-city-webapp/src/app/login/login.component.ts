import { Component, OnInit } from '@angular/core';
import { CommonService } from '../common.service';

class LoginModal {
  username: string;
  password: string;
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginData = new LoginModal();

  constructor(
    private commenService: CommonService
  ) { }

  ngOnInit() {
  }

  signInButtonClick() {
    this.commenService.userLogin(this.loginData);
  }
}