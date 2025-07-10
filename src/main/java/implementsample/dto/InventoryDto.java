package implementsample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InventoryDto {
    private String id;
    private String name;
    private int count;
    private String inventoryManagerId;
}
