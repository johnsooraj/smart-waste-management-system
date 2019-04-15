import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import Chart from 'chart.js';
import { bind } from '@angular/core/src/render3';
import { DatePipe } from '@angular/common';
import { CommonService } from '../common.service';

@Component({
  selector: 'app-city-graph',
  templateUrl: './city-graph.component.html',
  styleUrls: ['./city-graph.component.css']
})
export class CityGraphComponent implements OnInit {

  @ViewChild('binTrackerChart') binTrackerElement: ElementRef;

  @Input() binDetail: any;
  binTrackerList = Array<any>();

  constructor(
    private commonService: CommonService,
    private datePipe: DatePipe
  ) { }

  ngOnInit() {
    this.commonService.getBinTrackByBinId(this.binDetail.id).subscribe((binTrack) => {
      this.binTrackerList = binTrack;
      this.setChartData(binTrack);
    });
  }

  setChartData(binTrack: any) {
    var myChart = new Chart(this.binTrackerElement.nativeElement.id, {
      type: 'line',
      data: {
        labels: this.getLableForChart(),
        datasets: [{
          label: 'bin usage ',
          data: this.getDataForChart(),
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
  }

  getLableForChart() {
    let lableArray = [];
    if (this.binTrackerList) {
      this.binTrackerList.forEach(obj => lableArray.push(this.datePipe.transform(obj.dateTime, 'M/d/yy, h:mm a')));
    }
    return lableArray;
  }

  getDataForChart() {
    let dataArray = [];
    if (this.binTrackerList) {
      this.binTrackerList.forEach(obj => dataArray.push(obj.filledQuantity));
    }
    return dataArray;
  }

}
