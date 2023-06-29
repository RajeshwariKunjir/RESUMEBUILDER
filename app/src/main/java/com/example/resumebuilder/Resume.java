package com.example.resumebuilder;

import java.util.ArrayList;
import java.util.List;

public class Resume {

    private static Resume instance;

    private String name;
    private String nationality;
    private String address;
    private String phoneNumber;
    private List<Education> educationList;
    private List<Project> projectList;
    private List<Certification> certificationList;
    private List<String> hobbies;
    private List<String> skills;

    Resume(String john_doe) {
        educationList = new ArrayList<>();
        projectList = new ArrayList<>();
        certificationList = new ArrayList<>();
        hobbies = new ArrayList<>();
        skills = new ArrayList<>();
    }

    public static Resume getInstance() {
        if (instance == null) {
            instance = new Resume("John Doe");
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Education> getEducations() {
        return educationList;
    }

    public void addEducation(Education education) {
        educationList.add(education);
    }

    public List<Project> getProjects() {
        return projectList;
    }

    public void addProject(Project project) {
        projectList.add(project);
    }

    public List<Certification> getCertifications() {
        return certificationList;
    }

    public void addCertification(Certification certification) {
        certificationList.add(certification);
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void addHobby(String hobby) {
        hobbies.add(hobby);
    }

    public List<String> getSkills() {
        return skills;
    }

    public void addSkill(String skill) {
        skills.add(skill);
    }
}
