import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ArticleBrandCreateDto } from '../models/articleBrandDto/articleBrandCreateDto';
import { ArticleBrandUpdateDto } from '../models/articleBrandDto/articleBrandUpdateDto';

@Injectable({
  providedIn: 'root'
})
export class ArticleBrandService {

  apiUrl = environment.apiArticleBrand;
  constructor(private http: HttpClient) { }

  getArticleBrands(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getOneArticleBrand(id: String): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`);
  }

  addArticleBrand(newArticleBrand: ArticleBrandCreateDto ) {
    return this.http.post(this.apiUrl , newArticleBrand);
  }

  deleteArticleBrand(id: String): Observable<any>{
    return this.http.delete(this.apiUrl +"/"+ id);
  }

  updateArticleBrand(updateArticleBrand: ArticleBrandUpdateDto ): Observable<any> {
    return this.http.put(this.apiUrl, updateArticleBrand);
  }
}
