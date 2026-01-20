import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { DateFormatterPipe } from '../../../../pipes/date-formatter.pipe';
import { ReceiptEntity } from '../../../../dto/receipt-db-entity';

@Component({
  selector: 'app-receipt-info-card',
  standalone: true,
  imports: [CommonModule, MatCardModule, DateFormatterPipe],
  templateUrl: './receipt-info-card.html',
  styleUrl: './receipt-info-card.css',
})
export class ReceiptInfoCard {
  @Input() receipt!: ReceiptEntity;
}
