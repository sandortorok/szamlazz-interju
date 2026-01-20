import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'wrapper',
  imports: [
    CommonModule,
    MatCardModule,
  ],
  templateUrl: './wrapper.html',
  styleUrl: './wrapper.css',
})
export class Wrapper {
  @Input() title: string = '';
}
