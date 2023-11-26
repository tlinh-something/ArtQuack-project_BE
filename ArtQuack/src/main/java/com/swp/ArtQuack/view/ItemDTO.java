package com.swp.ArtQuack.view;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class ItemDTO {
    private int itemID;
    private String itemName;
    private String content;
    private boolean status;
}
