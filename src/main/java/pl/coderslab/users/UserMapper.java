package pl.coderslab.users;

import pl.coderslab.userGroup.UserGroupDao;
import pl.coderslab.userGroup.UserGroupMapper;

public class UserMapper {

    private final static UserGroupDao USER_GROUP_DAO = new UserGroupDao();

    public static User mapDtoToUser (UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUserGroup(
                UserGroupMapper.mapEntityToUserGroup(
                        USER_GROUP_DAO.read(
                                dto.getUserGroupId())));
        return user;
    }

    public static UserDto mapUserToDto (User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setUserGroupId(user.getUserGroup().getId());
        return dto;
    }

    public static User mapEntityToUser (UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setUserGroup(
                UserGroupMapper.mapEntityToUserGroup(
                        USER_GROUP_DAO.read(
                                entity.getUserGroupId())));
    return user;
    }

    public static UserEntity mapUserToEntity (User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setUserGroupId(user.getUserGroup().getId());
        return entity;
    }
}
