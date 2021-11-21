package com.monkeyk.sos.config;

import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * 2018/3/22
 *
 * 此配置用于启用 #oauth2 表达式，如：#oauth2.hasScope('read')
 *
 * @author Shengzhao Li
 * @deprecated  use spring-security-authorization-server replaced  from v2.1.1
 */
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class OAuth2MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {


    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        return new OAuth2MethodSecurityExpressionHandler();
        return super.createExpressionHandler();
    }

}
