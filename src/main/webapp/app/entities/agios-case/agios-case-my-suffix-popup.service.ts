import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { AgiosCaseMySuffix } from './agios-case-my-suffix.model';
import { AgiosCaseMySuffixService } from './agios-case-my-suffix.service';

@Injectable()
export class AgiosCaseMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private agiosCaseService: AgiosCaseMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.agiosCaseService.find(id).subscribe((agiosCase) => {
                    this.ngbModalRef = this.agiosCaseModalRef(component, agiosCase);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.agiosCaseModalRef(component, new AgiosCaseMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    agiosCaseModalRef(component: Component, agiosCase: AgiosCaseMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.agiosCase = agiosCase;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
