import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AgiosCaseMySuffixComponent } from './agios-case-my-suffix.component';
import { AgiosCaseMySuffixDetailComponent } from './agios-case-my-suffix-detail.component';
import { AgiosCaseMySuffixPopupComponent } from './agios-case-my-suffix-dialog.component';
import { AgiosCaseMySuffixDeletePopupComponent } from './agios-case-my-suffix-delete-dialog.component';

export const agiosCaseRoute: Routes = [
    {
        path: 'agios-case-my-suffix',
        component: AgiosCaseMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'agios-case-my-suffix/:id',
        component: AgiosCaseMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const agiosCasePopupRoute: Routes = [
    {
        path: 'agios-case-my-suffix-new',
        component: AgiosCaseMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'agios-case-my-suffix/:id/edit',
        component: AgiosCaseMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'agios-case-my-suffix/:id/delete',
        component: AgiosCaseMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
