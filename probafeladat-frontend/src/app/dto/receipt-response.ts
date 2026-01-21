export interface ReceiptSummaryDto {
  id: number;
  receiptNumber: string;
  date: string;
  totalNet: number;
  totalGross: number;
}

// Főválasz a Számlázz.hu API-tól
export interface ReceiptResponse {
  success: boolean;
  errorCode?: number;
  errorMessage?: string;
  receiptPdf?: string;
  receipt?: Receipt;
}

export interface Receipt {
  base: Base;
  items: ResponseItems;
  payments?: ResponsePayments;
  totals: Totals;
}

export interface Base {
  id: number;
  callId?: string;
  receiptNumber: string;
  type: string;
  cancelled: boolean;
  cancelledReceiptNumber?: string;
  date: string;
  paymentMethod: string;
  currency: string;
  exchangeBank?: string;
  exchangeRate?: number;
  comment?: string;
  ledgerCustomer?: string;
  test: boolean;
}

export interface ResponseItems {
  items: ResponseItem[];
}

export interface ResponseItem {
  name: string;
  itemId?: string;
  netUnitPrice: number;
  quantity: number;
  unit: string;
  netTotal: number;
  vatType?: string;
  vatRate: number;
  vatAmount: number;
  grossTotal: number;
  ledger?: ResponseItemLedger;
}

export interface ResponseItemLedger {
  accountNumber?: string;
  costCenter?: string;
  project?: string;
}

export interface ResponsePayments {
  payments: ResponsePayment[];
}

export interface ResponsePayment {
  paymentTool: string;
  amount: number;
  description?: string;
}

export interface Totals {
  vatRateSummaries: VatRateSummary[];
  totalSum: TotalSum;
}

export interface VatRateSummary {
  vatType?: string;
  vatRate: number;
  netTotal: number;
  vatAmount: number;
  grossTotal: number;
}

export interface TotalSum {
  netTotal: number;
  vatTotal: number;
  grossTotal: number;
}