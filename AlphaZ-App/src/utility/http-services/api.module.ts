import { NgModule, ModuleWithProviders, SkipSelf, Optional } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Configuration } from './configuration';

import { AuditEventsMvcEndpointService } from './api/auditEventsMvcEndpoint.service';
import { AuthorizationEndpointService } from './api/authorizationEndpoint.service';
import { CheckTokenEndpointService } from './api/checkTokenEndpoint.service';
import { CustomerControllerService } from './api/customerController.service';
import { EndpointMvcAdapterService } from './api/endpointMvcAdapter.service';
import { EnvironmentMvcEndpointService } from './api/environmentMvcEndpoint.service';
import { GlobalErrorControllerService } from './api/globalErrorController.service';
import { HealthMvcEndpointService } from './api/healthMvcEndpoint.service';
import { HeapdumpMvcEndpointService } from './api/heapdumpMvcEndpoint.service';
import { IndexControllerService } from './api/indexController.service';
import { LoggersMvcEndpointService } from './api/loggersMvcEndpoint.service';
import { MetricsMvcEndpointService } from './api/metricsMvcEndpoint.service';
import { PriviligeControllerService } from './api/priviligeController.service';
import { TokenEndpointService } from './api/tokenEndpoint.service';
import { TokenKeyEndpointService } from './api/tokenKeyEndpoint.service';
import { UserControllerService } from './api/userController.service';
import { WhitelabelApprovalEndpointService } from './api/whitelabelApprovalEndpoint.service';
import { WhitelabelErrorEndpointService } from './api/whitelabelErrorEndpoint.service';

@NgModule({
  imports:      [ CommonModule, HttpClientModule ],
  declarations: [],
  exports:      [],
  providers: [
    AuditEventsMvcEndpointService,
    AuthorizationEndpointService,
    CheckTokenEndpointService,
    CustomerControllerService,
    EndpointMvcAdapterService,
    EnvironmentMvcEndpointService,
    GlobalErrorControllerService,
    HealthMvcEndpointService,
    HeapdumpMvcEndpointService,
    IndexControllerService,
    LoggersMvcEndpointService,
    MetricsMvcEndpointService,
    PriviligeControllerService,
    TokenEndpointService,
    TokenKeyEndpointService,
    UserControllerService,
    WhitelabelApprovalEndpointService,
    WhitelabelErrorEndpointService ]
})
export class ApiModule {
    public static forRoot(configurationFactory: () => Configuration): ModuleWithProviders {
        return {
            ngModule: ApiModule,
            providers: [ { provide: Configuration, useFactory: configurationFactory } ]
        }
    }

    constructor( @Optional() @SkipSelf() parentModule: ApiModule) {
        if (parentModule) {
            throw new Error('ApiModule is already loaded. Import your base AppModule only.');
        }
    }
}
