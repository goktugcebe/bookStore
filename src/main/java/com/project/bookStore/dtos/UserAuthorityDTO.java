package com.project.bookStore.dtos;

import com.project.bookStore.common.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAuthorityDTO extends BaseDTO {
    private int authorityId;
    private int userId;
}
