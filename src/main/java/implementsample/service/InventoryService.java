package implementsample.service;

import java.util.List;

import implementsample.dto.InventoryDto;
import implementsample.repository.InventoryRepository;
import saasus.sdk.modules.SaaSusAPI;
import saasus.sdk.util.apiserver.SaasusIdentity;

public class InventoryService {

    /**
     * 在庫情報を取得するAPI
     * @param identity SaasusIdentity オブジェクト
     * @return 在庫情報のリスト
     */
    @SaaSusAPI(path = "getInventory")
    public static List<InventoryDto> getInventory(SaasusIdentity identity) {
        String tenantId = identity.getTenantId();

        return InventoryRepository.getInventory(tenantId);
    }

    /**
     * ユーザごとの在庫情報を取得するAPI
     * @param identity SaasusIdentity オブジェクト
     * @return 在庫情報のリスト
     */
    @SaaSusAPI(path = "getInventoryByUser")
    public static List<InventoryDto> getInventoryByUser(SaasusIdentity identity) {
        String tenantId = identity.getTenantId();
        String userId = identity.getUserId();

        return InventoryRepository.getInventoryByUser(tenantId, userId);
    }
}
