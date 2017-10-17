package com.olx.factory;

import com.olx.sac.api.attendance.web.facade.to.IssueTO;

/**
 * Created by raphael on 16/10/17.
 */
public final class IssueFactory {

    private IssueFactory(){}
    public static IssueTO create(){
        IssueTO issueTO = new IssueTO();
        issueTO.setType("TELEFONE");
        issueTO.setDescription("Já temos retorno deste atendimento?");
        issueTO.setReason("Dúvidas");
        issueTO.setState("RJ");
        return issueTO;
    }
}
