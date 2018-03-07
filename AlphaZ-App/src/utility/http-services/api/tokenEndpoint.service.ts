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

import { OAuth2AccessToken } from '../model/oAuth2AccessToken';

import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
import { Configuration }                                     from '../configuration';


@Injectable()
export class TokenEndpointService {

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
     * getAccessToken
     * 
     * @param parameters parameters
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public getAccessTokenUsingGET(parameters: string, observe?: 'body', reportProgress?: boolean): Observable<OAuth2AccessToken>;
    public getAccessTokenUsingGET(parameters: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OAuth2AccessToken>>;
    public getAccessTokenUsingGET(parameters: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OAuth2AccessToken>>;
    public getAccessTokenUsingGET(parameters: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling getAccessTokenUsingGET.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
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

        return this.httpClient.get<OAuth2AccessToken>(`${this.basePath}/oauth/token`,
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
     * postAccessToken
     * 
     * @param parameters parameters
     * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
     * @param reportProgress flag to report request and response progress.
     */
    public postAccessTokenUsingPOST(parameters: string, observe?: 'body', reportProgress?: boolean): Observable<OAuth2AccessToken>;
    public postAccessTokenUsingPOST(parameters: string, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<OAuth2AccessToken>>;
    public postAccessTokenUsingPOST(parameters: string, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<OAuth2AccessToken>>;
    public postAccessTokenUsingPOST(parameters: string, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
        if (parameters === null || parameters === undefined) {
            throw new Error('Required parameter parameters was null or undefined when calling postAccessTokenUsingPOST.');
        }

        let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
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

        return this.httpClient.post<OAuth2AccessToken>(`${this.basePath}/oauth/token`,
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