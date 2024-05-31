import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ArticleTypeCreateDto } from '../models/artcleTypeDto/articleTypeCreateDto';
import { ArticleTypeUpdateDto } from '../models/artcleTypeDto/articleTypeUpdateDto';

@Injectable({
  providedIn: 'root'
})
export class ArticleTypeService {


  apiUrl = environment.apiArticleType;
  constructor(private http: HttpClient) { }

  getArticleTypes(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getOneArticleType(id: String): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  addArticleType(newArticleType: ArticleTypeCreateDto ) {
    return this.http.post(this.apiUrl , newArticleType);
  }

  deleteArticleType(id: String): Observable<any>{
    return this.http.delete(this.apiUrl +"/"+ id);
  }

  updateArticleType(updateArticleType: ArticleTypeUpdateDto ): Observable<any> {
    return this.http.put(this.apiUrl, updateArticleType);
  }
}
