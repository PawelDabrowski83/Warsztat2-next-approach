package pl.coderslab.main;

import pl.coderslab.userGroup.*;

import java.util.Arrays;
import java.util.Optional;

public class GroupService {

    private final static UserGroupDao USER_GROUP_DAO = new UserGroupDao();

    public static UserGroupDto[] findAllGroups() {
        UserGroupEntity[] entities = USER_GROUP_DAO.findAll();
        return Arrays.stream(entities)
                .map(UserGroupMapper::mapEntityToUserGroup)
                .sorted()
                .map(UserGroupMapper::mapUserGroupToDto)
                .toArray(UserGroupDto[]::new);
    }

    public static UserGroupDto findGroupById(int id) {
        Optional<UserGroupEntity> entityOptional = Optional.ofNullable(USER_GROUP_DAO.read(id));
        UserGroupEntity entity = entityOptional.orElseGet(UserGroupEntity::new);
        return UserGroupMapper.mapUserGroupToDto(UserGroupMapper.mapEntityToUserGroup(entity));
    }
}
