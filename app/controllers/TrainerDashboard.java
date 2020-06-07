package controllers;


import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class TrainerDashboard extends Controller {

    public static void index() {
        Logger.info("Rendering Trainer Dasboard");
        Trainer trainer = Accounts.getLoggedInTrainer();
        List<Member> members = Member.findAll();
        render("trainerdashboard.html", members);
    }

    public static void memberIndex(Long id) {
        Member member = Member.findById(id);
        List<Assessment> assessments = member.assessments;
        double bmi = 0;
        String bmiCat = "Undefined";
        boolean iBW = false;
        if (assessments.size() > 0) {
            bmi = Math.round(GymUtility.calculateBMI(member, assessments.get(0))*100.0)/100.0;
            bmiCat = GymUtility.determineBMICategory(bmi);
            iBW = GymUtility.isIdealBodyWeight(member, assessments.get(0));
        }
        Logger.info("Rendering Member - Trainer View");
        render ("trainermembers.html", member, assessments, bmi, bmiCat, iBW);
    }

    public static void deleteMember (Long id)
    {
        Member member = Member.findById(id);
        Logger.info ("Removing" + member.name);
        member.delete();
        redirect("/trainerdashboard");
    }

    public static void addMember(String name, String email, String password, String address, String gender, float height, float startingWeight)
    {
        Logger.info("Registering New Member " + name);
        Member member = new Member(name, email, password, address, gender, height, startingWeight);
        member.save();
        redirect ("/trainerdashboard");
    }

    public static void addComment(Long id, Long assessmentid, String comment) {
        Member member = Member.findById(id);
        Assessment assessment = Assessment.findById(assessmentid);
        assessment.setComment(comment);
        assessment.save();
        Logger.info("Adding a Comment" + comment);
        redirect("/trainerdashboard");
    }
}
