// Backend entitás megfelelője - részletes nyugta nézet
export interface ReceiptEntity {
  // Response fields
  id: number;
  success: boolean;
  errorCode?: number;
  errorMessage?: string;
  receiptPdf?: string;

  // Base fields
  receiptId?: number;
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

  // Totals
  totalNet: number;
  totalVat: number;
  totalGross: number;

  // Relations
  items: ReceiptItemEntity[];
  payments: PaymentEntity[];
  vatRateSummaries: VatRateSummaryEntity[];
}

// Nyugta tétel entitás
export interface ReceiptItemEntity {
  id: number;
  itemId?: string;
  name: string;
  quantity: number;
  unit: string;
  netUnitPrice: number;
  netTotal: number;
  vatType?: string;
  vatRate: number;
  vatAmount: number;
  grossTotal: number;
}

// Fizetés entitás
export interface PaymentEntity {
  id: number;
  paymentTool: string;
  amount: number;
  description?: string;
}

// ÁFA kulcs összesítés entitás
export interface VatRateSummaryEntity {
  id: number;
  vatType?: string;
  vatRate: number;
  netTotal: number;
  vatAmount: number;
  grossTotal: number;
}
