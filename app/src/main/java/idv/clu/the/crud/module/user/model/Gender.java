package idv.clu.the.crud.module.user.model;

import java.util.Arrays;

/**
 * @author Carl Lu
 */
public enum Gender {

    MALE("MALE"), FEMALE("FEMALE");

    private final String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public static Gender from(String genderStr) {
        return Arrays.stream(Gender.values())
                .filter(value -> value.gender.equals(genderStr))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid gender value."));
    }

    public String toString() {
        return this.gender;
    }

}
