package com.flow.service;

import com.flow.flowdefinition.FlowDefinition;
import com.flow.repository.Form;
import com.flow.repository.Vocation;
import com.flow.flowdefinition.FlowDefinition ;
import java.util.List;

/**
 * @author 蔡小蔚
 */
public interface FormInfo {
    /**
     * return the list of vocation by nick
     * @param nick user's nick
     * @return list
     * @throws Exception SQLExecption
     */
    List<? extends Form> getVocationListByNick(String nick) throws Exception;

    /**
     * insertVocation
     * @param vocation the Object Vocation which be inserted
     */
    void insertVocation(Vocation vocation);

    /**
     *  discard!!!
     * process the Vocation message by admin
     * @param vocation the Class instance of Vocation
     * @param adminName admin name
     * @param agree he will agree the vocation if true, else not
     */
    void updateVocation(Vocation vocation, String adminName, boolean agree);

<<<<<<< HEAD
    List<? extends Form> getallvocation() throws Exception;
=======
>>>>>>> 856fee7df1fd1023d8daaa8ba64cabda829db61a

    List<? extends Form> getFormsByAdminName(String adminName, FlowDefinition.CHOICE choice);

    <T extends Form> void updateForm(T form, String adminName, boolean agree);
<<<<<<< HEAD

    Vocation  Getvovationbyid(String id );
=======
>>>>>>> 856fee7df1fd1023d8daaa8ba64cabda829db61a
}
