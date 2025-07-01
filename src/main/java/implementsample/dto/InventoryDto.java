package implementsample.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class InventoryDto {
    private String id;
    private String name;
    private int count;
    private String envId;
    private String purchaserId;
}
