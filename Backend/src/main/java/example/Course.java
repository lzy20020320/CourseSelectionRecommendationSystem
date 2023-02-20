package example;

public class Course implements Comparable<Course>{
    public String cName;
    public int appraise;
    public Course(String cName,int score){
        this.cName=cName;
        this.appraise=score;
    }
    public String ToString(){
        return "Course:Name="+cName+",Score="+appraise;
    }
    public int compareTo(Course o){
        return appraise>o.appraise? -1:1;
    }
}
