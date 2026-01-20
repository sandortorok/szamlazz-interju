import { Component, signal, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { HttpService } from '../../services/http-service';
import { CurrencyFormatterPipe } from '../../pipes/currency-formatter.pipe';
import { DateFormatterPipe } from '../../pipes/date-formatter.pipe';
import { Wrapper } from '../../shared/wrapper/wrapper';
import { DataTable, TableColumn } from '../../shared/data-table/data-table';
import { ReceiptSummaryDto } from '../../dto/receipt-response';

@Component({
  selector: 'app-nyugta-lista',
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    Wrapper,
    DataTable,
  ],
  templateUrl: './nyugta-lista.html',
  styleUrl: './nyugta-lista.css',
})
export class NyugtaLista implements OnInit {
  protected readonly receipts = signal<ReceiptSummaryDto[]>([]);
  
  private readonly columnDefinitions: TableColumn[] = [
    { key: 'receiptNumber', label: 'Nyugtaszám' },
    { key: 'date', label: 'Keltezés' },
    { key: 'totalNet', label: 'Nettó összeg' },
    { key: 'totalGross', label: 'Bruttó összeg' },
    {
      key: 'actions',
      label: 'Műveletek',
      type: 'action-link',
      actionIcon: 'visibility',
    },
  ];

  private readonly currencyPipe = new CurrencyFormatterPipe();
  private readonly datePipe = new DateFormatterPipe();

  protected readonly columns: TableColumn[] = this.columnDefinitions.map(col => {
    const enhancements: Record<string, Partial<TableColumn>> = {
      date: { formatter: (value: string) => this.datePipe.transform(value) },
      totalNet: { formatter: (value: number) => this.currencyPipe.transform(value) },
      totalGross: { formatter: (value: number) => this.currencyPipe.transform(value) },
      actions: { routerLink: (row: ReceiptSummaryDto) => ['/receipts', row.id] }
    };

    return { ...col, ...enhancements[col.key] };
  });

  constructor(private httpService: HttpService) {}

  ngOnInit(): void {
    this.loadReceipts();
  }

  private loadReceipts(): void {
    this.httpService.getAllReceipts().subscribe({
      next: (data) => {
        this.receipts.set(data);
      },
      error: (error) => {
        alert('Hiba történt a nyugták betöltése során!');
      },
    });
  }
}