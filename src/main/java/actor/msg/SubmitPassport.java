package actor.msg;

import pojo.Passport;

/**
 * Created by fabienbk on 14/09/17.
 */
public class SubmitPassport {
    private Passport passport;

    public SubmitPassport(Passport passport) {
        this.passport = passport;
    }

    public Passport getPassport() {
        return passport;
    }
}
