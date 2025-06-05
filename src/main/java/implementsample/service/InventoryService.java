package implementsample.service;

import java.util.List;

import implementsample.dto.InventoryDto;
import implementsample.repository.InventoryRepository;
import implementsample.util.TenantApiClient;
import saasus.sdk.modules.SaaSusAPI;

public class InventoryService {
    public List<InventoryDto> getInventory(String tenantId) {
        // テナントIDに対応する在庫情報取得
        return InventoryRepository.TenantInventory.getOrDefault(tenantId,
                InventoryRepository.TenantInventory.get("default"));
    }

    // Step 2: ソースコードへのアノテーション設定
    @SaaSusAPI(path = "getInventory")
    public static List<InventoryDto> getInventoryEntryPoint(String inventoryId) {
        // APIキーからテナントIDを取得
        TenantApiClient tenantClient = new TenantApiClient();
        String tenantId = tenantClient.getTenantIdFromApiKey(inventoryId);

        return InventoryRepository.TenantInventory.getOrDefault(tenantId,
                InventoryRepository.TenantInventory.get("default"));
    }
}
