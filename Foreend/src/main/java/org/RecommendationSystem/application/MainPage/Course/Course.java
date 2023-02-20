package org.RecommendationSystem.application.MainPage.Course;

import javafx.scene.image.Image;

public class Course {

    String name, teacher,college;
    double score;

    public Course(double score,String name, String teacher, String college ) {
        this.name = name;
        this.teacher = teacher;
        this.college = college;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getCollege() {
        return college;
    }

    public double getScore() {
        return score;
    }
}
