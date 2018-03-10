/**
 * AlphaZ  Api
 * AlphaZ Swagger Api
 *
 * OpenAPI spec version: V1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
/* tslint:disable:no-unused-variable member-ordering */

import { Inject, Injectable, Optional }                      from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams,
         HttpResponse, HttpEvent }                           from '@angular/common/http';
import { CustomHttpUrlEncodingCodec }                        from '../encoder';

import { Observable }                                        from 'rxjs/Observable';

import { ModelAndView } from '../model/modelAndView';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class AuthorizationEndpointService {

    protected basePath = 'https://localhost:8080';
    public defaultHeaders = new HttpHeaders();
    public configuration = new Configuration();

    constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
        if (basePath) {
            this.basePath = basePath;
        }
        if (configuration) {
            this.configuration = configuration;
            this.basePath = basePath || configuration.basePath || this.basePath;
        }
    }

    /**
     * @param consumes string[] mime-types
     * @return true: consumes contains 'multipart/form-data', false: otherwise
     */
    private canConsumeForm(consumes: string[]): boolean {
        const form = 'multipart/form-data';
        for (let consume of consumes) {
            if (form === consume) {
                return true;
            }
        }
        return false;
    }


    /**
     * authorize
     * 
     * @param parameters parameters
     * @param model model
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public authorizeUsingDELETE(parameters: string, model?: { [key: string]: string; }, observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public authorizeUsingDELETE(parameters: string, model?: { [key: string]: string; }, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public authorizeUsingDELETE(parameters: string, model?: { [key: string]: string; }, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public authorizeUsingDELETE(parameters: string, model?: { [key: string]: string; }, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling authorizeUsingDELETE.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (model !== undefined) {
            queryParameters = queryParameters.set('model', <any>model);
        }
        if (parameters !== undefined) {
            queryParameters = queryParameters.set('parameters', <any>parameters);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];

        return this.httpClient.delete<ModelAndView>(`${this.basePath}/oauth/authorize`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * authorize
     * 
     * @param parameters parameters
     * @param model model
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public authorizeUsingGET(parameters: string, model?: { [key: string]: string; }, observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public authorizeUsingGET(parameters: string, model?: { [key: string]: string; }, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public authorizeUsingGET(parameters: string, model?: { [key: string]: string; }, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public authorizeUsingGET(parameters: string, model?: { [key: string]: string; }, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling authorizeUsingGET.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (model !== undefined) {
            queryParameters = queryParameters.set('model', <any>model);
        }
        if (parameters !== undefined) {
            queryParameters = queryParameters.set('parameters', <any>parameters);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];

        return this.httpClient.get<ModelAndView>(`${this.basePath}/oauth/authorize`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * authorize
     * 
     * @param parameters parameters
     * @param model model
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public authorizeUsingHEAD(parameters: string, model?: { [key: string]: string; }, observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public authorizeUsingHEAD(parameters: string, model?: { [key: string]: string; }, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public authorizeUsingHEAD(parameters: string, model?: { [key: string]: string; }, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public authorizeUsingHEAD(parameters: string, model?: { [key: string]: string; }, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling authorizeUsingHEAD.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (model !== undefined) {
            queryParameters = queryParameters.set('model', <any>model);
        }
        if (parameters !== undefined) {
            queryParameters = queryParameters.set('parameters', <any>parameters);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
            'application/json'
        ];

        return this.httpClient.head<ModelAndView>(`${this.basePath}/oauth/authorize`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * authorize
     * 
     * @param parameters parameters
     * @param model model
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public authorizeUsingOPTIONS(parameters: string, model?: { [key: string]: string; }, observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public authorizeUsingOPTIONS(parameters: string, model?: { [key: string]: string; }, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public authorizeUsingOPTIONS(parameters: string, model?: { [key: string]: string; }, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public authorizeUsingOPTIONS(parameters: string, model?: { [key: string]: string; }, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling authorizeUsingOPTIONS.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (model !== undefined) {
            queryParameters = queryParameters.set('model', <any>model);
        }
        if (parameters !== undefined) {
            queryParameters = queryParameters.set('parameters', <any>parameters);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
            'application/json'
        ];

        return this.httpClient.options<ModelAndView>(`${this.basePath}/oauth/authorize`,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * authorize
     * 
     * @param parameters parameters
     * @param model model
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public authorizeUsingPATCH(parameters: string, model?: { [key: string]: string; }, observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public authorizeUsingPATCH(parameters: string, model?: { [key: string]: string; }, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public authorizeUsingPATCH(parameters: string, model?: { [key: string]: string; }, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public authorizeUsingPATCH(parameters: string, model?: { [key: string]: string; }, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling authorizeUsingPATCH.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (model !== undefined) {
            queryParameters = queryParameters.set('model', <any>model);
        }
        if (parameters !== undefined) {
            queryParameters = queryParameters.set('parameters', <any>parameters);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
            'application/json'
        ];

        return this.httpClient.patch<ModelAndView>(`${this.basePath}/oauth/authorize`,
            null,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * authorize
     * 
     * @param parameters parameters
     * @param model model
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public authorizeUsingPOST(parameters: string, model?: { [key: string]: string; }, observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public authorizeUsingPOST(parameters: string, model?: { [key: string]: string; }, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public authorizeUsingPOST(parameters: string, model?: { [key: string]: string; }, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public authorizeUsingPOST(parameters: string, model?: { [key: string]: string; }, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling authorizeUsingPOST.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (model !== undefined) {
            queryParameters = queryParameters.set('model', <any>model);
        }
        if (parameters !== undefined) {
            queryParameters = queryParameters.set('parameters', <any>parameters);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
            'application/json'
        ];

        return this.httpClient.post<ModelAndView>(`${this.basePath}/oauth/authorize`,
            null,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * authorize
     * 
     * @param parameters parameters
     * @param model model
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public authorizeUsingPUT(parameters: string, model?: { [key: string]: string; }, observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public authorizeUsingPUT(parameters: string, model?: { [key: string]: string; }, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public authorizeUsingPUT(parameters: string, model?: { [key: string]: string; }, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public authorizeUsingPUT(parameters: string, model?: { [key: string]: string; }, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling authorizeUsingPUT.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (model !== undefined) {
            queryParameters = queryParameters.set('model', <any>model);
        }
        if (parameters !== undefined) {
            queryParameters = queryParameters.set('parameters', <any>parameters);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            '*/*'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
            'application/json'
        ];

        return this.httpClient.put<ModelAndView>(`${this.basePath}/oauth/authorize`,
            null,
            {
                params: queryParameters,
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}