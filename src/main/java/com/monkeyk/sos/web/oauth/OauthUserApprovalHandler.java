package com.monkeyk.sos.web.oauth;

import com.monkeyk.sos.service.OauthService;

/**
 * @author Shengzhao Li
 * @deprecated   use spring-security-oauth2-authorization-server replaced from v2.1.1
 */
//public class OauthUserApprovalHandler extends TokenStoreUserApprovalHandler {
public class OauthUserApprovalHandler  {

    private OauthService oauthService;

    public OauthUserApprovalHandler() {
    }

//
//    public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
//        if (super.isApproved(authorizationRequest, userAuthentication)) {
//            return true;
//        }
//        if (!userAuthentication.isAuthenticated()) {
//            return false;
//        }
//
//        OauthClientDetails clientDetails = oauthService.loadOauthClientDetails(authorizationRequest.getClientId());
//        return clientDetails != null && clientDetails.trusted();
//
//    }

    public void setOauthService(OauthService oauthService) {
        this.oauthService = oauthService;
    }
}
