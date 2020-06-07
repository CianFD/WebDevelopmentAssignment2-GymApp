package controllers;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {
    public static void authenticate(String email, String password) {
        Member member = Member.findByEmail(email);
        Trainer trainer = Trainer.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        }
        if ((trainer != null) && (trainer.checkPassword(password) == true)) {
            session.put("logged_in_Trainerid", trainer.id);
            redirect("/trainerdashboard");
        }
        else {
            redirect("/login");
        }
    }

    public static void signup() {
        render("signup.html");
    }

    public static void register(String name, String email, String password, String address, String gender, float height, float startingWeight) {
        Logger.info("Registering New User " + email);
        Member member = new Member(name, email, password, address, gender, height, startingWeight);
        member.save();
        redirect("/");
    }


    public static void login() {
        render("login.html");
    }

    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        } else {
            login();
        }
        return member;
    }

    public static Trainer getLoggedInTrainer() {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = trainer.findById(Long.parseLong(trainerId));
        } else {
            login();
        }
        return trainer;
    }

    public static void logout() {
        session.clear();
        redirect ("/");
    }
}
