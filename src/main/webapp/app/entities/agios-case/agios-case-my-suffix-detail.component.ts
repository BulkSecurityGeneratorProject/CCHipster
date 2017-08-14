import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager  } from 'ng-jhipster';

import { AgiosCaseMySuffix } from './agios-case-my-suffix.model';
import { AgiosCaseMySuffixService } from './agios-case-my-suffix.service';

@Component({
    selector: 'jhi-agios-case-my-suffix-detail',
    templateUrl: './agios-case-my-suffix-detail.component.html'
})
export class AgiosCaseMySuffixDetailComponent implements OnInit, OnDestroy {

    agiosCase: AgiosCaseMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private agiosCaseService: AgiosCaseMySuffixService,
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
