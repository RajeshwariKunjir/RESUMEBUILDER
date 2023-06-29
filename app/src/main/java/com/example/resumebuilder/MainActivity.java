package com.example.resumebuilder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Resume resume;
    private LinearLayout layoutEducation;
    private LinearLayout layoutProjects;
    private LinearLayout layoutCertifications;
    private LinearLayout layoutHobbies;
    private LinearLayout layoutSkills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize resume data
//        resume = new Resume("John Doe");
//        resume.setNationality("American");
//        resume.setAddress("123 Main St, Anytown");
//        resume.setPhoneNumber("+1 123-456-7890");
//
//        Education education1 = new Education();
//        education1.setDegree("Bachelor's Degree");
//        education1.setInstitution("University A");
//        education1.setYear("2015-2019");
//        resume.addEducation(education1);
//
//        Education education2 = new Education();
//        education2.setDegree("Master's Degree");
//        education2.setInstitution("University B");
//        education2.setYear("2019-2021");
//        resume.addEducation(education2);
//
//        Project project1 = new Project();
//        project1.setName("Project A");
//        project1.setDescription("Description of Project A");
//        resume.addProject(project1);
//
//        Project project2 = new Project();
//        project2.setName("Project B");
//        project2.setDescription("Description of Project B");
//        resume.addProject(project2);
//
//        Certification certification1 = new Certification();
//        certification1.setName("Certification A");
//        certification1.setYear("2018");
//        resume.addCertification(certification1);
//
//        Certification certification2 = new Certification();
//        certification2.setName("Certification B");
//        certification2.setYear("2020");
//        resume.addCertification(certification2);
//
//        resume.addHobby("Reading");
//        resume.addHobby("Traveling");
//        resume.addHobby("Photography");
//
//        resume.addSkill("Java");
//        resume.addSkill("Android Development");
//        resume.addSkill("Project Management");

        // Initialize views
        layoutEducation = findViewById(R.id.layoutEducation);
        layoutProjects = findViewById(R.id.layoutProjects);
        layoutCertifications = findViewById(R.id.layoutCertifications);
        layoutHobbies = findViewById(R.id.layoutHobbies);
        layoutSkills = findViewById(R.id.layoutSkills);

        // Display resume data
        displayResume();

        // Set up button click listener
        Button btnEditResume = findViewById(R.id.btnEditResume);
        btnEditResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit resume button click here
                // Add your logic to navigate to the resume editing screen
                Intent intent = new Intent(MainActivity.this, EditResumeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayResume() {
        try {
            // Display basic resume information
            TextView textViewName = findViewById(R.id.textViewName);
            if (textViewName != null) {
                textViewName.setText(resume.getName());
            } else {
                Log.e("MainActivity", "TextView textViewName is null");
            }

            TextView textViewNationality = findViewById(R.id.textViewNationality);
            if (textViewNationality != null) {
                textViewNationality.setText(resume.getNationality());
            } else {
                Log.e("MainActivity", "TextView textViewNationality is null");
            }

            TextView textViewAddress = findViewById(R.id.textViewAddress);
            if (textViewAddress != null) {
                textViewAddress.setText(resume.getAddress());
            } else {
                Log.e("MainActivity", "TextView textViewAddress is null");
            }

            TextView textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
            if (textViewPhoneNumber != null) {
                textViewPhoneNumber.setText(resume.getPhoneNumber());
            } else {
                Log.e("MainActivity", "TextView textViewPhoneNumber is null");
            }

        // Display basic resume information
//        TextView textViewName = findViewById(R.id.textViewName);
//        textViewName.setText(resume.getName());
//
//        TextView textViewNationality = findViewById(R.id.textViewNationality);
//        textViewNationality.setText(resume.getNationality());
//
//        TextView textViewAddress = findViewById(R.id.textViewAddress);
//        textViewAddress.setText(resume.getAddress());
//
//        TextView textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
//        textViewPhoneNumber.setText(resume.getPhoneNumber());

        // Display education
        List<Education> educationList = resume.getEducations();
        for (Education education : educationList) {
            View educationView = getLayoutInflater().inflate(R.layout.education_item, null);

            TextView textViewDegree = educationView.findViewById(R.id.textViewDegree);
            textViewDegree.setText(education.getDegree());

            TextView textViewInstitution = educationView.findViewById(R.id.textViewInstitution);
            textViewInstitution.setText(education.getInstitution());

            TextView textViewYear = educationView.findViewById(R.id.textViewYear);
            textViewYear.setText(education.getYear());

            layoutEducation.addView(educationView);
        }

        // Display projects
        List<Project> projectList = resume.getProjects();
        for (Project project : projectList) {
            View projectView = getLayoutInflater().inflate(R.layout.project_item, null);

            TextView textViewProjectName = projectView.findViewById(R.id.textViewProjectName);
            textViewProjectName.setText(project.getName());

            TextView textViewDescription = projectView.findViewById(R.id.textViewDescription);
            textViewDescription.setText(project.getDescription());

            layoutProjects.addView(projectView);
        }

        // Display certifications
        List<Certification> certificationList = resume.getCertifications();
        for (Certification certification : certificationList) {
            View certificationView = getLayoutInflater().inflate(R.layout.certification_item, null);

            TextView textViewCertificationName = certificationView.findViewById(R.id.textViewCertificationName);
            textViewCertificationName.setText(certification.getName());

            TextView textViewCertificationYear = certificationView.findViewById(R.id.textViewCertificationYear);
            textViewCertificationYear.setText(certification.getYear());

            layoutCertifications.addView(certificationView);
        }

        // Display hobbies
        List<String> hobbyList = resume.getHobbies();
        for (String hobby : hobbyList) {
            View hobbyView = getLayoutInflater().inflate(R.layout.hobby_item, null);

            TextView textViewHobby = hobbyView.findViewById(R.id.textViewHobby);
            textViewHobby.setText(hobby);

            layoutHobbies.addView(hobbyView);
        }

        // Display skills
        List<String> skillList = resume.getSkills();
        for (String skill : skillList) {
            View skillView = getLayoutInflater().inflate(R.layout.skill_item, null);

            TextView textViewSkill = skillView.findViewById(R.id.textViewSkill);
            textViewSkill.setText(skill);

            layoutSkills.addView(skillView);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
