import com.alphaz.api.Application;
import com.alphaz.core.authorization.SignInService;
import com.alphaz.core.authorization.UserSignInEvent;
import com.alphaz.core.authorization.user.User;
import com.alphaz.core.authorization.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * ProjectName: alphaz
 * PackageName: PACKAGE_NAME
 * User: C0dEr
 * Date: 2017/5/3
 * Time: 下午5:04
 * Description:This is a class of PACKAGE_NAME
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
public class test {
    @Resource
    private UserService userService;

    @Test
//    @Transactional
    public void test() {
        User user = new User();
        user.setId(1l);
        user.setUsername("adf");
        userService.changePassword("123456", user);
        System.out.println("aa");
    }
}
