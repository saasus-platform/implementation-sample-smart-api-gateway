package implementsample.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import implementsample.dto.InventoryDto;

public class InventoryRepository {
    public static final Map<String, List<InventoryDto>> TenantInventory = new HashMap<String, List<InventoryDto>>() {
        {
            put("d296b330-3cce-40b6-88c3-035a1d86981f",
                    Arrays.asList(
                            new InventoryDto("BK001", "C# プログラミング入門", 12),
                            new InventoryDto("BK002", "実践ASP.NET Core開発", 8),
                            new InventoryDto("BK003", "クラウドアーキテクチャ設計", 10),
                            new InventoryDto("BK004", "データベース設計の基礎", 7),
                            new InventoryDto("BK005", "アルゴリズムとデータ構造", 9)));
            put("809e9684-764e-423e-92b9-6396de57c250",
                    Arrays.asList(
                            new InventoryDto("IT001", "ノートPC", 15),
                            new InventoryDto("IT002", "デスクトップPC", 10),
                            new InventoryDto("IT003", "ゲーミングPC", 8),
                            new InventoryDto("IT004", "モニター 24インチ", 20),
                            new InventoryDto("IT005", "モニター 27インチ", 12),
                            new InventoryDto("IT006", "メカニカルキーボード", 30),
                            new InventoryDto("IT007", "ワイヤレスマウス", 25),
                            new InventoryDto("IT008", "外付けSSD 1TB", 18),
                            new InventoryDto("IT009", "ネットワークルーター", 14),
                            new InventoryDto("IT010", "USB-C ドッキングステーション", 9)));
            put("default", Arrays.asList(new InventoryDto("XX999", "NOT FOUND", 0)));
        }
    };
}
