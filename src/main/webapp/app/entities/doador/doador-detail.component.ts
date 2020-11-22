import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDoador } from 'app/shared/model/doador.model';

@Component({
  selector: 'jhi-doador-detail',
  templateUrl: './doador-detail.component.html',
})
export class DoadorDetailComponent implements OnInit {
  doador: IDoador | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ doador }) => (this.doador = doador));
  }

  previousState(): void {
    window.history.back();
  }
}
