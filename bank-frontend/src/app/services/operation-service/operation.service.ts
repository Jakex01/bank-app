import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Operation} from "../../models/Operation";

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  private baseUrl = 'http://localhost:8080/api/v1/operation'

  constructor(
      private http: HttpClient
  ) { }
  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token'); // Retrieve the token from localStorage
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return headers;
  }
  getAllOperations(page: number, size: number): Observable<Operation[]> {
    console.log(size + "size");
    return this.http.get<Operation[]>(`${this.baseUrl}/all?page=${page}&size=${size}`, { headers: this.getHeaders() });
  }

}
