import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { AgiosCase } from './agios-case.model';
import { AgiosCaseService } from './agios-case.service';

@Component({
    selector: 'jhi-agios-case-detail',
    templateUrl: './agios-case-detail.component.html'
})
export class AgiosCaseDetailComponent implements OnInit, OnDestroy {

    agiosCase: AgiosCase;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private agiosCaseService: AgiosCaseService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInAgiosCases();
    }

    load(id) {
        this.agiosCaseService.find(id).subscribe((agiosCase) => {
            this.agiosCase = agiosCase;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInAgiosCases() {
        this.eventSubscriber = this.eventManager.subscribe(
            'agiosCaseListModification',
            (response) => this.load(this.agiosCase.id)
        );
    }
}
