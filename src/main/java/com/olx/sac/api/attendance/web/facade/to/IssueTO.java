package com.olx.sac.api.attendance.web.facade.to;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by raphael on 13/10/17.
 */

@Data
public class IssueTO {

    @NotBlank
    @ApiModelProperty(required = true, example = "Já temos retorno deste atendimento?")
    private String description;

    @NotBlank
    @ApiModelProperty(required = true, example = "Dúvidas")
    private String reason;

    @NotBlank
    @ApiModelProperty(required = true, example = "RJ")
    private String state;

    @NotBlank
    @ApiModelProperty(required = true, example = "TELEFONE")
    private String type;
}
