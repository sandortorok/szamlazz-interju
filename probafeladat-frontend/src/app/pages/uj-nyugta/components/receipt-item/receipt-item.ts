import { Component, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-receipt-item',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
  ],
  templateUrl: './receipt-item.html',
  styleUrl: './receipt-item.css',
})
export class ReceiptItem {
  @Input() itemGroup!: FormGroup;
  @Input() index!: number;
  @Input() canDelete: boolean = true;
  @Input() vatRates: readonly string[] = [];
  @Input() calculatedTotals!: { netTotal: number; vatAmount: number; grossTotal: number };
  
  @Output() remove = new EventEmitter<void>();

  onRemove(): void {
    this.remove.emit();
  }
}
