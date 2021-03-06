export * from './finTechAccountInformation.service';
import { FinTechAccountInformationService } from './finTechAccountInformation.service';
export * from './finTechAuthorization.service';
import { FinTechAuthorizationService } from './finTechAuthorization.service';
export * from './finTechBankSearch.service';
import { FinTechBankSearchService } from './finTechBankSearch.service';
export * from './finTechOauth2Authentication.service';
import { FinTechOauth2AuthenticationService } from './finTechOauth2Authentication.service';
export * from './fintechRetrieveAllSinglePayments.service';
import { FintechRetrieveAllSinglePaymentsService } from './fintechRetrieveAllSinglePayments.service';
export * from './fintechSinglePaymentInitiation.service';
import { FintechSinglePaymentInitiationService } from './fintechSinglePaymentInitiation.service';
export const APIS = [FinTechAccountInformationService, FinTechAuthorizationService, FinTechBankSearchService, FinTechOauth2AuthenticationService, FintechRetrieveAllSinglePaymentsService, FintechSinglePaymentInitiationService];
