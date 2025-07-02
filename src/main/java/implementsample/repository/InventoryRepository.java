package implementsample.repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import implementsample.dto.InventoryDto;

public class InventoryRepository {
    // 購入者ID定数
    private static final String PURCHASER_A = "f47ac10b-58cc-4372-a567-0e02b2c3d479";
    private static final String PURCHASER_B = "e47ac10b-58cc-4372-a567-0e02b2c3d480";
    private static final String PURCHASER_C = "a47ac10b-58cc-4372-a567-0e02b2c3d481";
    private static final String PURCHASER_D = "c47ac10b-58cc-4372-a567-0e02b2c3d483";
    private static final String PURCHASER_E = "d47ac10b-58cc-4372-a567-0e02b2c3d484";
    private static final String PURCHASER_F = "547ac10b-58cc-4372-a567-0e02b2c3d485";

    public static final Map<String, List<InventoryDto>> TenantInventory = new HashMap<String, List<InventoryDto>>() {
        {
            put("d296b330-3cce-40b6-88c3-035a1d86981f",
                    Arrays.asList(
                            new InventoryDto("BK001", "C# プログラミング入門", 12, "1", PURCHASER_A),
                            new InventoryDto("BK002", "実践ASP.NET Core開発", 8, "3", PURCHASER_B),
                            new InventoryDto("BK003", "クラウドアーキテクチャ設計", 10, "3", PURCHASER_A),
                            new InventoryDto("BK004", "データベース設計の基礎", 7, "3", PURCHASER_C),
                            new InventoryDto("BK005", "アルゴリズムとデータ構造", 9, "3", PURCHASER_B)));
            put("809e9684-764e-423e-92b9-6396de57c250",
                    Arrays.asList(
                            new InventoryDto("IT001", "ノートPC", 15, "1", PURCHASER_D),
                            new InventoryDto("IT002", "デスクトップPC", 10, "3", PURCHASER_E),
                            new InventoryDto("IT003", "ゲーミングPC", 8, "3", PURCHASER_D),
                            new InventoryDto("IT004", "モニター 24インチ", 20, "3", PURCHASER_F),
                            new InventoryDto("IT005", "モニター 27インチ", 12, "3", PURCHASER_E),
                            new InventoryDto("IT006", "メカニカルキーボード", 30, "3", PURCHASER_D),
                            new InventoryDto("IT007", "ワイヤレスマウス", 25, "3", PURCHASER_F),
                            new InventoryDto("IT008", "外付けSSD 1TB", 18, "3", PURCHASER_E),
                            new InventoryDto("IT009", "ネットワークルーター", 14, "3", PURCHASER_D),
                            new InventoryDto("IT010", "USB-C ドッキングステーション", 9, "3", PURCHASER_F)));
            put("default", Arrays.asList(new InventoryDto("XX999", "NOT FOUND", 0, "", "")));
        }
    };

    public static List<InventoryDto> getInventory(String tenantId, String envId) {
         // テナントIDもしくは環境IDがnullまたは空の場合は、空のリストを返す
         if (tenantId == null || tenantId.isEmpty()
                 || envId == null || envId.isEmpty()) {
             return Collections.emptyList();
         }

        List<InventoryDto> inventoryList = TenantInventory.getOrDefault(tenantId, Collections.emptyList());

        return inventoryList.stream()
                .filter(item -> item.getEnvId() != null && item.getEnvId().equals(envId))
                .collect(Collectors.toList());
    }

    public static List<InventoryDto> getInventoryByUser(String tenantId, String envId, String userId) {
        if (tenantId == null || tenantId.isEmpty()
                || envId == null || envId.isEmpty()) {
            return Collections.emptyList();
        }

        List<InventoryDto> inventoryList = TenantInventory.getOrDefault(tenantId, Collections.emptyList());

        return inventoryList.stream()
                .filter(item -> item.getEnvId() != null && item.getEnvId().equals(envId)
                        && (item.getPurchaserId() == null || item.getPurchaserId().equals(userId)))
                .collect(Collectors.toList());
    }
}