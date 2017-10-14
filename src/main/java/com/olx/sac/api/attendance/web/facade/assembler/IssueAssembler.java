package com.olx.sac.api.attendance.web.facade.assembler;

import com.olx.sac.api.attendance.web.facade.to.IssueTO;
import com.olx.sac.domain.model.issue.*;

/**
 * Created by raphael on 13/10/17.
 */
public final class IssueAssembler {
    public static Issue toTO(IssueTO issueTO) {
        return new Issue(new Type(issueTO.getType()),
                new Reason(issueTO.getReason()),
                new State(issueTO.getState()),
                new Description(issueTO.getDescription()));
    }
}
