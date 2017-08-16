import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AgiosHipsterSharedModule } from '../../shared';
import {
    AgiosCaseService,
    AgiosCasePopupService,
    AgiosCaseComponent,
    AgiosCaseDetailComponent,
    AgiosCaseDialogComponent,
    AgiosCasePopupComponent,
    AgiosCaseDeletePopupComponent,
    AgiosCaseDeleteDialogComponent,
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
        AgiosCaseComponent,
        AgiosCaseDetailComponent,
        AgiosCaseDialogComponent,
        AgiosCaseDeleteDialogComponent,
        AgiosCasePopupComponent,
        AgiosCaseDeletePopupComponent,
    ],
    entryComponents: [
        AgiosCaseComponent,
        AgiosCaseDialogComponent,
        AgiosCasePopupComponent,
        AgiosCaseDeleteDialogComponent,
        AgiosCaseDeletePopupComponent,
    ],
    providers: [
        AgiosCaseService,
        AgiosCasePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AgiosHipsterAgiosCaseModule {}
