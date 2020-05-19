package de.adorsys.opba.protocol.hbci.context;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableMap;
import de.adorsys.opba.protocol.api.common.ProtocolAction;
import de.adorsys.opba.protocol.bpmnshared.dto.context.BaseContext;
import de.adorsys.opba.protocol.hbci.service.storage.TransientDataEntry;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
public class HbciContext extends BaseContext {

    /**
     * PSU user ID (login) in ASPSP API
     */
    private String psuId;

    /**
     * Hardcoded process names based on the action name.
     */
    private Map<ProtocolAction, String> flowByAction = ImmutableMap.of(
            ProtocolAction.LIST_ACCOUNTS, "hbci-list-accounts",
            ProtocolAction.LIST_TRANSACTIONS, "hbci-list-transactions"
    );

    /**
     * FinTech WebHook that will be called by OpenBanking (and user will be redirected to) if the consent
     * was <b>granted</b> during authorization.
     */
    private String fintechRedirectUriOk;

    /**
     * FinTech WebHook that will be called by OpenBanking (and user will be redirected to) if the consent
     * was <b>declined</b> during authorization.
     */
    private String fintechRedirectUriNok;

    /**
     * Indicates whether TAN challenge was required.
     */
    private boolean tanChallengeRequired;

    @JsonIgnore
    public String getPsuPin() {
        TransientDataEntry entry = this.transientStorage().get();
        return null != entry ? entry.getPsuPin() : null;
    }

    @JsonIgnore
    public String getPsuTan() {
        TransientDataEntry entry = this.transientStorage().get();
        return null != entry ? entry.getTanValue() : null;
    }

    @JsonIgnore
    public void setPsuPin(String psuPassword) {
        this.transientStorage().set(new TransientDataEntry(psuPassword, null));
    }

    @JsonIgnore
    public void setPsuTan(String scaChallengeResult) {
        this.transientStorage().set(new TransientDataEntry(null, scaChallengeResult));
    }
}