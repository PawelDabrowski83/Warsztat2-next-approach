package pl.coderslab.userGroup;

public class UserGroupMapper {

    public static UserGroup mapEntityToUserGroup (UserGroupEntity entity) {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(entity.getId());
        userGroup.setName(entity.getName());
        return userGroup;
    }

    public static UserGroupEntity mapUserGroupToEntity (UserGroup userGroup) {
        UserGroupEntity entity = new UserGroupEntity();
        entity.setId(userGroup.getId());
        entity.setName(userGroup.getName());
        return entity;
    }

    public static UserGroup mapDtoToUserGroup (UserGroupDto dto) {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(dto.getId());
        userGroup.setName(dto.getName());
        return userGroup;
    }

    public static UserGroupDto mapUserGroupToDto (UserGroup userGroup) {
        UserGroupDto dto = new UserGroupDto();
        dto.setId(userGroup.getId());
        dto.setName(userGroup.getName());
        return dto;
    }
}
