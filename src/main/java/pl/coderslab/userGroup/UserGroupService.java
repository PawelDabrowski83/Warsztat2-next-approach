package pl.coderslab.userGroup;

import pl.coderslab.userGroup.UserGroupDao;
import pl.coderslab.userGroup.UserGroupDto;
import pl.coderslab.userGroup.UserGroupEntity;
import pl.coderslab.userGroup.UserGroupMapper;

import java.util.Arrays;
import java.util.Comparator;

public class UserGroupService {

    private final static UserGroupDao USER_GROUP_DAO = new UserGroupDao();

    public static UserGroupDto[] findAll() {
        UserGroupEntity[] entities = USER_GROUP_DAO.findAll();
        return Arrays.stream(entities)
                .map(UserGroupMapper::mapEntityToUserGroup)
                .sorted(Comparator.naturalOrder())
                .map(UserGroupMapper::mapUserGroupToDto)
                .toArray(UserGroupDto[]::new);
    }

    public static void createUserGroup (UserGroupDto dto) {
        USER_GROUP_DAO.create(
                UserGroupMapper.mapUserGroupToEntity(
                        UserGroupMapper.mapDtoToUserGroup(
                                dto)));
    }

    public static UserGroupDto readUserGroup (int id) {
        return UserGroupMapper.mapUserGroupToDto(
                UserGroupMapper.mapEntityToUserGroup(
                        USER_GROUP_DAO.read(
                                id)));
    }

    public static void editUserGroup (UserGroupDto dto) {
        USER_GROUP_DAO.update(
                UserGroupMapper.mapUserGroupToEntity(
                        UserGroupMapper.mapDtoToUserGroup(
                                dto)));
    }

    public static void deleteUserGroup (UserGroupDto dto) {
        USER_GROUP_DAO.delete(dto.getId());
    }
}
