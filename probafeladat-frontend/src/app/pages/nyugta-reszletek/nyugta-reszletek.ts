import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { HttpService } from '../../services/http-service';
import { Wrapper } from '../../shared/wrapper/wrapper';
import { CurrencyFormatterPipe } from '../../pipes/currency-formatter.pipe';
import { TableColumn } from '../../shared/data-table/data-table';
import { ReceiptInfoCard } from './components/receipt-info-card/receipt-info-card';
import { ReceiptItemsCard } from './components/receipt-items-card/receipt-items-card';
import { ReceiptTotalsCard } from './components/receipt-totals-card/receipt-totals-card';
import { ReceiptEntity } from '../../dto/receipt-db-entity';

@Component({
  selector: 'app-nyugta-reszletek',
  imports: [
    CommonModule,
    RouterModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatTableModule,
    Wrapper,
    ReceiptInfoCard,
    ReceiptItemsCard,
    ReceiptTotalsCard,
  ],
  templateUrl: './nyugta-reszletek.html',
  styleUrl: './nyugta-reszletek.css',
})
export class NyugtaReszletek implements OnInit {
  receipt = signal<ReceiptEntity | null>(null);
  
  private readonly currencyPipe = new CurrencyFormatterPipe();
  
  private readonly columnDefinitions = [
    { key: 'name', label: 'Megnevezés' },
    { key: 'quantity', label: 'Mennyiség' },
    { key: 'unit', label: 'Egység' },
    { key: 'netUnitPrice', label: 'Nettó egységár' },
    { key: 'vatRate', label: 'ÁFA' },
    { key: 'netTotal', label: 'Nettó' },
    { key: 'vatAmount', label: 'ÁFA összeg' },
    { key: 'grossTotal', label: 'Bruttó' },
  ];

  protected readonly itemColumns: TableColumn[] = this.columnDefinitions.map(col => {
    const enhancements: Record<string, Partial<TableColumn>> = {
      netUnitPrice: { formatter: (value: number) => this.currencyPipe.transform(value) },
      vatRate: { formatter: (value: number) => `${value}%` },
      netTotal: { formatter: (value: number) => this.currencyPipe.transform(value) },
      vatAmount: { formatter: (value: number) => this.currencyPipe.transform(value) },
      grossTotal: { formatter: (value: number) => this.currencyPipe.transform(value) },
    };

    return { ...col, ...enhancements[col.key] };
  });

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private httpService: HttpService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.loadReceipt(Number(id));
    }
  }

  private loadReceipt(id: number): void {
    this.httpService.getReceiptById(id).subscribe({
      next: (receipt) => {
        this.receipt.set(receipt);
      },
      error: (error) => {
        alert('Hiba történt a nyugta betöltése során!');
        this.router.navigate(['/receipts']);
      },
    });
  }

  downloadPdf(base64Pdf: string, receiptNumber: string): void {
    try {
      const byteCharacters = atob(base64Pdf);
      const byteNumbers = new Array(byteCharacters.length);
      for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i);
      }
      const byteArray = new Uint8Array(byteNumbers);
      
      const blob = new Blob([byteArray], { type: 'application/pdf' });
      
      const url = window.URL.createObjectURL(blob);
      const link = document.createElement('a');
      link.href = url;
      link.download = `${receiptNumber}.pdf`;
      link.click();
      
      window.URL.revokeObjectURL(url);
    } catch (error) {
      alert('Hiba történt a PDF letöltése során!');
    }
  }
}
