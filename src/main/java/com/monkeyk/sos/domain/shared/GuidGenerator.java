package com.monkeyk.sos.domain.shared;

//import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

import org.apache.commons.lang.RandomStringUtils;

import java.util.UUID;

/**
 * @author Shengzhao Li
 */
public abstract class GuidGenerator {


//    private static RandomValueStringGenerator defaultClientSecretGenerator = new RandomValueStringGenerator(32);


    /**
     * private constructor
     */
    private GuidGenerator() {
    }

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateClientSecret() {
        return RandomStringUtils.random(32, true, true);
    }

}