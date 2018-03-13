package com.alphaz.application.authorization.service.impl;

import com.alphaz.application.authorization.dto.privilege.*;
import com.alphaz.application.authorization.dto.user.UserViewModel;
import com.alphaz.application.authorization.service.PrivilegeService;
import com.alphaz.core.authorization.entity.*;
import com.alphaz.core.authorization.model.permission.Menu;
import com.alphaz.core.authorization.model.permission.MenuOperation;
import com.alphaz.core.authorization.model.permission.Operation;
import com.alphaz.core.authorization.model.role.Role;
import com.alphaz.core.authorization.model.role.RoleMO;
import com.alphaz.core.authorization.model.user.UserRole;
import com.alphaz.core.authorization.dao.RMORepository;
import com.alphaz.core.authorization.dao.RoleRepository;
import com.alphaz.core.authorization.dao.UserRepository;
import com.alphaz.core.service.LocalizationService;
import com.alphaz.infrastructure.domain.constant.common.State;
import com.alphaz.infrastructure.domain.constant.common.Status;
import com.alphaz.infrastructure.domain.model.common.ResponseModel;
import com.alphaz.infrastructure.domain.service.common.BaseServiceImpl;
import com.alphaz.infrastructure.util.extension.StreamPredicate;
import com.alphaz.infrastructure.util.valid.ValideHelper;
import com.querydsl.core.BooleanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.core.service.impl
 * User: C0dEr
 * Date: 2017/7/21
 * Time: 上午10:53
 * Description:This is a class of com.alphaz.core.service.impl
 */
