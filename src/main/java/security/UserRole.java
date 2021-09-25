package security;

import com.google.common.collect.Sets;

import java.util.Set;

import static security.UserPermission.*;

public enum UserRole {
    HR(Sets.newHashSet(USER_READ)),
    EMPLOYEE(Sets.newHashSet(USER_READ, USER_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions){
        this.permissions = permissions;
    }


}
