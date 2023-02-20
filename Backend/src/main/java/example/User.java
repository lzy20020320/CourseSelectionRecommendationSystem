package example;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String userName;
    public List<Course> courseList = new ArrayList<>();
    public User(){}
    public User(String userName){
        this.userName = userName;
    }
    public User set (String cName,int score){
        this.courseList.add(new Course(cName,score));
        return this;
    }
    public Course find(String cName){
        for(Course c:courseList){
            if(c.cName.equals(cName)){
                return c;
            }
        }
        return null;
    }
    public String toString(){
        return "User:name:"+userName+"courseList:"+courseList;
    }
}
