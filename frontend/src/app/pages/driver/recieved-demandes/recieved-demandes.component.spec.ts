import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecievedDemandesComponent } from './recieved-demandes.component';

describe('RecievedDemandesComponent', () => {
  let component: RecievedDemandesComponent;
  let fixture: ComponentFixture<RecievedDemandesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecievedDemandesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecievedDemandesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
