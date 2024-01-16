package implementsample.controller;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import saasus.sdk.auth.ApiException;
import saasus.sdk.auth.api.CredentialApi;
import saasus.sdk.auth.api.TenantUserApi;
import saasus.sdk.auth.api.UserInfoApi;
import saasus.sdk.auth.models.Credentials;
import saasus.sdk.auth.models.UserInfo;
import saasus.sdk.auth.models.Users;
import saasus.sdk.modules.AuthApiClient;
import saasus.sdk.modules.Configuration;

@RestController
public class SampleController {
    public static String getIDToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            StringTokenizer st = new StringTokenizer(authHeader);
            if (st.countTokens() == 2 && st.nextToken().equalsIgnoreCase("Bearer")) {
                return st.nextToken();
            }
        }
        return "";
    }

    @GetMapping(value = "/credentials", produces = "application/json")
    public ResponseEntity<?> getCredentials(HttpSession session, HttpServletRequest request) throws Exception {
        String code = request.getParameter("code");

        AuthApiClient apiClient = new Configuration().getAuthApiClient();
        apiClient.setReferer(request.getHeader("Referer"));
        CredentialApi apiInstance = new CredentialApi(apiClient);
        Credentials result = null;
        try {
            result = apiInstance.getAuthCredentials(code, "tempCodeAuth", null);
        } catch (ApiException e) {
            System.err.println("Exception when calling CredentialApi#getAuthCredentials");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
            throw e;
        }

        return ResponseEntity.ok(result.toJson());
    }

    @GetMapping(value = "/refresh", produces = "application/json")
    public ResponseEntity<?> refresh(HttpSession session, HttpServletRequest request,
            @CookieValue(name = "SaaSusRefreshToken", defaultValue = "") String cookieValue) throws Exception {
        if (cookieValue == "") {
            return ResponseEntity.badRequest().body("No refresh token found");
        }

        AuthApiClient apiClient = new Configuration().getAuthApiClient();
        apiClient.setReferer(request.getHeader("Referer"));
        CredentialApi apiInstance = new CredentialApi(apiClient);
        Credentials result = null;
        try {
            result = apiInstance.getAuthCredentials(null, "refreshTokenAuth", cookieValue);
        } catch (ApiException e) {
            System.err.println("Exception when calling CredentialApi#getAuthCredentials");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
            throw e;
        }
        return ResponseEntity.ok(result.toJson());
    }

    @GetMapping(value = "/userinfo", produces = "application/json")
    public ResponseEntity<?> getMe(HttpSession session, HttpServletRequest request) throws Exception {
        AuthApiClient apiClient = new Configuration().getAuthApiClient();
        apiClient.setReferer(request.getHeader("Referer"));

        UserInfoApi userInfoApi = new UserInfoApi(apiClient);
        UserInfo userInfo = null;
        try {
            userInfo = userInfoApi.getUserInfo(getIDToken(request));
            System.out.println(userInfo);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserInfoApi#getUserInfo");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
            throw e;
        }

        return ResponseEntity.ok(userInfo.toJson());
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> getUsers(HttpSession session, HttpServletRequest request) throws Exception {
        AuthApiClient apiClient = new Configuration().getAuthApiClient();
        apiClient.setReferer(request.getHeader("Referer"));

        UserInfoApi userInfoApi = new UserInfoApi(apiClient);
        UserInfo userInfo = null;
        try {
            userInfo = userInfoApi.getUserInfo(getIDToken(request));
            System.out.println(userInfo);
        } catch (ApiException e) {
            System.err.println("Exception when calling UserInfoApi#getUserInfo");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
            throw e;
        }
        System.out.println(userInfo.getTenants());

        String tenantId = userInfo.getTenants().get(0).getId();
        TenantUserApi tenantUserApi = new TenantUserApi(apiClient);
        Users users = null;
        try {
            users = tenantUserApi.getTenantUsers(tenantId);
            System.out.println(users);
        } catch (ApiException e) {
            System.err.println("Exception when calling TenantUserApi#getTenantUsers");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
            throw e;
        }

        return ResponseEntity.ok(users.getUsers());
    }
}
