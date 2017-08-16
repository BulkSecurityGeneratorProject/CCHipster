import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AgiosCase } from './agios-case.model';
import { AgiosCasePopupService } from './agios-case-popup.service';
import { AgiosCaseService } from './agios-case.service';

@Component({
    selector: 'jhi-agios-case-dialog',
    templateUrl: './agios-case-dialog.component.html'
})
export class AgiosCaseDialogComponent implements OnInit {

    agiosCase: AgiosCase;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private agiosCaseService: AgiosCaseService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.agiosCase.id !== undefined) {
            this.subscribeToSaveResponse(
                this.agiosCaseService.update(this.agiosCase));
        } else {
            this.subscribeToSaveResponse(
                this.agiosCaseService.create(this.agiosCase));
        }
    }

    private subscribeToSaveResponse(result: Observable<AgiosCase>) {
        result.subscribe((res: AgiosCase) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: AgiosCase) {
        this.eventManager.broadcast({ name: 'agiosCaseListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError(error) {
        try {
            error.json();
        } catch (exception) {
            error.message = error.text();
        }
        this.isSaving = false;
        this.onError(error);
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}

@Component({
    selector: 'jhi-agios-case-popup',
    template: ''
})
export class AgiosCasePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private agiosCasePopupService: AgiosCasePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.agiosCasePopupService
                    .open(AgiosCaseDialogComponent as Component, params['id']);
            } else {
                this.agiosCasePopupService
                    .open(AgiosCaseDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
