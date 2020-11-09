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
    public List<? extends Form> getallvocation() throws Exception {
        List<Vocation> list = (List<Vocation>) tableDao .searchallvocation();
        return list;
    }

    @Override
    public Vocation Getvovationbyid(String id) {
        Vocation vocation =tableDao.GetVocationByid(id);
        return vocation ;
    }

    @Override
    public List<? extends Form> getFormsByAdminName(String adminName, FlowDefinition.CHOICE choice)  {
        List<Vocation> res = new ArrayList<>();

        if (FlowDefinition.CHOICE.Vocation.equals(choice)) {
            List<Vocation> list = (List<Vocation>) tableDao.searchallvocation() ;
            ArrayList<FlowDefinition.Node> nodes = FlowDefinition.getPreNodes(adminName);

            FlowDefinition.Node currentNode = FlowDefinition.getCurrentNode(adminName);
            if(currentNode.index == 1 && currentNode.name!="student") {
                List<Vocation> tempres = new ArrayList<>();
                list.forEach(vocation -> {
                    if (vocation.getFormstatus() == 0) {
                        tempres.add(vocation);
                    }
                });
                return tempres;
            }
            assert currentNode.name.equals(adminName);
            AtomicBoolean canProcess = new AtomicBoolean(true);
            int shiftLen = currentNode.index - 1;
            int xorNum = 1<<shiftLen;
            list.forEach(vocation -> {
                /**
                 * 1.
                 * if form.stauts != -1 ,we process it
                 * 2.
                 * form-type-value  form-type
                 *          1       Vocation
                 *          2       Receipt
                 *          ...     ...
                 */
                if (vocation.getFormtype() == 1 && vocation.getFormstatus()!=-1 ) {
                    nodes.forEach(node -> {
                        int idx = node.index;
                        int xor = 1<<(idx - 1);
                        int status = vocation.getFormstatus();
                        int should = status|xor;

                        if (should!=status) {
                            canProcess.set(false);
                        }
                    });
                    int status2 = vocation.getFormstatus();
                    int should = status2 | xorNum;
                    if (should == status2) {
                        canProcess.set(false);
                    }
                    if (canProcess.get()) {
                        res.add(vocation);
                    }
                }
                canProcess.set(true);
            });
        }
//        else {
//            throw new DataOpException("not implement");
//        }

        return res;
    }
    @Override
    public <T extends Form> void updateForm(T form,String adminName, boolean agree) {
        if (agree) {
            FlowDefinition.Node currentNode = FlowDefinition.getCurrentNode(adminName);
            assert currentNode.name.equals(adminName);
            int shiftLen = currentNode.index - 1;
            int xorNum = 1<<shiftLen;
            form.setFormstatus(form.getFormstatus()^xorNum);
        }else {
            form.setFormstatus(-1);
        }
        tableDao.updateTable(form, Form.class);
    }

    @Deprecated
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
                if(form1.getUuid() .equals(form.getUuid())  ) {
                    form1.setFormstatus(-1);
                }
            });
            tableDao.updateTables(forms, Form.class);
        }
    }


    @Override
    public List<? extends Form> getFormsByAdminName(String adminName, FlowDefinition.CHOICE choice) {
        List<Vocation> res = new ArrayList<>();
        if (FlowDefinition.CHOICE.Vocation.equals(choice)) {
            List<Vocation> list = (List<Vocation>) tableDao.getAllTables(Vocation.class);
            ArrayList<FlowDefinition.Node> nodes = FlowDefinition.getPreNodes(adminName);

            FlowDefinition.Node currentNode = FlowDefinition.getCurrentNode(adminName);
            assert currentNode.name.equals(adminName);
            AtomicBoolean canProcess = new AtomicBoolean(true);
            int shiftLen = currentNode.index - 1;
            int xorNum = 1<<shiftLen;
            list.forEach(vocation -> {
                /**
                 * 1.
                 * if form.stauts != -1 ,we process it
                 * 2.
                 * form-type-value  form-type
                 *          1       Vocation
                 *          2       Receipt
                 *          ...     ...
                 */
                if (vocation.getFormtype() == 1 && vocation.getFormstatus()!=-1 ) {
                    nodes.forEach(node -> {
                        int idx = node.index;
                        int xor = 1<<(idx - 1);
                        int status = vocation.getFormstatus();
                        int should = status|xor;
                        if (should!=status) {
                            canProcess.set(false);
                        }
                    });
                    if (canProcess.get()) {
                        res.add(vocation);
                    }
                }
                canProcess.set(true);
            });
        }
//        else {
//            throw new DataOpException("not implement");
//        }

        return res;
    }

    @Override
    public <T extends Form> void updateForm(T form,String adminName, boolean agree) {
        if (agree) {
            FlowDefinition.Node currentNode = FlowDefinition.getCurrentNode(adminName);
            assert currentNode.name.equals(adminName);
            int shiftLen = currentNode.index - 1;
            int xorNum = 1<<shiftLen;
            form.setFormstatus(form.getFormstatus()^xorNum);
        }else {
            form.setFormstatus(-1);
        }
        tableDao.updateTable(form, Form.class);
    }
}
