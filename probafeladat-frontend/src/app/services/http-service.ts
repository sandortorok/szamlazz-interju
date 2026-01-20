import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ReceiptRequest } from '../dto/receipt-request';
import { ReceiptSummaryDto, ReceiptResponse } from '../dto/receipt-response';
import { ReceiptEntity } from '../dto/receipt-db-entity';

@Injectable({
  providedIn: 'root',
})
export class HttpService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = 'http://localhost:8080/api/receipts';


  testReceiptFromSample(): Observable<ReceiptResponse> {
    return this.http.get<ReceiptResponse>(`${this.apiUrl}/test`);
  }

  getAllReceipts(): Observable<ReceiptSummaryDto[]> {
    return this.http.get<ReceiptSummaryDto[]>(this.apiUrl);
  }

  getReceiptById(id: number): Observable<ReceiptEntity> {
    return this.http.get<ReceiptEntity>(`${this.apiUrl}/${id}`);
  }

  createReceipt(request: ReceiptRequest): Observable<ReceiptResponse> {
    return this.http.post<ReceiptResponse>(this.apiUrl, request);
  }
}
