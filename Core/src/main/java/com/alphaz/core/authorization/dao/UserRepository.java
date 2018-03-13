package com.alphaz.core.authorization.dao;

import com.alphaz.core.authorization.entity.AlphazUserEntity;
import com.alphaz.infrastructure.dao.jpa.BaseRepository;


/**
 * Created by admin on 2017/1/13.
 */
public interface UserRepository extends BaseRepository<AlphazUserEntity, Long> {

//    AlphazUserEntity findFirstByUsernameAndPasswordAndState(String username, String password, State state);

//    @Query(value = "SELECT new com.alphaz.core.pojo.dto.UserRoleDTO(a.id , a.username, c.id , c.roleName,a.password)" +
//            " FROM AlphazUserEntity  a , AlphazUserRoleEntity  b  , AlphazRoleEntity  c  " +
//            "WHERE a.state = 0 AND c.state = 0 and a.username=?1 and a.id=b.userid and b.roleid=c.id")
//    List<UserRole> getUserRole(String username);

//    @Query(value = "SELECT new com.alphaz.core.pojo.dto.UserRoleDTO(a.id , a.username, c.id , c.roleName,a.password)" +
//            " FROM AlphazUserEntity  a , AlphazUserRoleEntity  b  , AlphazRoleEntity  c  " +
//            "WHERE a.state = 0 AND c.state = 0 and a.id=?1 and a.id=b.userid and b.roleid=c.id")
//    List<UserRole> getUserRole(Long userid);

//    @Query("SELECT  new com.alphaz.core.pojo.dto.MenuOperationDTO(b.id,b.menuName, c.id,c.operationName,b.label,c.label,b.parentid,b.url,b.icon,c.icon,b.sort,e.state) " +
//            "FROM AlphazRoleEntity a , AlphazUserRoleEntity f , AlphazMenuEntity b , AlphazOperationEntity c , AlphazMenuOperationEntity d , AlphazRMOEntity e ,AlphazUserEntity  g " +
//            "WHERE a.id = f.roleid" +
//            " AND a.id = e.rid " +
//            "AND b.id = d.menuid " +
//            "and d.operationid = c.id " +
//            "and e.moid = d.id " +
//            "and g.id = f.userid " +
//            "and a.state = 0 " +
//            "and b.state = 0 " +
//            "and c.state = 0 " +
//            "and g.id =?1")
//    List<MenuOperation> getMenuOperationByUserid(Long userid);

//    @Query("SELECT  new com.alphaz.core.pojo.dto.MenuOperationDTO(b.id,b.menuName, c.id,c.operationName,b.label,c.label,b.parentid,b.url,b.icon,c.icon,b.sort,e.state) " +
//            "FROM AlphazRoleEntity a ," +
//            "AlphazMenuEntity b ," +
//            " AlphazOperationEntity c , " +
//            "AlphazMenuOperationEntity d ," +
//            " AlphazRMOEntity e " +
//            " where a.id = e.rid " +
//            "AND b.id = d.menuid " +
//            "and d.operationid = c.id " +
//            "and e.moid = d.id " +
//            "and a.state = 0 " +
//            "and b.state = 0 " +
//            "and c.state = 0 " +
//            "and a.id =?1")
//    List<MenuOperation> getMenuOperationByRoleid(Long roleid);

//    @Query("SELECT DISTINCT new com.alphaz.core.pojo.dto.MenuOperationDTO(b.id,b.menuName, c.id,c.operationName,b.label,c.label,b.parentid,b.url,b.icon,c.icon,b.sort) " +
//            "FROM  AlphazMenuEntity b , AlphazOperationEntity c , AlphazMenuOperationEntity d " +
//            "where b.id = d.menuid " +
//            "and d.operationid = c.id " +
//            "and b.state = 0 " +
//            "and c.state = 0 "
//    )
//    List<MenuOperation> getALLMenuOperation();

//    @Query("SELECT DISTINCT new com.alphaz.core.pojo.dto.RoleDTO(a.id,a.label,a.description,a.editable) " +
//            "FROM AlphazRoleEntity a , AlphazUserRoleEntity f , AlphazMenuEntity b   ,AlphazUserEntity  g " +
//            "WHERE a.id = f.roleid " +
//            "and g.id = f.userid " +
//            "and a.state = 0 " +
//            "and b.state = 0 " +
//            "and g.id =?1")
//    List<Role> findRolesByUserid(Long userid);

    //    @Query("SELECT DISTINCT new com.alphaz.core.pojo.dto.RoleDTO(a.id,a.label,a.description,a.editable) " +
//            "FROM AlphazRoleEntity a " +
//            "where a.state =?1")
//    List<Role> findALLRolesByState(State state);
    AlphazUserEntity findByUsername(String username);
}
