package com.flow.service;

import com.flow.dao.TableDao;
import com.flow.exdException.DataOpException;
import com.flow.flowdefinition.FlowDefinition;
import com.flow.repository.Form;
import com.flow.repository.Vocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 蔡小蔚
 */
@Service
public class FormInfoImpl implements FormInfo {

    @Autowired
    private TableDao tableDao;


    @Override
    public List<? extends Form> getVocationListByNick(String nick) throws Exception {
        List<Vocation> list = (List<Vocation>) tableDao.searchRoundForm(nick, Vocation.class, null);
        return list;
    }

    @Override
    public void insertVocation(Vocation vocation) {
        tableDao.insertTable(vocation, Vocation.class, vocation.getStarttime(), vocation.getLasttime(), vocation.getDescript());
    }

    @Override
    public void updateVocation(Vocation vocation, String adminName, boolean agree) {
        Form form = vocation;
        if (agree) {
            ArrayList<FlowDefinition.Node> nodes = FlowDefinition.getPreNodes(adminName);
            try {
                FlowDefinition.Node currentNode = FlowDefinition.getCurrentNode(adminName);
                assert currentNode.name.equals(adminName);
                AtomicBoolean canProcess = new AtomicBoolean(true);
                int shiftLen = currentNode.index - 1;
                int xorNum = 1<<shiftLen;

                List<Form> forms = (List<Form>) tableDao.getAllTables(Form.class);
                forms.forEach(form1 -> {
                    /**
                     * 1.
                     * if form.stauts != -1 ,we process it
                     * 2.
                     * form-type-value  form-type
                     *          1       Vocation
                     *          2       Receipt
                     *          ...     ...
                     */
                    if (form1.getFormtype() == 1 && form1.getFormstatus()!=-1 ) {
                        nodes.forEach(node -> {
                            int idx = node.index;
                            int xor = 1<<(idx - 1);
                            int status = form1.getFormstatus();
                            int should = status|xor;
                            if (should!=status) {
                                canProcess.set(false);
                            }
                        });
                        if (canProcess.get()) {
                            form1.setFormstatus(form1.getFormstatus()^xorNum);
                        }
                    }
                    canProcess.set(true);
                });
                tableDao.updateTables(forms, Form.class);
            }
            catch (DataOpException ignored) {

            }
        }else {
            List<Form> forms = (List<Form>) tableDao.getAllTables(Form.class);
            forms.forEach(form1 -> {
                form1.setFormstatus(-1);
            });
        }
    }
}
