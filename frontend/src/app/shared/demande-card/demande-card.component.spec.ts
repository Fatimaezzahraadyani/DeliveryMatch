import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandeCardComponent } from './demande-card.component';

describe('DemandeCardComponent', () => {
  let component: DemandeCardComponent;
  let fixture: ComponentFixture<DemandeCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DemandeCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DemandeCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
