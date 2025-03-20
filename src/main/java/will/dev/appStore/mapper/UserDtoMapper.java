package will.dev.appStore.mapper;

import org.springframework.stereotype.Component;
import will.dev.appStore.dto.UserDTO;
import will.dev.appStore.entites.User;

import java.util.function.Function;

@Component
public class UserDtoMapper implements Function<User, UserDTO> {
    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getOtherNames(),
                user.getLastName(),
                user.getRole(),
                user.getEmail(),
                user.getLastTimeActive(),
                user.getPhone(),
                user.getEnterpriseName(),
                user.getEmailVerified(),
                user.getVerifiedBy(),
                user.isVerified(),
                user.isOnline(),
                user.isActive(),
                user.isShowOnlineStatus(),
                user.getLastTimeActive());
    }
}
