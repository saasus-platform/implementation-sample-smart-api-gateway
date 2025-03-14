package implementsample.service;

import java.util.List;

import implementsample.repository.InventoryRepository;
import implementsample.util.TenantApiClient;
import saasus.sdk.modules.SaaSusAPI;
import implementsample.dto.InventoryDto;

public class InventoryService {
    public List<InventoryDto> getInventory(String tenantId) {
        // テナントIDに対応する在庫情報取得
        return InventoryRepository.TenantInventory.getOrDefault(tenantId,
                InventoryRepository.TenantInventory.get("default"));
    }

    @SaaSusAPI(path = "getInventory")
    public static List<InventoryDto> getInventoryEntryPoint(String xApiKey) {
        // APIキーからテナントIDを取得
        TenantApiClient tenantClient = new TenantApiClient();
        String tenantId = tenantClient.getTenantIdFromApiKey(xApiKey);

        return InventoryRepository.TenantInventory.getOrDefault(tenantId,
                InventoryRepository.TenantInventory.get("default"));
    }
}
