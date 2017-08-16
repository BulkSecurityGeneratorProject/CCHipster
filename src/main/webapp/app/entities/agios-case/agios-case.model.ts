import { BaseEntity } from './../../shared';

export class AgiosCase implements BaseEntity {
    constructor(
        public id?: number,
        public caseNr?: number,
        public caseNo?: string,
        public personNr?: string,
        public companyNr?: string,
        public agiosNodeName?: string,
        public workflowUid?: string,
        public reasons?: string,
        public statusUid?: string,
        public currentStepUid?: string,
        public codeName?: string,
        public evtOpen?: string,
        public displayName?: string,
        public evtclose?: string,
        public agiosEntity?: string,
    ) {
    }
}
