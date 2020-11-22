import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { CitelSharedModule } from 'app/shared/shared.module';
import { CitelCoreModule } from 'app/core/core.module';
import { CitelAppRoutingModule } from './app-routing.module';
import { CitelHomeModule } from './home/home.module';
import { CitelEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    CitelSharedModule,
    CitelCoreModule,
    CitelHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    CitelEntityModule,
    CitelAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent],
})
export class CitelAppModule {}
