export interface ReceiptRequest {
  settings: Settings;
  header: Header;
  items: Items;
  payments?: Payments;
}

export interface Settings {
  username?: string;
  password?: string;
  apiKey: string;
  pdfDownload: boolean;
}

export interface Header {
  callId?: string;
  prefix: string;
  paymentMethod: string;
  currency: string;
  exchangeRate?: number;
  exchangeBank?: string;
  comment?: string;
  pdfTemplate?: string;
  ledgerCustomer?: string;
}

export interface Items {
  items: Item[];
}

export interface Item {
  name: string;
  itemId?: string;
  quantity: number;
  unit: string;
  netUnitPrice: number;
  vatRate: string;
  netTotal: number;
  vatAmount: number;
  grossTotal: number;
  comment?: string;
}

export interface Payments {
  payments: Payment[];
}

export interface Payment {
  paymentTool: string;
  amount: number;
  description?: string;
}
