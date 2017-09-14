package pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Fabien Benoit-Koch on 13/09/2017.
 */
public class Identity {

    private UUID id;

    private String name;

    private Passport passport;

    private List<ValidationResult> resultList = new ArrayList<>();

    public Identity(UUID id) {
        this.id = id;
    }

    public List<ValidationResult> getResultList() {
        return resultList;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    private static class ValidationResult {
        private String name;
        private boolean ok;

        private ValidationResult(String name, boolean ok) {
            this.name = name;
            this.ok = ok;
        }

        public String getName() {
            return name;
        }

        public boolean isOk() {
            return ok;
        }
    }
}
