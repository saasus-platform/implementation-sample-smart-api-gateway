package implementsample.service;

import java.util.List;

import implementsample.dto.InventoryDto;
import implementsample.repository.InventoryRepository;
import saasus.sdk.modules.SaaSusAPI;
import saasus.sdk.util.apiserver.SaaSusIdentity;

public class InventoryService {
    public List<InventoryDto> getInventory(String tenantId) {
        // テナントIDに対応する在庫情報取得
        return InventoryRepository.TenantInventory.getOrDefault(tenantId,
                InventoryRepository.TenantInventory.get("default"));
    }
    
    // Step 2: ソースコードへのアノテーション設定
    @SaaSusAPI(path = "getInventory")
    public static List<InventoryDto> getInventoryEntryPoint(SaaSusIdentity identity) {
        String tenantId = identity.getTenantId();
        String envId = identity.getEnvId();
         
        return InventoryRepository.getInventory(tenantId, envId);
    }

    @SaaSusAPI(path = "getInventoryByUser")
    public static List<InventoryDto> getInventoryByUser(SaaSusIdentity identity) {
        // ユーザIDを取得
        String tenantId = identity.getTenantId();
        String envId = identity.getEnvId();
        String userId = identity.getUserId();

        return InventoryRepository.getInventoryByUser(tenantId, envId, userId);
    }
}
