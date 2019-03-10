import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewpostingComponent } from './viewposting.component';

describe('ViewpostingComponent', () => {
  let component: ViewpostingComponent;
  let fixture: ComponentFixture<ViewpostingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewpostingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewpostingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
