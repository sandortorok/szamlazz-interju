import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { Router, RouterModule } from '@angular/router';
import { HttpService } from '../../services/http-service';
import { ReceiptRequest, Item } from '../../dto/receipt-request';
import { ReceiptResponse } from '../../dto/receipt-response';
import { VAT_RATES, PAYMENT_METHODS, CURRENCIES, DEFAULT_VALUES } from '../../utils/constants';
import { Wrapper } from '../../shared/wrapper/wrapper';
import { ReceiptBasicInfo } from './components/receipt-basic-info/receipt-basic-info';
import { ReceiptItem } from './components/receipt-item/receipt-item';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-uj-nyugta',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatIconModule,
    RouterModule,
    Wrapper,
    ReceiptBasicInfo,
    ReceiptItem,
  ],
  templateUrl: './uj-nyugta.html',
  styleUrl: './uj-nyugta.css',
})
export class UjNyugta {
  receiptForm: FormGroup;

  readonly vatRates = VAT_RATES;
  readonly paymentMethods = PAYMENT_METHODS;
  readonly currencies = CURRENCIES;

  constructor(
    private fb: FormBuilder,
    private httpService: HttpService,
    private router: Router
  ) {
    this.receiptForm = this.fb.group({
      prefix: [DEFAULT_VALUES.prefix, Validators.required],
      paymentMethod: [DEFAULT_VALUES.paymentMethod, Validators.required],
      currency: [DEFAULT_VALUES.currency, Validators.required],
      comment: [''],
      pdfDownload: [DEFAULT_VALUES.pdfDownload, Validators.required],
      items: this.fb.array([this.createItem()]),
    });
  }

  get items(): FormArray {
    return this.receiptForm.get('items') as FormArray;
  }

  createItem(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      quantity: [DEFAULT_VALUES.quantity, [Validators.required, Validators.min(0.01)]],
      unit: [DEFAULT_VALUES.unit, Validators.required],
      netUnitPrice: [DEFAULT_VALUES.netUnitPrice, [Validators.required, Validators.min(0)]],
      vatRate: [DEFAULT_VALUES.vatRate, Validators.required],
      comment: [''],
    });
  }

  addItem(): void {
    this.items.push(this.createItem());
  }

  removeItem(index: number): void {
    if (this.items.length > 1) {
      this.items.removeAt(index);
    }
  }

  getItemFormGroup(index: number): FormGroup {
    return this.items.at(index) as FormGroup;
  }

  getGrossSum(): number {
    let total = 0;
    for (let i = 0; i < this.items.length; i++) {
      const totals = this.calculateItemTotals(i);
      total += totals.grossTotal;
    }
    return total;
  }

  calculateItemTotals(index: number): { netTotal: number; vatAmount: number; grossTotal: number } {
    const item = this.items.at(index).value;
    const netTotal = item.quantity * item.netUnitPrice;
    const vatRate = parseFloat(item.vatRate) / 100;
    const vatAmount = netTotal * vatRate;
    const grossTotal = netTotal + vatAmount;
    
    return { netTotal, vatAmount, grossTotal };
  }
  protected onSubmit(): void {
    if (this.receiptForm.invalid) {
      return;
    }

    const formValue = this.receiptForm.value;
    
    // Tételek összeállítása
    const items: Item[] = formValue.items.map((item: any, index: number) => {
      const totals = this.calculateItemTotals(index);
      return {
        name: item.name,
        quantity: parseFloat(item.quantity.toFixed(2)),
        unit: item.unit,
        netUnitPrice: parseFloat(item.netUnitPrice.toFixed(2)),
        vatRate: item.vatRate,
        netTotal: parseFloat(totals.netTotal.toFixed(2)),
        vatAmount: parseFloat(totals.vatAmount.toFixed(2)),
        grossTotal: Math.round(totals.grossTotal),
        comment: item.comment || undefined,
      };
    });

    const request: ReceiptRequest = {
      settings: {
        apiKey: environment.szamlazzCredentials.apiKey,
        pdfDownload: formValue.pdfDownload,
      },
      header: {
        prefix: formValue.prefix,
        paymentMethod: formValue.paymentMethod,
        currency: formValue.currency,
        comment: formValue.comment || undefined,
      },
      items: {
        items: items,
      },
    };

    this.httpService.createReceipt(request).subscribe({
      next: (response: ReceiptResponse) => {
        if (response.success) {
          this.router.navigate(['/']);
        } else {
          alert(response.errorMessage || 'Hiba történt a nyugta létrehozása során!');
        }
      },
      error: (error) => {
        const errorMessage = error.error?.errorMessage || error.error?.hibauzenet || 'Hiba történt a nyugta létrehozása során!';
        alert(errorMessage);
      },
    });
  }
}
