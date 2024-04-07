import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class PdfService {
  constructor(private http: HttpClient) { }

  private baseUrl = 'http://localhost:8080/api/v1/pdf'
  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token'); // Retrieve the token from localStorage
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return headers;
  }

  downloadPdf(id: number){
    this.http.get(`${this.baseUrl}/download/${id}`, { headers: this.getHeaders(), responseType: 'blob' })
        .subscribe({
          next: (blob) => {
            // Create a new Blob object using the response data
            const a = document.createElement('a');
            a.href = URL.createObjectURL(blob);
            a.download = 'transaction-report.pdf'; // Customize the file name
            document.body.appendChild(a); // Append the anchor to body
            a.click(); // Simulate a click on the anchor
            document.body.removeChild(a); // Clean up the DOM
            URL.revokeObjectURL(a.href); // Release the created URL
          },
          error: (error) => {
            console.error('There was an error during the download: ', error);
          },
          complete: () => console.log('Download complete')
        });
  }
}
