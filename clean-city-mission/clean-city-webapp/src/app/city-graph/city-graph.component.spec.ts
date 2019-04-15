import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CityGraphComponent } from './city-graph.component';

describe('CityGraphComponent', () => {
  let component: CityGraphComponent;
  let fixture: ComponentFixture<CityGraphComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CityGraphComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CityGraphComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
