import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleTypeDialogComponent } from './article-type-dialog.component';

describe('ArticleTypeDialogComponent', () => {
  let component: ArticleTypeDialogComponent;
  let fixture: ComponentFixture<ArticleTypeDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticleTypeDialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ArticleTypeDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
