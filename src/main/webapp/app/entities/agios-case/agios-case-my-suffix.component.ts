import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { AgiosCaseMySuffix } from './agios-case-my-suffix.model';
import { AgiosCaseMySuffixService } from './agios-case-my-suffix.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-agios-case-my-suffix',
    templateUrl: './agios-case-my-suffix.component.html'
})
export class AgiosCaseMySuffixComponent implements OnInit, OnDestroy {
agiosCases: AgiosCaseMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private agiosCaseService: AgiosCaseMySuffixService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.agiosCaseService.query().subscribe(
            (res: ResponseWrapper) => {
                this.agiosCases = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInAgiosCases();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: AgiosCaseMySuffix) {
        return item.id;
    }
    registerChangeInAgiosCases() {
        this.eventSubscriber = this.eventManager.subscribe('agiosCaseListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
