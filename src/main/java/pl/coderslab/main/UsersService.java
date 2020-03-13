package pl.coderslab.main;

import pl.coderslab.users.UserDao;
import pl.coderslab.users.UserDto;
import pl.coderslab.users.UserEntity;
import pl.coderslab.users.UserMapper;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

public class UsersService {

    private final static UserDao USER_DAO = new UserDao();

    public static UserDto[] findUsersByGroupId (int id) {
         UserEntity[] entities = USER_DAO.findAllUserByUserGroupId(id);
         return Arrays.stream(entities)
                 .map(UserMapper::mapEntityToUser)
                 .sorted()
                 .map(UserMapper::mapUserToDto)
                 .toArray(UserDto[]::new);
    }

    public static UserDto findUserById (int id) {
        Optional<UserEntity> entityOptional = Optional.ofNullable(USER_DAO.read(id));
        UserEntity entity = entityOptional.orElseGet(UserEntity::new);
        return UserMapper.mapUserToDto(UserMapper.mapEntityToUser(entity));
    }

    public static UserDto[] findAll() {
        UserEntity[] entities = USER_DAO.findAll();
        return Arrays.stream(entities)
                .map(UserMapper::mapEntityToUser)
                .sorted()
                .map(UserMapper::mapUserToDto)
                .toArray(UserDto[]::new);
    }

    public static boolean createUser(UserDto dto) {
            UserEntity entity = UserMapper.mapUserToEntity(UserMapper.mapDtoToUser(dto));
            entity = USER_DAO.create(entity);
        return entity.getId() > 0;
    }
}
