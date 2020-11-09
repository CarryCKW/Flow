package com.flow.repository;

import com.flow.flowdefinition.FlowDefinition;

import java.io.IOException;

/**
 * @author 蔡小蔚
 */
public class FlowDefInfo {
    private FlowDefType type = null;
    private String beforeChangeDescript = null;
    private String changedDescript = null;


    public void setType(FlowDefType type) {
        this.type = type;
    }

    public FlowDefInfo() throws IOException {
        String beforedef = FlowDefinition.getFlowDefinition(FlowDefinition.CHOICE.Vocation);
        this.setBeforeChangeDescript(beforedef);
    }

    private void setBeforeChangeDescript(String beforeChangeDescript) {
        this.beforeChangeDescript = beforeChangeDescript;
    }

    public void setChangedDescript(String changedDescript) {
        this.changedDescript = changedDescript;
    }

    public FlowDefType getType() {
        return type;
    }

    public String getBeforeChangeDescript() {
        return beforeChangeDescript;
    }

    public String getChangedDescript() {
        return changedDescript;
    }
}
