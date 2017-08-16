import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';

import { AgiosCase } from './agios-case.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class AgiosCaseService {

    private resourceUrl = 'api/agios-cases';

    constructor(private http: Http) { }

    create(agiosCase: AgiosCase): Observable<AgiosCase> {
        const copy = this.convert(agiosCase);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    update(agiosCase: AgiosCase): Observable<AgiosCase> {
        const copy = this.convert(agiosCase);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            return res.json();
        });
    }

    find(id: number): Observable<AgiosCase> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            return res.json();
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        return new ResponseWrapper(res.headers, jsonResponse, res.status);
    }

    private convert(agiosCase: AgiosCase): AgiosCase {
        const copy: AgiosCase = Object.assign({}, agiosCase);
        return copy;
    }
}
