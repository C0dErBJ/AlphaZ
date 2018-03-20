package com.alphaz.application.authorization.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.service.impl
 * User: C0dEr
 * Date: 2016-11-10
 * Time: 14:58
 * Description:
 *
 * @author c0der
 */
@Service
@Transactional
public class UserServiceImpl  {
//    @Resource
//    private UserRepository userRepository;
//    @Resource
//    private PrivilegeDomainService privilegeService;
//
//    @Override
//    public ResponseModel addUser(UserModel model) {
//        AlphazUserEntity entity = new AlphazUserEntity();
//        entity.setUsername(model.username);
//        entity.setPassword(model.password);
//        entity.setState(State.ACTIVE);
//        entity.setPhone(model.phone);
//        entity = userRepository.save(entity);
//        return new ResponseModel();
//    }
//
//    @Override
//    public ResponseModel deleteUser(Long userid) {
//        AlphazUserEntity user = this.userRepository.getOne(userid);
//
//        if (user == null) {
//            return new ResponseModel(new ErrorInfo("用户不存在"));
//        }
//        user.setState(State.DELETED);
//        this.userRepository.save(user);
//        return new ResponseModel();
//    }
//
//    @Override
//    public ResponseModel updateUser(Long userid, UserUpdateModel model) {
//        AlphazUserEntity user = this.userRepository.getOne(userid);
//        Object a = new Object();
//        if (user == null) {
//            return new ResponseModel();
//        }
//        if (!ValideHelper.isNullOrEmpty(model.phone)) {
//            user.setPhone(model.phone);
//        }
//        return new ResponseModel();
//    }
//
//    @Override
//    public String findEmailByUsername(String username) {
//        AlphazUserEntity userEntity = this.userRepository.findByUsername(username);
//        if (userEntity == null) {
//            return null;
//        }
//        return userEntity.getEmail();
//    }
//
//    @Override
//    public ResponseModel changePassword(String username, String password) {
//        AlphazUserEntity userEntity = this.userRepository.findByUsername(username);
//        if (userEntity == null) {
//            return new ResponseModel(new ErrorInfo("用户不存在"));
//        }
//        userEntity.setPassword(password);
//        this.userRepository.save(userEntity);
//        return new ResponseModel();
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AlphazUserEntity entity = this.userRepository.findByUsername(username);
//        UserDetails user = new UserDetails() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return null;
//            }
//
//            @Override
//            public String getPassword() {
//                return entity.getPassword();
//            }
//
//            @Override
//            public String getUsername() {
//                return entity.getUsername();
//            }
//
//            @Override
//            public boolean isAccountNonExpired() {
//                return entity.isAccountNonExpired();
//            }
//
//            @Override
//            public boolean isAccountNonLocked() {
//                return entity.isAccountNonLocked();
//            }
//
//            @Override
//            public boolean isCredentialsNonExpired() {
//                return entity.isCredentialsNonExpired();
//            }
//
//            @Override
//            public boolean isEnabled() {
//                return entity.isEnabled();
//            }
//        };
//        return user;
//    }
}
