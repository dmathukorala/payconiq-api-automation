package com.payconiq.api.dto.response.auth;

import com.payconiq.api.dto.response.base.BaseResponseDTO;
import lombok.Data;

@Data
public class CreateTokenDTO extends BaseResponseDTO {

    private String token;

}
