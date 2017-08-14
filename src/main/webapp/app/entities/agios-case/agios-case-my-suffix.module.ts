import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AgiosHipsterSharedModule } from '../../shared';
import {
    AgiosCaseMySuffixService,
    AgiosCaseMySuffixPopupService,
    AgiosCaseMySuffixComponent,
    AgiosCaseMySuffixDetailComponent,
    AgiosCaseMySuffixDialogComponent,
    AgiosCaseMySuffixPopupComponent,
    AgiosCaseMySuffixDeletePopupComponent,
    AgiosCaseMySuffixDeleteDialogComponent,
    agiosCaseRoute,
    agiosCasePopupRoute,
} from './';

const ENTITY_STATES = [
    ...agiosCaseRoute,
    ...agiosCasePopupRoute,
];

@NgModule({
    imports: [
        AgiosHipsterSharedModule,
        RouterModule.forRoot(ENTITY_STATES, { useHash: true })
    ],
    declarations: [
        AgiosCaseMySuffixComponent,
        AgiosCaseMySuffixDetailComponent,
        AgiosCaseMySuffixDialogComponent,
        AgiosCaseMySuffixDeleteDialogComponent,
        AgiosCaseMySuffixPopupComponent,
        AgiosCaseMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        AgiosCaseMySuffixComponent,
        AgiosCaseMySuffixDialogComponent,
        AgiosCaseMySuffixPopupComponent,
        AgiosCaseMySuffixDeleteDialogComponent,
        AgiosCaseMySuffixDeletePopupComponent,
    ],
    providers: [
        AgiosCaseMySuffixService,
        AgiosCaseMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AgiosHipsterAgiosCaseMySuffixModule {}
