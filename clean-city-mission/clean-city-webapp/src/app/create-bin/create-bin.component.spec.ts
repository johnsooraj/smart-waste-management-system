import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateBinComponent } from './create-bin.component';

describe('CreateBinComponent', () => {
  let component: CreateBinComponent;
  let fixture: ComponentFixture<CreateBinComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateBinComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateBinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
