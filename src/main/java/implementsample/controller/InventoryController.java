package implementsample.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import implementsample.dto.InventoryDto;
import implementsample.service.InventoryService;

import saasus.sdk.auth.ApiException;
import saasus.sdk.auth.api.UserInfoApi;
import saasus.sdk.auth.models.UserInfo;
import saasus.sdk.modules.AuthApiClient;
import saasus.sdk.modules.Configuration;

@RestController
public class InventoryController {
    @GetMapping("/products")
    public List<InventoryDto> getInventory(@Context HttpServletRequest request) throws ApiException {

        // JWT取得
        String token = request.getHeader("Authorization").substring(7);
        System.out.println("token:" + token);

        // ユーザ情報取得
        AuthApiClient apiClient = new Configuration().getAuthApiClient();
        UserInfoApi userInfoApi = new UserInfoApi(apiClient);
        UserInfo userInfo = null;
        userInfo = userInfoApi.getUserInfo(token);
        System.out.println("userInfo:" + userInfo);

        // テナントID取得
        String tenantId = userInfo.getTenants().get(0).getId();
        System.out.println("tenantId:" + tenantId);

        // テナントIDに対応する在庫情報取得
        InventoryService inventoryService = new InventoryService();
        return inventoryService.getInventory(tenantId);
    }

    // @SaaSusAPI(path = "getInventory")
    // public static List<InventoryDto> getInventoryEntryPoint(String xApiKey) {
    // // APIキーからテナントIDを取得
    // TenantApiClient tenantClient = new TenantApiClient();
    // String tenantId = tenantClient.getTenantIdFromApiKey(xApiKey);

    // // getInventoryDataServiceメソッドを呼び出し
    // InventoryService inventoryService = new InventoryService();
    // List<InventoryDto> inventoryItems = inventoryService.getInventory(tenantId);

    // return inventoryItems;
    // }
}
