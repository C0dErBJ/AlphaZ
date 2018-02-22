package com.alphaz.core.authorization.service.impl;

import com.alphaz.core.authorization.pojo.entity.AlphazUserEntity;
import com.alphaz.core.authorization.pojo.entity.QAlphazUserEntity;
import com.alphaz.core.authorization.service.UserService;
import com.alphaz.core.constant.DataState;
import com.alphaz.core.dao.TeamUserDAO;
import com.alphaz.core.authorization.dao.UserDAO;
import com.alphaz.core.pojo.entity.TbTeamusersEntity;
import com.alphaz.core.pojo.viewmodel.DataTableModel;
import com.alphaz.core.pojo.viewmodel.ResponseModel;
import com.alphaz.core.pojo.viewmodel.privilege.MenuOperationModel;
import com.alphaz.core.pojo.viewmodel.user.UserModel;
import com.alphaz.core.pojo.viewmodel.user.UserSearchModel;
import com.alphaz.core.pojo.viewmodel.user.UserUpdateModel;
import com.alphaz.core.pojo.viewmodel.user.UserViewModel;
import com.alphaz.core.authorization.service.PrivilegeService;
import com.alphaz.util.string.DateUtil;
import com.alphaz.util.valid.ValideHelper;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;
    @Resource
    private PrivilegeService privilegeService;
    @Resource
    private TeamUserDAO teamUserDAO;

    @Override
    public ResponseModel addUser(UserModel model) {
        AlphazUserEntity entity = new AlphazUserEntity();
        entity.setUsername(model.username);
        entity.setPassword(model.password);
        entity.setState(DataState.Ava);
        entity.setPhone(model.phone);
        entity = userDAO.save(entity);
        if (model.teamid != null) {
            TbTeamusersEntity team = new TbTeamusersEntity();
            team.setTeamid(model.teamid);
            team.setUserid(entity.getId());
            teamUserDAO.save(team);
        }
        return new ResponseModel(DataState.Ava, "新增成功");
    }

    @Override
    public ResponseModel deleteUser(Long userid) {
        AlphazUserEntity user = this.userDAO.findOne(userid);

        if (user == null) {
            return new ResponseModel(DataState.NAva, "用户不存在");
        }
        user.setState(DataState.NAva);
        this.userDAO.save(user);
        return new ResponseModel(DataState.Ava, "操作成功");
    }

    @Override
    public ResponseModel updateUser(Long userid, UserUpdateModel model) {
        AlphazUserEntity user = this.userDAO.findOne(userid);
        if (user == null) {
            return new ResponseModel(DataState.NAva, "用户未找到");
        }
        if (!ValideHelper.isNullOrEmpty(model.phone)) {
            user.setPhone(model.phone);
        }
        return new ResponseModel(DataState.Ava, "修改成功");
    }

    @Override
    public DataTableModel<List<UserViewModel>> findUser(UserSearchModel model) {
        DataTableModel<List<UserViewModel>> dataTableModel = new DataTableModel<List<UserViewModel>>();
        dataTableModel.setDraw(model.draw);
        BooleanExpression be = QAlphazUserEntity.alphazUserEntity.state.ne(DataState.NAva);
        if (!ValideHelper.isNullOrEmpty(model.keyword)) {
            be = QAlphazUserEntity.alphazUserEntity.username.likeIgnoreCase("%" + model.keyword + "%")
                    .or(QAlphazUserEntity.alphazUserEntity.address.likeIgnoreCase("%" + model.keyword + "%"))
                    .or(QAlphazUserEntity.alphazUserEntity.wechat.likeIgnoreCase("%" + model.keyword + "%"))
                    .or(QAlphazUserEntity.alphazUserEntity.weibo.likeIgnoreCase("%" + model.keyword + "%"));
        }
        Long filteredSize = this.userDAO.JQF()
                .select(QAlphazUserEntity.alphazUserEntity)
                .from(QAlphazUserEntity.alphazUserEntity)
                .where(be).fetchCount();
        Long totle = this.userDAO.JQF()
                .select(QAlphazUserEntity.alphazUserEntity)
                .from(QAlphazUserEntity.alphazUserEntity)
                .where(QAlphazUserEntity.alphazUserEntity.state.ne(DataState.NAva)).fetchCount();
        dataTableModel.setRecordsFiltered(filteredSize);
        dataTableModel.setRecordsTotal(totle);
        List<UserViewModel> userEntities = this.userDAO.JQF()
                .select(QAlphazUserEntity.alphazUserEntity)
                .from(QAlphazUserEntity.alphazUserEntity)
                .where(be).limit(model.length).offset(model.start / model.length).fetch().stream()
                .map(a -> new UserViewModel() {{
                    setUsername(a.getUsername());
                    setUserid(a.getId());
                    setPhone(a.getPhone());
                    setRegistertime(a.getCreateTime());
                    setBirthday(a.getBirthday());
                }}).collect(Collectors.toList());
        dataTableModel.setData(userEntities);
        return dataTableModel;
    }

    @Override
    public String findEmailByUsername(String username) {
        AlphazUserEntity userEntity = this.userDAO.findByUsername(username);
        if (userEntity == null) {
            return null;
        }
        return userEntity.getEmail();
    }

    @Override
    public ResponseModel changePassword(String username, String password) {
        AlphazUserEntity userEntity = this.userDAO.findByUsername(username);
        if (userEntity == null) {
            return new ResponseModel(DataState.NAva, "用户不存在");
        }
        userEntity.setPassword(password);
        this.userDAO.save(userEntity);
        return new ResponseModel(DataState.Ava, "操作成功");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userDAO.findByUsername(username);
    }
}
