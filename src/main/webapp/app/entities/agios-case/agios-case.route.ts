import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { JhiPaginationUtil } from 'ng-jhipster';

import { AgiosCaseComponent } from './agios-case.component';
import { AgiosCaseDetailComponent } from './agios-case-detail.component';
import { AgiosCasePopupComponent } from './agios-case-dialog.component';
import { AgiosCaseDeletePopupComponent } from './agios-case-delete-dialog.component';

export const agiosCaseRoute: Routes = [
    {
        path: 'agios-case',
        component: AgiosCaseComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'agios-case/:id',
        component: AgiosCaseDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const agiosCasePopupRoute: Routes = [
    {
        path: 'agios-case-new',
        component: AgiosCasePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'agios-case/:id/edit',
        component: AgiosCasePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'agios-case/:id/delete',
        component: AgiosCaseDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'AgiosCases'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
