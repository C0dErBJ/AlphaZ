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
import { ResponseModel } from '../model/responseModel';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class IndexControllerService {

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
     * customerList
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public customerListUsingGET(observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public customerListUsingGET(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public customerListUsingGET(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public customerListUsingGET(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<ModelAndView>(`${this.basePath}/customerlist`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * dashboard
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public dashboardUsingGET(observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public dashboardUsingGET(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public dashboardUsingGET(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public dashboardUsingGET(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<ModelAndView>(`${this.basePath}/dashboard`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * login
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public loginUsingGET(observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public loginUsingGET(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public loginUsingGET(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public loginUsingGET(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<ModelAndView>(`${this.basePath}/login`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * logout
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public logoutUsingGET(observe?: 'body', reportProgress?: boolean): Observable<string>;
    public logoutUsingGET(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<string>>;
    public logoutUsingGET(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<string>>;
    public logoutUsingGET(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<string>(`${this.basePath}/logout`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * privilege
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public privilegeUsingGET(observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public privilegeUsingGET(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public privilegeUsingGET(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public privilegeUsingGET(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<ModelAndView>(`${this.basePath}/privilege`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * productlist
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public productlistUsingGET(observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public productlistUsingGET(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public productlistUsingGET(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public productlistUsingGET(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<ModelAndView>(`${this.basePath}/productlist`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

    /**
     * Script
     * 
     * @param scriptType scriptType
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public scriptUsingGET(scriptType?: string, observe?: 'body', reportProgress?: boolean): Observable<string>;
    public scriptUsingGET(scriptType?: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<string>>;
    public scriptUsingGET(scriptType?: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<string>>;
    public scriptUsingGET(scriptType?: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (scriptType !== undefined) {
            queryParameters = queryParameters.set('scriptType', <any>scriptType);
        }

        let headers = this.defaultHeaders;

        // to determine the Accept header
        let httpHeaderAccepts: string[] = [
            'application/javascript'
        ];
        let httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
        if (httpHeaderAcceptSelected != undefined) {
            headers = headers.set("Accept", httpHeaderAcceptSelected);
        }

        // to determine the Content-Type header
        let consumes: string[] = [
        ];

        return this.httpClient.get<string>(`${this.basePath}/script`,
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
     * sidebarmenu
     * 
     * @param userid 
     * @param username 
     * @param avatar 
     * @param password 
     * @param registertime 
     * @param phone 
     * @param birthday 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public sidebarmenuUsingGET(userid?: number, username?: string, avatar?: number, password?: string, registertime?: Date, phone?: string, birthday?: Date, observe?: 'body', reportProgress?: boolean): Observable<ResponseModel>;
    public sidebarmenuUsingGET(userid?: number, username?: string, avatar?: number, password?: string, registertime?: Date, phone?: string, birthday?: Date, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ResponseModel>>;
    public sidebarmenuUsingGET(userid?: number, username?: string, avatar?: number, password?: string, registertime?: Date, phone?: string, birthday?: Date, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ResponseModel>>;
    public sidebarmenuUsingGET(userid?: number, username?: string, avatar?: number, password?: string, registertime?: Date, phone?: string, birthday?: Date, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
        if (userid !== undefined) {
            queryParameters = queryParameters.set('userid', <any>userid);
        }
        if (username !== undefined) {
            queryParameters = queryParameters.set('username', <any>username);
        }
        if (avatar !== undefined) {
            queryParameters = queryParameters.set('avatar', <any>avatar);
        }
        if (password !== undefined) {
            queryParameters = queryParameters.set('password', <any>password);
        }
        if (registertime !== undefined) {
            queryParameters = queryParameters.set('registertime', <any>registertime.toISOString());
        }
        if (phone !== undefined) {
            queryParameters = queryParameters.set('phone', <any>phone);
        }
        if (birthday !== undefined) {
            queryParameters = queryParameters.set('birthday', <any>birthday.toISOString());
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

        return this.httpClient.get<ResponseModel>(`${this.basePath}/sidebarmenu`,
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
     * userList
     * 
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public userListUsingGET(observe?: 'body', reportProgress?: boolean): Observable<ModelAndView>;
    public userListUsingGET(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ModelAndView>>;
    public userListUsingGET(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ModelAndView>>;
    public userListUsingGET(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {

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

        return this.httpClient.get<ModelAndView>(`${this.basePath}/userlist`,
            {
                withCredentials: this.configuration.withCredentials,
                headers: headers,
                observe: observe,
                reportProgress: reportProgress
            }
        );
    }

}