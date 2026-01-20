import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'currencyFormatter',
  standalone: true,
})
export class CurrencyFormatterPipe implements PipeTransform {
  private readonly numberFormatter = new Intl.NumberFormat('hu-HU', {
    minimumFractionDigits: 0,
    maximumFractionDigits: 2,
  });

  transform(amount: number | null | undefined, currency: string = 'Ft'): string {
    if (amount === null || amount === undefined) {
      return '';
    }
    const formattedNumber = this.numberFormatter.format(amount);
    return currency ? `${formattedNumber} ${currency}` : formattedNumber;
  }
}
