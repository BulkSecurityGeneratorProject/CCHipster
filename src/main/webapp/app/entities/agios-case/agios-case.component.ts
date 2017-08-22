import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { AgiosCase } from './agios-case.model';
import { AgiosCaseService } from './agios-case.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-agios-case',
    templateUrl: './agios-case.component.html'
})
export class AgiosCaseComponent implements OnInit, OnDestroy {
agiosCases: AgiosCase[];
    currentAccount: any;
    eventSubscriber: Subscription;
    currentSearch: string;

    constructor(
        private agiosCaseService: AgiosCaseService,
        private alertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private activatedRoute: ActivatedRoute,
        private principal: Principal
    ) {
        this.currentSearch = activatedRoute.snapshot.params['search'] ? activatedRoute.snapshot.params['search'] : '';
    }

    loadAll() {
        if (this.currentSearch) {
            this.agiosCaseService.search({
                query: this.currentSearch,
                }).subscribe(
                    (res: ResponseWrapper) => this.agiosCases = res.json,
                    (res: ResponseWrapper) => this.onError(res.json)
                );
            return;
       }
        this.agiosCaseService.query().subscribe(
            (res: ResponseWrapper) => {
                this.agiosCases = res.json;
                this.currentSearch = '';
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }

    search(query) {
        if (!query) {
            return this.clear();
        }
        this.currentSearch = query;
        this.loadAll();
    }

    clear() {
        this.currentSearch = '';
        this.loadAll();
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

    trackId(index: number, item: AgiosCase) {
        return item.id;
    }
    registerChangeInAgiosCases() {
        this.eventSubscriber = this.eventManager.subscribe('agiosCaseListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }
}
