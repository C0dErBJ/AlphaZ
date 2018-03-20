import com.alphaz.api.Application;
import com.alphaz.core.authorization.user.User;
import com.alphaz.core.authorization.user.UserDomainService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    private UserDomainService userDomainService;

    @Test
    public void test() {
        User aa = new User();
        aa.setId(5l);
        userDomainService.changePassword("a", aa);
        System.out.println("a");
    }
}
