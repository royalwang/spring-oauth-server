package com.monkeyk.sos.config;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.gen.ECKeyGenerator;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

import javax.sql.DataSource;

/**
 * 2018/2/8
 * <p>
 * <p>
 * OAuth2 config
 *
 * @author Shengzhao Li
 */
@Configuration
// import from v2.1.1
@Import(OAuth2AuthorizationServerConfiguration.class)
public class OAuth2ServerConfiguration {


    /*Fixed,  resource-id */
    public static final String RESOURCE_ID = "sos-resource";


    /**
     * JdbcTemplate config
     *
     * @param dataSource DataSource
     * @return JdbcTemplate
     * @since 2.1.1
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


    /**
     * RegisteredClientRepository config
     * <p>
     * SQL: oauth2-registered-client-schema.sql
     *
     * @param jdbcTemplate JdbcTemplate
     * @return RegisteredClientRepository
     * @since 2.1.1
     */
    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }


    /**
     * OAuth2AuthorizationService config
     * <p>
     * SQL: oauth2-authorization-schema.sql
     *
     * @param jdbcTemplate               JdbcTemplate
     * @param registeredClientRepository RegisteredClientRepository
     * @return OAuth2AuthorizationService
     * @since 2.1.1
     */
    @Bean
    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
    }


    /**
     * OAuth2AuthorizationConsentService config
     * <p>
     * SQL: oauth2-authorization-consent-schema.sql
     *
     * @param jdbcTemplate               JdbcTemplate
     * @param registeredClientRepository RegisteredClientRepository
     * @return OAuth2AuthorizationConsentService
     * @since 2.1.1
     */
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
    }


    /**
     * JWT生成与校验使用的 JWK
     * <p>
     * 使用算法：EC, P_256
     *
     * @return JWKSource
     * @throws JOSEException e
     * @since 2.1.1
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws JOSEException {
        ECKeyGenerator keyGenerator = new ECKeyGenerator(Curve.P_256);
        keyGenerator.keyID(RESOURCE_ID);
        JWK jwk = keyGenerator.generate();
        System.out.println("\n Use auto-generated jwk: " + jwk.toJSONString());
        JWKSet jwkSet = new JWKSet(jwk);

        return new ImmutableJWKSet<>(jwkSet);
    }

//    /**
//     * // unity resource
//     * UNITY 资源的访问权限配置
//     */
//    @Configuration
//    @EnableResourceServer
//    protected static class UnityResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources) {
//            resources.resourceId(RESOURCE_ID).stateless(false);
//        }
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http
//                    // Since we want the protected resources to be accessible in the UI as well we need
//                    // session creation to be allowed (it's disabled by default in 2.0.6)
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                    .and()
//                    // 所有以 /unity/  开头的 URL属于此资源
//                    .requestMatchers().antMatchers("/unity/**")
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/unity/**").access("#oauth2.hasScope('read') and hasRole('UNITY')");
//
//        }
//
//    }
//
//
//    /**
//     * // mobile resource
//     * MOBILE 资源的访问权限配置
//     */
//    @Configuration
//    @EnableResourceServer
//    protected static class MobileResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//        @Override
//        public void configure(ResourceServerSecurityConfigurer resources) {
//            resources.resourceId(RESOURCE_ID).stateless(false);
//        }
//
//        @Override
//        public void configure(HttpSecurity http) throws Exception {
//            http
//                    // Since we want the protected resources to be accessible in the UI as well we need
//                    // session creation to be allowed (it's disabled by default in 2.0.6)
//                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                    .and()
//                    // 所有以 /m/  开头的 URL属于此资源
//                    .requestMatchers().antMatchers("/m/**")
//                    .and()
//                    .authorizeRequests()
//                    .antMatchers("/m/**").access("#oauth2.hasScope('read') and hasRole('MOBILE')");
//
//        }
//
//    }
//
//    @Configuration
//    @EnableAuthorizationServer
//    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
//
//
//        @Autowired
//        private TokenStore tokenStore;
//
//        @Autowired
//        private DefaultTokenServices tokenServices;
//
//
//        @Autowired
//        private ClientDetailsService clientDetailsService;
//
//
//        @Autowired
//        private OauthService oauthService;
//
//
//        @Autowired
//        private AuthorizationCodeServices authorizationCodeServices;
//
//
//        @Autowired
//        private UserService userDetailsService;
//
//
//        @Autowired
//        @Qualifier("authenticationManagerBean")
//        private AuthenticationManager authenticationManager;
//
//
//        @Override
//        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//            clients.withClientDetails(clientDetailsService);
//        }
//
//
////        /*
////         * JDBC TokenStore
////         */
////        @Bean
////        public TokenStore tokenStore(DataSource dataSource) {
////            return new JdbcTokenStore(dataSource);
////        }
//
//        /*
//         * Redis TokenStore (有Redis场景时使用)
//         */
////        @Bean
////        public TokenStore tokenStore(RedisConnectionFactory connectionFactory) {
////            final RedisTokenStore redisTokenStore = new RedisTokenStore(connectionFactory);
////            //prefix
////            redisTokenStore.setPrefix(RESOURCE_ID);
////            return redisTokenStore;
////        }
//
//
//        @Bean
//        public ClientDetailsService clientDetailsService(DataSource dataSource) {
//            return new CustomJdbcClientDetailsService(dataSource);
//        }
//
//
//        @Bean
//        public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
//            return new JdbcAuthorizationCodeServices(dataSource);
//        }
//
//
//        @Override
//        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//            endpoints.tokenServices(tokenServices)
//                    .tokenStore(tokenStore)
//                    .authorizationCodeServices(authorizationCodeServices)
//                    .userDetailsService(userDetailsService)
//                    .userApprovalHandler(userApprovalHandler())
//                    .authenticationManager(authenticationManager);
//        }
//
//        @Override
//        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//            // real 值可自定义
//            oauthServer.realm("spring-oauth-server")
//                    // 支持 client_credentials 的配置
//                    .allowFormAuthenticationForClients();
//        }
//
//        @Bean
//        public OAuth2RequestFactory oAuth2RequestFactory() {
//            return new DefaultOAuth2RequestFactory(clientDetailsService);
//        }
//
//
//        @Bean
//        public UserApprovalHandler userApprovalHandler() {
//            OauthUserApprovalHandler userApprovalHandler = new OauthUserApprovalHandler();
//            userApprovalHandler.setOauthService(oauthService);
//            userApprovalHandler.setTokenStore(tokenStore);
//            userApprovalHandler.setClientDetailsService(this.clientDetailsService);
//            userApprovalHandler.setRequestFactory(oAuth2RequestFactory());
//            return userApprovalHandler;
//        }
//
//    }


}
