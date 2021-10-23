package com.monkeyk.sos.service.business;

import com.monkeyk.sos.service.dto.AccessTokenDto;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 2019/7/6
 *
 * @author Shengzhao Li
 */
class PasswordInlineAccessTokenInvokerTest extends AbstractInlineAccessTokenInvokerTest {


    @Test
    void invokeNormal() {

        createClientDetails();

        createUser();

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("grant_type", "password");
        params.put("scope", "read");
        params.put("username", username);
        params.put("password", password);


        PasswordInlineAccessTokenInvoker accessTokenInvoker = new PasswordInlineAccessTokenInvoker();
        final AccessTokenDto tokenDto = accessTokenInvoker.invoke(params);

        assertNotNull(tokenDto);
        assertNotNull(tokenDto.getAccessToken());
        assertNotNull(tokenDto.getRefreshToken());

//        System.out.println(accessTokenDto);

    }


    //    @Test(expected = InvalidGrantException.class)
    @Test
    void invalidUsername() {

        createClientDetails();

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("grant_type", "password");
        params.put("scope", "read");

        params.put("username", "useraaa");
        params.put("password", "password");

        PasswordInlineAccessTokenInvoker accessTokenInvoker = new PasswordInlineAccessTokenInvoker();
        assertThrows(InvalidGrantException.class, () -> {
            final AccessTokenDto tokenDto = accessTokenInvoker.invoke(params);

            assertNull(tokenDto);
        });


//        System.out.println(accessTokenDto);

    }


    //    @Test(expected = IllegalStateException.class)
    @Test
    void invalidScope() {

        createClientDetails();
        createUser();

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("grant_type", "password");
//        params.put("scope", "read");

        params.put("username", username);
        params.put("password", password);

        PasswordInlineAccessTokenInvoker accessTokenInvoker = new PasswordInlineAccessTokenInvoker();
        assertThrows(IllegalStateException.class, () -> {
            final AccessTokenDto tokenDto = accessTokenInvoker.invoke(params);

            assertNull(tokenDto);
        });


//        System.out.println(accessTokenDto);

    }


}