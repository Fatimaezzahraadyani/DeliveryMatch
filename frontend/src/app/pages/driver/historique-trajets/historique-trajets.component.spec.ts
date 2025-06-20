import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoriqueTrajetsComponent } from './historique-trajets.component';

describe('HistoriqueTrajetsComponent', () => {
  let component: HistoriqueTrajetsComponent;
  let fixture: ComponentFixture<HistoriqueTrajetsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HistoriqueTrajetsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoriqueTrajetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
