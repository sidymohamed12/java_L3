package com.dette.enums;

public enum Role {
    admin, boutiquier, client;

    public static Role getRole(String value) {
        return java.util.Arrays.stream(Role.values())
                .filter(role -> role.name().compareTo(value) == 0)
                .findFirst()
                .orElse(null);
    }

    public static Role getRoleById(int id) {
        return java.util.Arrays.stream(Role.values())
                .filter(role -> role.ordinal() == (id - 1))
                .findFirst()
                .orElse(null);
    }

}
