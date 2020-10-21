package test;

import com.flow.service.UserInfo;
import com.flow.service.UserInfoImpl;

public class UserInfoTest {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfoImpl();
        System.out.println(userInfo.getUserByNick("stu").toString());
    }
}
