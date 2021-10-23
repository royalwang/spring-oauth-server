package com.monkeyk.sos.service.business;

import com.monkeyk.sos.service.dto.AccessTokenDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * 2019/7/6
 *
 * @author Shengzhao Li
 */
public class ClientCredentialsInlineAccessTokenInvokerTest extends AbstractInlineAccessTokenInvokerTest {


    @Test
    public void invokeNormal() {

        createClientDetails();

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("grant_type", "client_credentials");
        params.put("scope", "read");


        ClientCredentialsInlineAccessTokenInvoker accessTokenInvoker = new ClientCredentialsInlineAccessTokenInvoker();
        final AccessTokenDto accessTokenDto = accessTokenInvoker.invoke(params);

        assertNotNull(accessTokenDto);
        assertNotNull(accessTokenDto.getAccessToken());

//        System.out.println(accessTokenDto);

    }

    //    @Test(expected = NoSuchClientException.class)
    @Test()
    void invalidClientId() {

        createClientDetails();

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId + "ssoso");
        params.put("client_secret", clientSecret);
        params.put("grant_type", "client_credentials");
        params.put("scope", "read");


        ClientCredentialsInlineAccessTokenInvoker accessTokenInvoker = new ClientCredentialsInlineAccessTokenInvoker();
        assertThrows(NoSuchClientException.class, () -> {
            final AccessTokenDto accessTokenDto = accessTokenInvoker.invoke(params);

            assertNotNull(accessTokenDto);
            assertNotNull(accessTokenDto.getAccessToken());
        });

//        System.out.println(accessTokenDto);

    }

    @Test()
    void invalidClientSecret() {

        createClientDetails();

        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret + "sooe");
        params.put("grant_type", "client_credentials");
        params.put("scope", "read");


        ClientCredentialsInlineAccessTokenInvoker accessTokenInvoker = new ClientCredentialsInlineAccessTokenInvoker();
        final AccessTokenDto accessTokenDto = accessTokenInvoker.invoke(params);

        assertNotNull(accessTokenDto);
        assertNotNull(accessTokenDto.getAccessToken());

//        System.out.println(accessTokenDto);

    }

}