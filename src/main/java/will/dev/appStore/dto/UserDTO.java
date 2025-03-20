package will.dev.appStore.dto;

import will.dev.appStore.entites.User;

import java.util.Date;

public record UserDTO(
        Long id,
        String username,
        String firstName,
        String otherNames,
        String lastName,
        //ImagePath userImagePath,
        String role,
        String email,
        String phone,
        String enterpriseName,
        String emailVerified,
        //String authToken,
        //String emailVerifyToken,
        //String passwordResetToken,
        Date verified,
        User verifiedBy,
        boolean online,
        boolean showOnlineStatus,
        boolean lastTimeActive,
        boolean active,
        String deletedAt
        //Date createdAt,
        //Date updatedAt
) {}
