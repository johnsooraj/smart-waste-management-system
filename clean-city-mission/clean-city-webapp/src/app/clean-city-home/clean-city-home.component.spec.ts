import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CleanCityHomeComponent } from './clean-city-home.component';

describe('CleanCityHomeComponent', () => {
  let component: CleanCityHomeComponent;
  let fixture: ComponentFixture<CleanCityHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CleanCityHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CleanCityHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
