export const VAT_RATES = ['0', '5', '10', '27'] as const;

export const PAYMENT_METHODS = ['készpénz', 'bankkártya', 'átutalás', 'SZÉP kártya'] as const;

export const CURRENCIES = ['Ft', 'HUF', 'EUR', 'USD'] as const;

export const DEFAULT_VALUES = {
  prefix: 'PQRST',
  paymentMethod: 'készpénz',
  currency: 'Ft',
  vatRate: '10',
  unit: 'db',
  quantity: 1,
  netUnitPrice: 10000,
  pdfDownload: false,
} as const;
