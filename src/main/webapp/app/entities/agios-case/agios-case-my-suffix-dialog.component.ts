import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { AgiosCaseMySuffix } from './agios-case-my-suffix.model';
import { AgiosCaseMySuffixPopupService } from './agios-case-my-suffix-popup.service';
import { AgiosCaseMySuffixService } from './agios-case-my-suffix.service';

@Component({
    selector: 'jhi-agios-case-my-suffix-dialog',
    templateUrl: './agios-case-my-suffix-dialog.component.html'
})
export class AgiosCaseMySuffixDialogComponent implements OnInit {

    agiosCase: AgiosCaseMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private alertService: JhiAlertService,
        private agiosCaseService: AgiosCaseMySuffixService,
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

    private subscribeToSaveResponse(result: Observable<AgiosCaseMySuffix>) {
        result.subscribe((res: AgiosCaseMySuffix) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError(res));
    }

    private onSaveSuccess(result: AgiosCaseMySuffix) {
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
    selector: 'jhi-agios-case-my-suffix-popup',
    template: ''
})
export class AgiosCaseMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private agiosCasePopupService: AgiosCaseMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.agiosCasePopupService
                    .open(AgiosCaseMySuffixDialogComponent as Component, params['id']);
            } else {
                this.agiosCasePopupService
                    .open(AgiosCaseMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
