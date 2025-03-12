package implementsample.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import saasus.sdk.auth.ApiException;
import saasus.sdk.auth.api.UserInfoApi;
import saasus.sdk.auth.models.UserInfo;
import saasus.sdk.modules.AuthApiClient;
import saasus.sdk.modules.Configuration;
import saasus.sdk.modules.SaaSusAPI;

@RestController
public class InventoryController {
    private static final Logger logger = Logger.getLogger(InventoryController.class.getName());

    @SaaSusAPI(path = "getInventory")
    public static List<InventoryItem> getInventoryEntryPoint(String xApiKey) {

        // System.out.println("apiKey:" + apiKey);

        // APIキーからテナントIDを取得
        TenantApiClient tenantClient = new Configuration().getTenantApiClient();
        String tenantId = tenantClient.getTenantIdFromApiKey(xApiKey);
        // String tenantId = "";
        // if (apiKey.equals("tenant1")) {
        // tenantId = "1cfd6af8-219c-4f64-8fe3-2e8973697bff";
        // } else if (apiKey.equals("tenant2")) {
        // tenantId = "aee54d7b-1819-484c-a584-fef85a64419e";
        // } else {
        // tenantId = "oh....";
        // }
        // System.out.println("tenantId:" + tenantId);

        // getInventoryDataServiceメソッドを呼び出し
        InventoryController controller = new InventoryController();
        List<InventoryController.InventoryItem> inventoryItems = controller.getInventoryDataService(tenantId);

        return inventoryItems;
    }

    @GetMapping("/products")
    public List<InventoryItem> getInventory(@Context HttpServletRequest request) {

        // JWT取得
        String token = request.getHeader("Authorization").substring(7);

        // ユーザ情報取得
        AuthApiClient apiClient = new Configuration().getAuthApiClient();
        UserInfoApi userInfoApi = new UserInfoApi(apiClient);
        UserInfo userInfo = null;
        try {
            userInfo = userInfoApi.getUserInfo(token);
        } catch (ApiException e) {
            logger.log(Level.WARNING, "Failed to get user info: {0}", e.getMessage());
            return Collections.singletonList(new InventoryItem("ERROR", "Failed to get user info", 0));
        }

        // テナントID取得
        String tenantId = userInfo.getTenants().get(0).getId();

        // テナントIDに対応する在庫情報取得
        return getInventoryDataService(tenantId);
    }

    public List<InventoryItem> getInventoryDataService(String tenantId) {

        // テナントIDに対応する在庫情報取得
        return TenantInventory.getOrDefault(tenantId, TenantInventory.get("default"));
    }

    private static final Map<String, List<InventoryItem>> TenantInventory = new HashMap<String, List<InventoryItem>>() {
        {
            put("1cfd6af8-219c-4f64-8fe3-2e8973697bff",
                    Arrays.asList(
                            new InventoryItem("BK001", "C# プログラミング入門", 12),
                            new InventoryItem("BK002", "実践ASP.NET Core開発", 8),
                            new InventoryItem("BK003", "クラウドアーキテクチャ設計", 10),
                            new InventoryItem("BK004", "データベース設計の基礎", 7),
                            new InventoryItem("BK005", "アルゴリズムとデータ構造", 9)));
            put("aee54d7b-1819-484c-a584-fef85a64419e",
                    Arrays.asList(
                            new InventoryItem("IT001", "ノートPC", 15),
                            new InventoryItem("IT002", "デスクトップPC", 10),
                            new InventoryItem("IT003", "ゲーミングPC", 8),
                            new InventoryItem("IT004", "モニター 24インチ", 20),
                            new InventoryItem("IT005", "モニター 27インチ", 12),
                            new InventoryItem("IT006", "メカニカルキーボード", 30),
                            new InventoryItem("IT007", "ワイヤレスマウス", 25),
                            new InventoryItem("IT008", "外付けSSD 1TB", 18),
                            new InventoryItem("IT009", "ネットワークルーター", 14),
                            new InventoryItem("IT010", "USB-C ドッキングステーション", 9)));
            put("default", Arrays.asList(new InventoryItem("XX999", "NOT FOUND", 0)));
        }
    };

    public static class InventoryItem {
        private String id;
        private String name;
        private int count;

        public InventoryItem(String id, String name, int count) {
            this.id = id;
            this.name = name;
            this.count = count;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
