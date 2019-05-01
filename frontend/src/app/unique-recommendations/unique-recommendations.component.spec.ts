import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { UniqueRecommendationsComponent } from './unique-recommendations.component';

describe('UniqueRecommendationsComponent', () => {
  let component: UniqueRecommendationsComponent;
  let fixture: ComponentFixture<UniqueRecommendationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UniqueRecommendationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UniqueRecommendationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


});
