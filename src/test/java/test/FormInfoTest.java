package test;

import com.flow.repository.Vocation;
import com.flow.service.FormInfo;
import com.flow.service.FormInfoImpl;

import java.util.List;

public class FormInfoTest {
    public static void main(String[] args) throws Exception {
        FormInfo formInfo = new FormInfoImpl();
        List<Vocation> list = (List<Vocation>) formInfo.getVocationListByNick("stu");
        for (Vocation vocation:list) {
            System.out.println(vocation.getUuid());
        }


    }
}
