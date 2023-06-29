package com.example.resumebuilder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



import java.io.File;
import java.io.FileOutputStream;

public class EditResumeActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextNationality;
    private EditText editTextAddress;
    private EditText editTextPhoneNumber;
    private EditText editTextEducation;
    private EditText editTextProjects;
    private EditText editTextSkills;
    private EditText editTextHobbies;
    private EditText editTextCertificates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resume);

        editTextName = findViewById(R.id.editTextName);
        editTextNationality = findViewById(R.id.editTextNationality);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextEducation = findViewById(R.id.editTextEducation);
        editTextProjects = findViewById(R.id.editTextProjects);
        editTextSkills = findViewById(R.id.editTextSkills);
        editTextHobbies = findViewById(R.id.editTextHobbies);
        editTextCertificates = findViewById(R.id.editTextCertificates);

        // Get the resume object passed from MainActivity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("resume")) {
            Resume resume = intent.getParcelableExtra("resume");
            if (resume != null) {
                editTextName.setText(resume.getName());
                editTextNationality.setText(resume.getNationality());
                editTextAddress.setText(resume.getAddress());
                editTextPhoneNumber.setText(resume.getPhoneNumber());

                // Populate education
                StringBuilder educationBuilder = new StringBuilder();
                for (Education education : resume.getEducations()) {
                    educationBuilder.append(education.getDegree())
                            .append(" - ")
                            .append(education.getInstitution())
                            .append(" (")
                            .append(education.getYear())
                            .append(")\n");
                }
                editTextEducation.setText(educationBuilder.toString());

                // Populate projects
                StringBuilder projectsBuilder = new StringBuilder();
                for (Project project : resume.getProjects()) {
                    projectsBuilder.append(project.getName())
                            .append(": ")
                            .append(project.getDescription())
                            .append("\n");
                }
                editTextProjects.setText(projectsBuilder.toString());

                // Populate skills
                StringBuilder skillsBuilder = new StringBuilder();
                for (String skill : resume.getSkills()) {
                    skillsBuilder.append(skill).append("\n");
                }
                editTextSkills.setText(skillsBuilder.toString());

                // Populate hobbies
                StringBuilder hobbiesBuilder = new StringBuilder();
                for (String hobby : resume.getHobbies()) {
                    hobbiesBuilder.append(hobby).append("\n");
                }
                editTextHobbies.setText(hobbiesBuilder.toString());

                // Populate certificates
                StringBuilder certificatesBuilder = new StringBuilder();
                for (Certification certification : resume.getCertifications()) {
                    certificatesBuilder.append(certification.getName())
                            .append(" (")
                            .append(certification.getYear())
                            .append(")\n");
                }
                editTextCertificates.setText(certificatesBuilder.toString());
            }
        }

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the updated resume data
                String name = editTextName.getText().toString();
                String nationality = editTextNationality.getText().toString();
                String address = editTextAddress.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String education = editTextEducation.getText().toString();
                String projects = editTextProjects.getText().toString();
                String skills = editTextSkills.getText().toString();
                String hobbies = editTextHobbies.getText().toString();
                String certificates = editTextCertificates.getText().toString();

                Resume updatedResume = new Resume(name);
                updatedResume.setName(name);
                updatedResume.setNationality(nationality);
                updatedResume.setAddress(address);
                updatedResume.setPhoneNumber(phoneNumber);

                // Update education
                String[] educationLines = education.split("\n");
                for (String line : educationLines) {
                    String[] parts = line.split(" - ");
                    if (parts.length == 3) {
                        String degree = parts[0];
                        String institution = parts[1];
                        String year = parts[2].replaceAll("[()]", "");
                        Education educationObj = new Education();
                        educationObj.setDegree(degree);
                        educationObj.setInstitution(institution);
                        educationObj.setYear(year);
                        updatedResume.addEducation(educationObj);
                    }
                }

                // Update projects
                String[] projectLines = projects.split("\n");
                for (String line : projectLines) {
                    String[] parts = line.split(": ");
                    if (parts.length == 2) {
                        String projectName = parts[0];
                        String description = parts[1];
                        Project project = new Project();
                        project.setName(projectName);
                        project.setDescription(description);
                        updatedResume.addProject(project);
                    }
                }

                // Update skills
                String[] skillLines = skills.split("\n");
                for (String line : skillLines) {
                    updatedResume.addSkill(line);
                }

                // Update hobbies
                String[] hobbyLines = hobbies.split("\n");
                for (String line : hobbyLines) {
                    updatedResume.addHobby(line);
                }

                // Update certificates
                String[] certificateLines = certificates.split("\n");
                for (String line : certificateLines) {
                    String[] parts = line.split(" \\(");
                    if (parts.length == 2) {
                        String certificateName = parts[0];
                        String year = parts[1].replaceAll("[()]", "");
                        Certification certification = new Certification();
                        certification.setName(certificateName);
                        certification.setYear(year);
                        updatedResume.addCertification(certification);
                    }
                }
                Document document = new Document();

                // Generate a file name for the PDF
                String fileName = "resume.pdf";
                File file = new File(getExternalFilesDir(null), fileName);

                try {
                    // Create a PDF writer instance
                    PdfWriter.getInstance(document, new FileOutputStream(file));

                    // Open the document
                    document.open();

                    // Add the resume details to the document
                    document.add(new Paragraph("Name: " + name));
                    document.add(new Paragraph("Nationality: " + nationality));
                    document.add(new Paragraph("Address: " + address));
                    document.add(new Paragraph("Phone Number: " + phoneNumber));

                    // Add education
                    document.add(new Paragraph("Education:"));
                    educationLines = education.split("\n");
                    for (String line : educationLines) {
                        document.add(new Paragraph(line));
                    }

                    // Add projects
                    document.add(new Paragraph("Projects:"));
                    projectLines = projects.split("\n");
                    for (String line : projectLines) {
                        document.add(new Paragraph(line));
                    }

                    // Add skills
                    document.add(new Paragraph("Skills:"));
                    skillLines = skills.split("\n");
                    for (String line : skillLines) {
                        document.add(new Paragraph(line));
                    }

                    // Add hobbies
                    document.add(new Paragraph("Hobbies:"));
                    hobbyLines = hobbies.split("\n");
                    for (String line : hobbyLines) {
                        document.add(new Paragraph(line));
                    }

                    // Add certificates
                    document.add(new Paragraph("Certificates:"));
                    certificateLines = certificates.split("\n");
                    for (String line : certificateLines) {
                        document.add(new Paragraph(line));
                    }

                    // Close the document
                    document.close();

                    // Show a toast message indicating the PDF file path
                    Toast.makeText(EditResumeActivity.this, "Resume saved as PDF: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(EditResumeActivity.this, "Error saving PDF", Toast.LENGTH_SHORT).show();
                }

                // Pass the updated resume object back to MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("resume", (Parcelable) updatedResume);
                setResult(RESULT_OK, resultIntent);
                finish();

                // Pass the updated resume object back to MainActivity
                resultIntent = new Intent();
                resultIntent.putExtra("resume", (Parcelable) updatedResume);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
