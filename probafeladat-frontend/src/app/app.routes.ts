import { Routes } from '@angular/router';
import { NyugtaLista } from './pages/nyugta-lista/nyugta-lista';
import { UjNyugta } from './pages/uj-nyugta/uj-nyugta';
import { NyugtaReszletek } from './pages/nyugta-reszletek/nyugta-reszletek';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/receipts',
    pathMatch: 'full',
  },
  {
    path: 'receipts',
    component: NyugtaLista,
  },
  {
    path: 'receipts/:id',
    component: NyugtaReszletek,
  },
  {
    path: 'new-receipt',
    component: UjNyugta
  }
];
