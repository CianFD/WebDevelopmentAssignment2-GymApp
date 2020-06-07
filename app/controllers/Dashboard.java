package controllers;


import models.Assessment;
import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller
{
  public static void index() {
    Logger.info("Rendering Dasboard");
    Member member = Accounts.getLoggedInMember();
    List<Assessment> assessments = member.assessments;
    double bmi = 0;
    String bmiCat = "Undefined";
    boolean iBW = false;
    if (assessments.size() > 0) {
      bmi = Math.round(GymUtility.calculateBMI(member, assessments.get(0))*100.0)/100.0;
      bmiCat = GymUtility.determineBMICategory(bmi);
      iBW = GymUtility.isIdealBodyWeight(member, assessments.get(0));
    }
    render("dashboard.html", member, assessments, bmi, bmiCat, iBW);
  }


    public static void addAssessment(String datetime, double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment)
  {
    Assessment assessment = new Assessment(datetime, weight, chest, thigh, upperArm, waist, hips, comment);
    Member member= Accounts.getLoggedInMember();
    member.assessments.add(0, assessment);
    member.save();
    Logger.info("Add Assessment" + weight + chest + thigh + upperArm + waist + hips);
    redirect("/dashboard");
  }

  public static void deleteAssessment(Long assessmentid) {
    Member member = Accounts.getLoggedInMember();
    Assessment assessment = Assessment.findById(assessmentid);
    member.assessments.remove(assessment);
    member.save();
    assessment.delete();
    Logger.info("Deleting " + assessment.id);
    redirect("/dashboard");
  }

  public static void editMember(Long id, String name, String email, String password, String address, String gender, float height, float startingWeight) {
    Member member = Accounts.getLoggedInMember();
    member.setEmail(email);
    member.setName(name);
    member.setAddress(address);
    member.setGender(gender);
    member.setHeight(height);
    member.setStartingWeight(startingWeight);
    member.save();
    Logger.info("Editing Member " + member.name);
    redirect("/dashboard");
  }

  public static void edit() {
    render("editmember.html");
  }

}
