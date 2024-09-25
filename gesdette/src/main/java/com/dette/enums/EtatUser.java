package com.dette.enums;

public enum EtatUser {
    Activer, Desactiver;

    public static EtatUser getEtatUser(String value) {
        return java.util.Arrays.stream(EtatUser.values())
                .filter(etat -> etat.name().compareTo(value) == 0)
                .findFirst()
                .orElse(null);
    }

    public static EtatUser getEtatById(int id) {
        return java.util.Arrays.stream(EtatUser.values())
                .filter(etat -> etat.ordinal() == (id - 1))
                .findFirst()
                .orElse(null);
    }
}
