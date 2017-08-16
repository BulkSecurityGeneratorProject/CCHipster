import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AgiosCase } from './agios-case.model';
import { AgiosCasePopupService } from './agios-case-popup.service';
import { AgiosCaseService } from './agios-case.service';

@Component({
    selector: 'jhi-agios-case-delete-dialog',
    templateUrl: './agios-case-delete-dialog.component.html'
})
export class AgiosCaseDeleteDialogComponent {

    agiosCase: AgiosCase;

    constructor(
        private agiosCaseService: AgiosCaseService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.agiosCaseService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'agiosCaseListModification',
                content: 'Deleted an agiosCase'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-agios-case-delete-popup',
    template: ''
})
export class AgiosCaseDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private agiosCasePopupService: AgiosCasePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.agiosCasePopupService
                .open(AgiosCaseDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
