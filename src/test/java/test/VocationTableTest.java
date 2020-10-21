package test;

import com.flow.combination.VocationTable;
import com.flow.repository.Form;
import com.flow.repository.Vocation;

public class VocationTableTest {
    public static void main(String[] args) {
        Vocation vocation = new Vocation();
        vocation.setDescript("dsf");
        VocationTable vocationTable = new VocationTable();
        vocationTable.setNick("dsfas");
        vocationTable.setVocation(vocation);
        System.out.println(vocationTable.getNick());
    }
}
