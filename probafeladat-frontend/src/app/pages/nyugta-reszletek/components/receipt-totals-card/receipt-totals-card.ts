import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { CurrencyFormatterPipe } from '../../../../pipes/currency-formatter.pipe';

@Component({
  selector: 'app-receipt-totals-card',
  standalone: true,
  imports: [CommonModule, MatCardModule, CurrencyFormatterPipe],
  templateUrl: './receipt-totals-card.html',
  styleUrl: './receipt-totals-card.css',
})
export class ReceiptTotalsCard {
  @Input() totalNet!: number;
  @Input() totalVat!: number;
  @Input() totalGross!: number;
}
