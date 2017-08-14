import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { AgiosCaseMySuffix } from './agios-case-my-suffix.model';
import { AgiosCaseMySuffixPopupService } from './agios-case-my-suffix-popup.service';
import { AgiosCaseMySuffixService } from './agios-case-my-suffix.service';

@Component({
    selector: 'jhi-agios-case-my-suffix-delete-dialog',
    templateUrl: './agios-case-my-suffix-delete-dialog.component.html'
})
export class AgiosCaseMySuffixDeleteDialogComponent {

    agiosCase: AgiosCaseMySuffix;

    constructor(
        private agiosCaseService: AgiosCaseMySuffixService,
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
    selector: 'jhi-agios-case-my-suffix-delete-popup',
    template: ''
})
export class AgiosCaseMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private agiosCasePopupService: AgiosCaseMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.agiosCasePopupService
                .open(AgiosCaseMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
