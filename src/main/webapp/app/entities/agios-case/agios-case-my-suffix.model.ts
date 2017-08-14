import { BaseEntity } from './../../shared';

export class AgiosCaseMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public caseNr?: string,
        public personNr?: string,
        public companyNr?: string,
        public agiosNodeName?: string,
        public workflowUid?: string,
        public caseNo?: string,
    ) {
    }
}
