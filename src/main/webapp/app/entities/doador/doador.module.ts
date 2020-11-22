import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CitelSharedModule } from 'app/shared/shared.module';
import { DoadorComponent } from './doador.component';
import { DoadorDetailComponent } from './doador-detail.component';
import { doadorRoute } from './doador.route';

@NgModule({
  imports: [CitelSharedModule, RouterModule.forChild(doadorRoute)],
  declarations: [DoadorComponent, DoadorDetailComponent],
})
export class CitelDoadorModule {}
