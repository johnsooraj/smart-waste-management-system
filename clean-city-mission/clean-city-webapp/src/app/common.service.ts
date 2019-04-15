import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  userLoginData: any;

  constructor(
    private http: HttpClient,
    private router: Router,
  ) { }

  userLogin(data1: any) {
    this.http.post("/api/user/login", data1).subscribe((data) => {
      if (data) {
        this.userLoginData = data;
      } else {
        console.log('login failed!')
      }
    });
    return false;
  }

  getSingleUserData(id: string): Observable<any> {
    return this.http.get('/api/user/' + id);
  }

  getSingleBinData(id: string): Observable<any> {
    return this.http.get('/api/bin/' + id);
  }

  saveBinData(data: any): Observable<any> {
    return this.http.post('/api/bin', data);
  }

  getAllBinDetails(): Observable<any> {
    return this.http.get('/api/bin');
  }

  getAllUserDetails(): Observable<any> {
    return this.http.get('/api/user');
  }

  getBinTrackByBinId(binId: string): Observable<any> {
    return this.http.get('/api/bin/bintrack/' + binId);
  }


}