@Transactional
@Service
public class PrivilegeServiceImpl extends BaseServiceImpl implements PrivilegeService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RMORepository rmodao;
    @Autowired
    private RoleRepository roleRepository;
    @Resource
    private LocalizationService localizationService;

    /**
     * 用于权限认证获取当前用户的角色
     *
     * @param username 账户名
     * @return
     */
    @Override
    public UserViewModel getUserRole(String username) {
//        List<UserRole> userRoleDTO = this.userRepository.getUserRole(username);
        List<UserRole> userRoleDTO = new ArrayList<>();
        UserViewModel userViewModel = new UserViewModel();
        if (ValideHelper.isNullOrEmpty(userRoleDTO)) {
            return null;
        }
        userViewModel.setUsername(userRoleDTO.get(0).getUsername());
        userViewModel.setUserid(userRoleDTO.get(0).getUserid());
        userViewModel.setPassword(userRoleDTO.get(0).getPassword());
        Map<Long, String> roles = new HashMap<>();
        userRoleDTO.forEach(a -> roles.put(a.getRoleid(), a.getRolename()));
        userViewModel.setRoles(roles);
        return userViewModel;
    }

    @Override
    public ResponseModel getUserRoleByUsername(String username) {
        return new ResponseModel(Status.SUCCESS, null, this.getUserRole(username));
    }

    @Override
    public MenuOperationModel getMenuOperationByUserid(Long userid) {
//        List<MenuOperation> mo = this.userRepository.getMenuOperationByUserid(userid);
        List<MenuOperation> mo = new ArrayList<>();
        return MOTree(mo);
    }

    @Override
    public MenuOperationModel getMenuOperationByRoleid(Long roleid) {
//        List<MenuOperation> mo = this.userRepository.getMenuOperationByRoleid(roleid);
        List<MenuOperation> mo = new ArrayList<>();
        return MOTree(mo);
    }

    @Override
    public ResponseModel getMOByUserid(Long userid) {
        return new ResponseModel(Status.SUCCESS, null, this.getMenuOperationByUserid(userid));
    }

    @Override
    public ResponseModel getMenuByUserId(Long userid) {
//        List<MenuOperation> dto = this.userRepository.getMenuOperationByUserid(userid);
        List<MenuOperation> dto = new ArrayList<>();
        //由于getMenuOperationByUserid 方法结果是所有operation和menu对应数据，存在重复menu，这里只需要唯一menu
        List<MenuOperation> sorted = dto.stream().filter(StreamPredicate.distinctByKey(p -> p.menuid))
                .sorted(Comparator.comparing(a -> a.menuSort)).collect(Collectors.toList());
        List<MenuViewModel> menuViewModels = new ArrayList<>();
        sorted.forEach(a -> {
            MenuViewModel mvm = new MenuViewModel();
            mvm.setMenuName(a.menuname);
            mvm.setLabel(a.menuLabel);
            mvm.setIcon(a.menuIcon);
            mvm.setUrl(a.url);
            if (a.parentid != null) {
                Optional<MenuOperation> menuOperationDTO = sorted.stream().filter(b -> a.parentid.equals(b.menuid)).findFirst();
                menuOperationDTO.ifPresent(menuOperation1 -> mvm.setChild(new MenuViewModel() {{
                    setUrl(menuOperation1.url);
                    setIcon(menuOperation1.menuIcon);
                    setMenuName(menuOperation1.menuname);
                    setLabel(menuOperation1.menuLabel);
                }}));
            }
            menuViewModels.add(mvm);
        });
        ResponseModel model = new ResponseModel(Status.SUCCESS, null, menuViewModels);
        return model;
    }

    @Override
    public ResponseModel getRoles(Long userid) {
//        List<Role> rolelist = userRepository.findRolesByUserid(userid);
        List<Role> rolelist = new ArrayList<>();
        ResponseModel<List<Role>> model = new ResponseModel(Status.SUCCESS, null, rolelist);
        return model;
    }

    @Override
    @Transactional
    public ResponseModel deleteRole(Long roleid) {
        this.roleRepository.JQF().update(QAlphazRoleEntity.alphazRoleEntity).where(QAlphazRoleEntity.alphazRoleEntity.id.eq(roleid)).set(QAlphazRoleEntity.alphazRoleEntity.state, State.DELETED).execute();
        this.rmodao.deleteAlphazRMOEntitiesByRid(roleid);
        ResponseModel model = new ResponseModel();
        return model;
    }

    @Override
    public ResponseModel<AllRoleMenuVIewModel> getAllRoleAndMenuOperation() {
//        List<MenuOperation> dto = this.userRepository.getALLMenuOperation();
        List<MenuOperation> dto = new ArrayList<>();
        MenuOperationModel menuOperationModel = MOTree(dto);
        AllRoleMenuVIewModel data = new AllRoleMenuVIewModel();

        List<PrivilegeTreeView> list = new ArrayList<>();
        menuOperationModel.keyPair.forEach((key, value) -> list.add(new PrivilegeTreeView() {{
            setText(key.getLabel());
            setData(key.getId().toString());
            setIcon(key.getIcon());
            setChildren(value.stream().map(b -> new PrivilegeTreeView() {{
                setData(key.getId() + "_" + b.getId());
                setText(b.getLabel());
                setIcon(b.getIcon());
            }}).collect(Collectors.toList()));
        }}));
        data.menuOperation = list;
//        data.roleList = this.userRepository.findALLRolesByState(State.ACTIVE);
        ResponseModel model = new ResponseModel(Status.SUCCESS, null, data);
        return model;
    }

    @Override
    public ResponseModel getMOByRoleid(Long roleid) {
        MenuOperationModel moModel = this.getMenuOperationByRoleid(roleid);
        AlphazRoleEntity role = this.roleRepository.getOne(roleid);
        List<PrivilegeTreeView> list = new ArrayList<>();
        moModel.keyPair.forEach((key, value) -> list.add(new PrivilegeTreeView() {{
            setText(key.getLabel());
            setData(key.getId().toString());
            setIcon(key.getIcon());
            setState(new StateBean() {{
                setOpened(true);
                setDisabled(role.getEditable());
            }});
            setChildren(value.stream().map(b -> new PrivilegeTreeView() {{
                setData(key.getId() + "_" + b.getId());
                setText(b.getLabel());
                setIcon(b.getIcon());
                setState(new StateBean() {{
                    setOpened(true);
                    setSelected(b.getIsenabled() == State.DELETED);
                    setDisabled(role.getEditable());
                }});
            }}).collect(Collectors.toList()));
        }}));
        ResponseModel model = new ResponseModel(Status.SUCCESS, null, list);
        return model;
    }

    @Override
    public ResponseModel addRole(String rolename, String description) {
        AlphazRoleEntity entity = new AlphazRoleEntity();
        entity.setLabel(rolename);
        entity.setDescription(description);
        entity.setState(State.ACTIVE);
        entity.setEditable(true);
        AlphazRoleEntity role = roleRepository.save(entity);
        Role dto = new Role(role.getId(), role.getLabel(), role.getDescription(), role.getEditable());
        ResponseModel model = new ResponseModel(Status.SUCCESS, null, dto);
        return model;
    }

    @Override
    public ResponseModel updateRole(Role role) {
        AlphazRoleEntity alphazRoleEntity = this.roleRepository.getOne(role.getId());
        alphazRoleEntity.setLabel(role.getRoleName());
        alphazRoleEntity.setDescription(role.getDescription());
        this.roleRepository.save(alphazRoleEntity);
        ResponseModel model = new ResponseModel(Status.SUCCESS, null, role);
        return model;
    }

    @Override
    public ResponseModel getRoleById(Long roleid) {
        AlphazRoleEntity role = this.roleRepository.getOne(roleid);
        Role roleDTO = new Role(role.getId(), role.getLabel(), role.getDescription(), role.getEditable());
        ResponseModel model = new ResponseModel(Status.SUCCESS, null, roleDTO);
        return model;
    }

    /**
     * dto转化成树形结构
     *
     * @param mo
     * @return
     */
    private MenuOperationModel MOTree(List<MenuOperation> mo) {
        MenuOperationModel model = new MenuOperationModel();
        Map<String, List<String>> namePair = new HashMap<>();
        Map<Menu, List<Operation>> keyPair = new HashMap<>();
        List<Long> menuids = mo.stream().map(MenuOperation::getMenuid).distinct().collect(Collectors.toList());
        menuids.forEach(a -> {
            List<MenuOperation> modto = mo.stream().filter(b -> a == b.getMenuid()).collect(Collectors.toList());
            if (!ValideHelper.isNullOrEmpty(modto)) {
                namePair.put(modto.get(0).getMenuname(), modto.stream().map(MenuOperation::getOprationName).collect(Collectors.toList()));
                keyPair.put(new Menu() {{
                    setId(modto.get(0).getMenuid());
                    setMenuname(modto.get(0).getMenuname());
                    setLabel(modto.get(0).getMenuLabel());
                    setIcon(modto.get(0).getMenuIcon());
                }}, modto.stream().map(b -> new Operation() {{
                    setId(b.getOperationid());
                    setOperationname(b.getOprationName());
                    setLabel(b.getOperationLabel());
                    setIcon(b.getOperationIcon());
                    setIsenabled(b.getIsMenuOpeationEnabled());
                }}).collect(Collectors.toList()));
            }
        });
        model.setKeyPair(keyPair);
        model.setNamePair(namePair);
        return model;
    }

    @Override
    public ResponseModel updateRMObymoid(Long roleid, List<MOValueModel> maps) {
        QAlphazMenuOperationEntity qAlphazMenuOperationEntity = QAlphazMenuOperationEntity.alphazMenuOperationEntity;
        QAlphazRMOEntity qAlphazRMOEntity = QAlphazRMOEntity.alphazRMOEntity;
        BooleanBuilder bb = new BooleanBuilder();
        if (!ValideHelper.isNullOrEmpty(maps)) {
            maps.forEach(a -> bb.or(qAlphazMenuOperationEntity.menuid.eq(a.getMenuid()).and(qAlphazMenuOperationEntity.operationid.eq(a.getOperationid()))));
        }
        //已经存在数据库的角色对应权限
        List<RoleMO> staticMo = this.userRepository.JQF().select(qAlphazRMOEntity.id,
                qAlphazMenuOperationEntity.menuid,
                qAlphazMenuOperationEntity.operationid,
                qAlphazRMOEntity.state,
                qAlphazRMOEntity.moid,
                qAlphazRMOEntity.rid)
                .from(qAlphazRMOEntity).from(qAlphazMenuOperationEntity).where(bb.getValue())
                .where(qAlphazRMOEntity.moid.eq(qAlphazMenuOperationEntity.id)
                        .and(qAlphazRMOEntity.rid.eq(roleid))).fetch().stream()
                .map(a ->
                        new RoleMO(a.get(qAlphazRMOEntity.id),
                                a.get(qAlphazMenuOperationEntity.menuid),
                                a.get(qAlphazMenuOperationEntity.operationid),
                                a.get(qAlphazRMOEntity.moid),
                                a.get(qAlphazRMOEntity.rid),
                                a.get(qAlphazRMOEntity.state)))
                .collect(Collectors.toList());
        //需要存储的角色对应权限，还未在数据库中建立
        List<MOValueModel> needToInsert = maps.stream().
                filter(a -> staticMo.stream().noneMatch(b -> b.getMenuid().equals(a.getMenuid()) && b.getOperationid().equals(a.getOperationid())))
                .collect(Collectors.toList());
        if (needToInsert.size() > 0) {
            BooleanBuilder newbb = new BooleanBuilder();
            needToInsert.forEach(a -> newbb.or(qAlphazMenuOperationEntity.menuid.eq(a.getMenuid()).and(qAlphazMenuOperationEntity.operationid.eq(a.getOperationid()))));
            List<AlphazMenuOperationEntity> alphazMenuOperationEntities = this.userRepository.JQF().select(qAlphazMenuOperationEntity)
                    .from(qAlphazMenuOperationEntity)
                    .where(newbb)
                    .fetch();
//保存新增的角色对应权限
            rmodao.saveAll(alphazMenuOperationEntities.stream().map(b -> {
                AlphazRMOEntity rmoEntity = new AlphazRMOEntity();
                needToInsert.stream().filter(a -> b.getOperationid().equals(a.getOperationid()) && b.getMenuid().equals(a.getMenuid()))
                        .findFirst().ifPresent(c -> {
                    rmoEntity.setState(c.getState());
                    rmoEntity.setRid(roleid);
                    rmoEntity.setMoid(b.getId());
                });
                return rmoEntity;
            }).collect(Collectors.toList()));
        }
        //设置已经有的权限对应角色状态
        maps.forEach(b ->
                staticMo.stream().filter(a -> b.getOperationid().equals(a.getOperationid()) && b.getMenuid().equals(a.getMenuid()))
                        .findFirst()
                        .ifPresent(c -> c.setState(b.getState()))
        );
        //更新已经有的权限对应角色状态
        rmodao.saveAll(staticMo.stream().map(model -> {
            AlphazRMOEntity alphazRMOEntity = new AlphazRMOEntity();
            alphazRMOEntity.setId(model.getId());
            alphazRMOEntity.setMoid(model.getMoid());
            alphazRMOEntity.setRid(model.getRid());
            alphazRMOEntity.setState(model.getState());
            return alphazRMOEntity;
        }).collect(Collectors.toList()));
        ResponseModel model = new ResponseModel();
        return model;
    }
}
