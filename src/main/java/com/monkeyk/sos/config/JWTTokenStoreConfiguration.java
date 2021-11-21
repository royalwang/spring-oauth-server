package com.monkeyk.sos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;


/**
 * 2020/6/9
 * <p>
 * <p>
 * JWT TokenStore config
 *
 * 使用时配置参数
 * <pre>sos.token.store=jwt</pre>
 *
 * @author Shengzhao Li
 * @since 2.1.0
 */
@Configuration
@ConditionalOnProperty(name = "sos.token.store", havingValue = "jwt")
public class JWTTokenStoreConfiguration {


    /**
     * 不同的系统用不同的jwtKey；不推荐共用一样的
     *
     * HMAC key, default: IH6S2dhCEMwGr7uE4fBakSuDh9SoIrRa
     * alg: HMACSHA256
     */
    @Value("${sos.token.store.jwt.key:IH6S2dhCEMwGr7uE4fBakSuDh9SoIrRa}")
    private String jwtKey;


//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter(UserService userService) {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//
//        DefaultAccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();
//        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
//        userAuthenticationConverter.setUserDetailsService(userService);
////        userAuthenticationConverter.setDefaultAuthorities(new String[]{"USER"});
//        tokenConverter.setUserTokenConverter(userAuthenticationConverter);
//
//        tokenConverter.setIncludeGrantType(true);
////        tokenConverter.setScopeAttribute("_scope");
//        jwtAccessTokenConverter.setAccessTokenConverter(tokenConverter);
//
//        jwtAccessTokenConverter.setSigningKey(this.jwtKey);
//        return jwtAccessTokenConverter;
//    }

//    /**
//     * JWT TokenStore
//     *
//     * @since 2.1.0
//     */
//    @Bean
//    public TokenStore tokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
//        return new JwtTokenStore(jwtAccessTokenConverter);
//    }


//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices(TokenStore tokenStore, JwtAccessTokenConverter tokenEnhancer, ClientDetailsService clientDetailsService) {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore);
//        tokenServices.setClientDetailsService(clientDetailsService);
//        //support refresh token
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setTokenEnhancer(tokenEnhancer);
//        return tokenServices;
//    }

}
