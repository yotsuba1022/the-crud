package idv.clu.the.crud.module.user.model;

/**
 * @author Carl Lu
 */
public enum Gender {

    MALE("MALE"), FEMALE("FEMALE");

    private final String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String toString() {
        return this.gender;
    }

}
