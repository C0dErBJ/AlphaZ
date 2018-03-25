import com.alphaz.api.Application;
import com.alphaz.core.authorization.SignInService;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class test {
    @Resource
    private UserService userService;
    @Resource
    private SignInService signInService;

    @Test
    @Transactional
    public void test() {
        User aa = userService.findById(1l);
        userService.changePassword("123456", aa);

        User user = signInService.login("admin", "123456");
        System.out.println(user);
    }
}
