/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AgiosHipsterTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AgiosCaseDetailComponent } from '../../../../../../main/webapp/app/entities/agios-case/agios-case-detail.component';
import { AgiosCaseService } from '../../../../../../main/webapp/app/entities/agios-case/agios-case.service';
import { AgiosCase } from '../../../../../../main/webapp/app/entities/agios-case/agios-case.model';

describe('Component Tests', () => {

    describe('AgiosCase Management Detail Component', () => {
        let comp: AgiosCaseDetailComponent;
        let fixture: ComponentFixture<AgiosCaseDetailComponent>;
        let service: AgiosCaseService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AgiosHipsterTestModule],
                declarations: [AgiosCaseDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AgiosCaseService,
                    JhiEventManager
                ]
            }).overrideTemplate(AgiosCaseDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AgiosCaseDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AgiosCaseService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AgiosCase(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.agiosCase).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
