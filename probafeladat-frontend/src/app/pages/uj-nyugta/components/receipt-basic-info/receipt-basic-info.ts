import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatCheckboxModule } from '@angular/material/checkbox';

@Component({
  selector: 'app-receipt-basic-info',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatCheckboxModule,
  ],
  templateUrl: './receipt-basic-info.html',
  styleUrl: './receipt-basic-info.css',
})
export class ReceiptBasicInfo {
  @Input() formGroup!: FormGroup;
  @Input() paymentMethods: readonly string[] = [];
  @Input() currencies: readonly string[] = [];
}
