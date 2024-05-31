import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleBrandDialogComponent } from './article-brand-dialog.component';

describe('ArticleBrandDialogComponent', () => {
  let component: ArticleBrandDialogComponent;
  let fixture: ComponentFixture<ArticleBrandDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticleBrandDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleBrandDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
