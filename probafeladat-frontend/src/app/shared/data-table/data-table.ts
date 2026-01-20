import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';

export interface TableColumn {
  key: string;
  label: string;
  formatter?: (value: any) => string;
  type?: 'text' | 'action-link';
  actionIcon?: string;
  routerLink?: (row: any) => any[];
}

@Component({
  selector: 'app-data-table',
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    RouterLink,
  ],
  templateUrl: './data-table.html',
  styleUrl: './data-table.css',
})
export class DataTable {
  @Input() dataSource: any[] = [];
  @Input() columns: TableColumn[] = [];
  @Input() tableClass: string = 'data-table';

  get displayedColumns(): string[] {
    return this.columns.map(col => col.key);
  }

  getCellValue(row: any, column: TableColumn): string {
    const value = row[column.key];
    return column.formatter ? column.formatter(value) : value;
  }
}
