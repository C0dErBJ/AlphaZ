import com.alphaz.api.Application
import com.alphaz.application.authorization.user.UserAppService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import javax.annotation.Resource
import javax.jdo.annotations.Transactional

/**
 *@Author: c0der
 *@Date: 下午10:32 2018/4/1
 *@Description:
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [(Application::class)])
class UserTest {
    @Resource
    private lateinit var userAppService: UserAppService

    @Test
    @Transactional
    fun test() {
        val user = userAppService.getListByPage(null, PageRequest.of(1, 10))
        //        User user = new User();
        //        user.setId(1l);
        //        user.setUsername("adf");
        //        userService.changePassword("123456", user);
        //        System.out.println("aa");
    }
}