import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormatter',
  standalone: true,
})
export class DateFormatterPipe implements PipeTransform {
  private readonly formatter = new Intl.DateTimeFormat('hu-HU');

  transform(date: Date | string | null | undefined): string {
    if (!date) {
      return '';
    }
    const dateObj = typeof date === 'string' ? new Date(date) : date;
    return this.formatter.format(dateObj);
  }
}

@Pipe({
  name: 'dateTimeFormatter',
  standalone: true,
})
export class DateTimeFormatterPipe implements PipeTransform {
  private readonly formatter = new Intl.DateTimeFormat('hu-HU', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
  });

  transform(date: Date | string | null | undefined): string {
    if (!date) {
      return '';
    }
    const dateObj = typeof date === 'string' ? new Date(date) : date;
    return this.formatter.format(dateObj);
  }
}
