package com.phone.modules.vo;

import com.phone.modules.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**

 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO extends User {
    private Long currentPage = 1L;
    private Long pageSize = 1L;
}
