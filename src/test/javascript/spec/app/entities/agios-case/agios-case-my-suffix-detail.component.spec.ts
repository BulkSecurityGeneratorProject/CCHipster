/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { AgiosHipsterTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { AgiosCaseMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/agios-case/agios-case-my-suffix-detail.component';
import { AgiosCaseMySuffixService } from '../../../../../../main/webapp/app/entities/agios-case/agios-case-my-suffix.service';
import { AgiosCaseMySuffix } from '../../../../../../main/webapp/app/entities/agios-case/agios-case-my-suffix.model';

describe('Component Tests', () => {

    describe('AgiosCaseMySuffix Management Detail Component', () => {
        let comp: AgiosCaseMySuffixDetailComponent;
        let fixture: ComponentFixture<AgiosCaseMySuffixDetailComponent>;
        let service: AgiosCaseMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [AgiosHipsterTestModule],
                declarations: [AgiosCaseMySuffixDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    AgiosCaseMySuffixService,
                    JhiEventManager
                ]
            }).overrideTemplate(AgiosCaseMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(AgiosCaseMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AgiosCaseMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new AgiosCaseMySuffix(10)));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.agiosCase).toEqual(jasmine.objectContaining({id: 10}));
            });
        });
    });

});
