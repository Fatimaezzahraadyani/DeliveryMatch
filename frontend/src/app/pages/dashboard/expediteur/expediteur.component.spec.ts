import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExpediteurComponent } from './expediteur.component';

describe('ExpediteurComponent', () => {
  let component: ExpediteurComponent;
  let fixture: ComponentFixture<ExpediteurComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ExpediteurComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExpediteurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
