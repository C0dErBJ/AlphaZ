package com.alphaz.core.authorization.user

import com.alphaz.core.localization.LocalizationService
import com.alphaz.infrastructure.domain.Policy
import com.alphaz.infrastructure.exception.BusinessErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

/**
 *@Author: c0der
 *@Date: 下午1:42 2018/3/25
 *@Description:
 */
@Component
open class UserPolicy : Policy<User> {
    @Autowired
    private lateinit var l: LocalizationService;
    @Autowired
    private lateinit var userRepository: UserRepository

    val passwordEncoder: PasswordEncoder
        get() = BCryptPasswordEncoder()


    open fun passwordStrength(t: User): Boolean {
        assert(t.password != null) {
            l.getMessage("PasswordShouldBePresent")
        }
        var hasNum = false;
        var hasChar = false;
        t.password!!.forEach { it ->
            apply {
                hasNum = it.isDigit();
                hasChar = it.isLetter();
            }
        }
        if (hasNum && hasChar) {
            return true;
        }
        throw BusinessErrorException(l.getMessage("PasswordNotStrongEnough"));
    }

    /**
     * 注册验证
     */
    open fun userSignUpPolicy(t: User): Boolean {
        assert(t.username != null) {
            l.getMessage("UsernameShouldBePresent")
        }
        val dupUser = userRepository.findFirstByUsername(t.username!!)
        if (dupUser != null) {
            throw BusinessErrorException(l.getMessage("UserNameHasBeenUsed"))
        }
        return true
    }

    /**
     * 登录验证
     */
    open fun userSignInPolicy(t: User): Boolean {

        if (t.loginFailCount > 5) {
            t.lock()
            userRepository.save(t);
            return false;
        }

        return true;
    }

    /**
     * 密码加密
     */
    open fun encode(password: String): String {
//        val messageDigest = MessageDigest.getInstance("SHA-256")
//        messageDigest.update(password.toByteArray());
//        return Hex.encodeHexString(messageDigest.digest())
        return passwordEncoder.encode(password);
    }

}