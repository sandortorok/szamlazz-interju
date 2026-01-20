import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { DataTable, TableColumn } from '../../../../shared/data-table/data-table';
import { ReceiptItemEntity } from '../../../../dto/receipt-db-entity';

@Component({
  selector: 'app-receipt-items-card',
  standalone: true,
  imports: [CommonModule, MatCardModule, DataTable],
  templateUrl: './receipt-items-card.html',
  styleUrl: './receipt-items-card.css',
})
export class ReceiptItemsCard {
  @Input() items: ReceiptItemEntity[] = [];
  @Input() columns: TableColumn[] = [];
}
