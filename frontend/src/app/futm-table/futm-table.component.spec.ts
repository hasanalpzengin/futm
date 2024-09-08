import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FutmTableComponent } from './futm-table.component';

describe('FutmTableComponent', () => {
  let component: FutmTableComponent;
  let fixture: ComponentFixture<FutmTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FutmTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FutmTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
