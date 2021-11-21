package com.monkeyk.sos.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;


/**
 * 2020/6/9
 * <p>
 * <p>
 * JDBC TokenStore config
 * 使用时配置参数
 * <pre>sos.token.store=jdbc</pre>   (默认)
 *
 * @author Shengzhao Li
 * @since 2.1.0
 */
@Configuration
@ConditionalOnProperty(name = "sos.token.store", havingValue = "jdbc", matchIfMissing = true)
public class JdbcTokenStoreConfiguration {

//
//    /**
//     * JDBC TokenStore
//     */
//    @Bean
//    public TokenStore tokenStore(DataSource dataSource) {
//        return new JdbcTokenStore(dataSource);
//    }
//
//
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices(TokenStore tokenStore, ClientDetailsService clientDetailsService) {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore);
//        tokenServices.setClientDetailsService(clientDetailsService);
//        //support refresh token
//        tokenServices.setSupportRefreshToken(true);
//        return tokenServices;
//    }

}
