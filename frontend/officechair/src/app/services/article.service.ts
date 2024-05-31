import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ArticleViewDto } from '../models/articleDto/articleViewDto';
import { ArticleUpdateDto } from '../models/articleDto/articleUpdateDto';
import { ArticleCreateDto } from '../models/articleDto/articleCreateDto';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {


  apiUrl = environment.apiArticle;
  constructor(private http: HttpClient) { }

  getArticles(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getOneArticle(id: String): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  addArticle(newArticle: ArticleCreateDto ) {
    return this.http.post(this.apiUrl , newArticle);
  }

  deleteArticle(id: String): Observable<any>{
    return this.http.delete(this.apiUrl +"/"+ id);
  }

  updateArticle(updateArticle: ArticleUpdateDto ): Observable<any> {
    return this.http.put(this.apiUrl, updateArticle);
  }



}





