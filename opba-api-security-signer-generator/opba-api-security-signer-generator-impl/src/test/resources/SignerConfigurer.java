package de.adorsys.signer.test;

import de.adorsys.opba.api.security.generator.api.GeneratedSigner;

@GeneratedSigner(openApiYamlPath = {"api/tpp_banking_api_ais.yml", "api/tpp_banking_api_pis.yml", "api/tpp_banking_api_bank_search.yml"})
public class SignerConfigurer {
}