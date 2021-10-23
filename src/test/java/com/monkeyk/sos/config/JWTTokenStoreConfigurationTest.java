package com.monkeyk.sos.config;


import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 2020/6/9
 *
 * @author Shengzhao Li
 * @since 2.1.0
 */
class JWTTokenStoreConfigurationTest {


    @Test
    void keyTest() throws Exception {

        RandomValueStringGenerator randomValueStringGenerator = new RandomValueStringGenerator(32);
        String verifierKey = randomValueStringGenerator.generate();
        assertNotNull(verifierKey);
//        System.out.println(verifierKey);

    }


    @Test
    void testJwtAccessTokenConverter() throws Exception {

        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("IH6S2dhCEMwGr7uE4fBakSuDh9SoIrRa");
        jwtAccessTokenConverter.afterPropertiesSet();

        assertFalse(jwtAccessTokenConverter.isPublic());
        Map<String, String> key = jwtAccessTokenConverter.getKey();
        assertNotNull(key);

    }

}