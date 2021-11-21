package com.monkeyk.sos.service.business;

import com.monkeyk.sos.service.dto.AccessTokenDto;
import org.junit.jupiter.api.Test;
//import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 2019/7/6
 *
 * @author Shengzhao Li
 */
class RefreshTokenInlineAccessTokenInvokerTest extends AbstractInlineAccessTokenInvokerTest {


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
        assertNotNull(tokenDto.getRefreshToken());
        assertNotNull(tokenDto.getAccessToken());


        Map<String, String> params2 = new HashMap<>();
        params2.put("client_id", clientId);
        params2.put("client_secret", clientSecret);
        params2.put("grant_type", "refresh_token");
        params2.put("scope", "read");
        params2.put("refresh_token", tokenDto.getRefreshToken());


        RefreshTokenInlineAccessTokenInvoker refreshTokenInlineAccessTokenInvoker = new RefreshTokenInlineAccessTokenInvoker();
        final AccessTokenDto accessTokenDto = refreshTokenInlineAccessTokenInvoker.invoke(params2);


        assertNotNull(accessTokenDto);
        assertNotNull(accessTokenDto.getAccessToken());

        assertNotEquals(accessTokenDto.getAccessToken(), tokenDto.getAccessToken());
        assertNotEquals(accessTokenDto.getRefreshToken(), tokenDto.getRefreshToken());

    }


    //    @Test(expected = InvalidGrantException.class)
    @Test
    void invalidRefreshToken() {

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
        assertNotNull(tokenDto.getRefreshToken());
        assertNotNull(tokenDto.getAccessToken());


        Map<String, String> params2 = new HashMap<>();
        params2.put("client_id", clientId);
        params2.put("client_secret", clientSecret);
        params2.put("grant_type", "refresh_token");
        params2.put("scope", "read");
        params2.put("refresh_token", tokenDto.getRefreshToken() + "sss");


//        RefreshTokenInlineAccessTokenInvoker refreshTokenInlineAccessTokenInvoker = new RefreshTokenInlineAccessTokenInvoker();
//        assertThrows(InvalidTokenException.class, () -> {
//            final AccessTokenDto accessTokenDto = refreshTokenInlineAccessTokenInvoker.invoke(params2);
//
//
//            assertNotNull(accessTokenDto);
//            assertNotNull(accessTokenDto.getAccessToken());
//
//            assertNotEquals(accessTokenDto.getAccessToken(), tokenDto.getAccessToken());
//            assertNotEquals(accessTokenDto.getRefreshToken(), tokenDto.getRefreshToken());
//
//        });

    }


}