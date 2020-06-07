package models;

import play.db.jpa.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Assessment extends Model
{
  public String dateAndTime;
  public double weight;
  public double chest;
  public double thigh;
  public double upperArm;
  public double waist;
  public double hips;
  public String comment;

  public static String datetime(){
    String datetime;
    Date date=new Date();
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    return datetime=formatter.format(date);
  }

  public Assessment(String dateAndTime, double weight, double chest, double thigh, double upperArm, double waist, double hips, String comment)
  {
    setDateAndTime(dateAndTime);
    setWeight(weight);
    setChest(chest);
    setThigh(thigh);
    setUpperArm(upperArm);
    setWaist(waist);
    setHips(hips);
    setComment(comment);
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public double getChest() {
    return chest;
  }

  public void setChest(double chest) {
    this.chest = chest;
  }

  public double getThigh() {
    return thigh;
  }

  public void setThigh(double thigh) {
    this.thigh = thigh;
  }

  public double getUpperArm() {
    return upperArm;
  }

  public void setUpperArm(double upperArm) {
    this.upperArm = upperArm;
  }

  public double getWaist() {
    return waist;
  }

  public void setWaist(double waist) {
    this.waist = waist;
  }

  public double getHips() {
    return hips;
  }

  public void setHips(double hips) {
    this.hips = hips;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String getDateAndTime() { return dateAndTime; }

  public void setDateAndTime (String DateAndTime) {
    this.dateAndTime = datetime();
}
}
