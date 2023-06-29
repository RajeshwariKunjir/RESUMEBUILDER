package com.example.resumebuilder;// Import required libraries
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResumePdfGenerator {

    public static void generatePdf(Resume resume) {
        // Create a new PDF document
        PdfDocument document = new PdfDocument();

        // Create a page with the desired dimensions
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        // Create a canvas for drawing on the page
        Canvas canvas = page.getCanvas();

        // Set up the paint object for styling the text
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(12);

        // Write the resume details on the canvas
        int startX = 50;
        int startY = 50;
        int lineHeight = 20;

        canvas.drawText("Name: " + resume.getName(), startX, startY, paint);
        startY += lineHeight;
        canvas.drawText("Nationality: " + resume.getNationality(), startX, startY, paint);
        startY += lineHeight;
        canvas.drawText("Address: " + resume.getAddress(), startX, startY, paint);
        startY += lineHeight;
        canvas.drawText("Phone Number: " + resume.getPhoneNumber(), startX, startY, paint);

        // Draw education details
        startY += 2 * lineHeight;
        canvas.drawText("Education:", startX, startY, paint);
        startY += lineHeight;

        for (Education education : resume.getEducations()) {
            canvas.drawText(education.getDegree() + " - " + education.getInstitution() + " (" + education.getYear() + ")", startX, startY, paint);
            startY += lineHeight;
        }

        // Draw project details
        startY += 2 * lineHeight;
        canvas.drawText("Projects:", startX, startY, paint);
        startY += lineHeight;

        for (Project project : resume.getProjects()) {
            canvas.drawText(project.getName() + ": " + project.getDescription(), startX, startY, paint);
            startY += lineHeight;
        }

        // Draw skill details
        startY += 2 * lineHeight;
        canvas.drawText("Skills:", startX, startY, paint);
        startY += lineHeight;

        for (String skill : resume.getSkills()) {
            canvas.drawText(skill, startX, startY, paint);
            startY += lineHeight;
        }

        // Draw hobby details
        startY += 2 * lineHeight;
        canvas.drawText("Hobbies:", startX, startY, paint);
        startY += lineHeight;

        for (String hobby : resume.getHobbies()) {
            canvas.drawText(hobby, startX, startY, paint);
            startY += lineHeight;
        }

        // Draw certificate details
        startY += 2 * lineHeight;
        canvas.drawText("Certificates:", startX, startY, paint);
        startY += lineHeight;

        for (Certification certification : resume.getCertifications()) {
            canvas.drawText(certification.getName() + " (" + certification.getYear() + ")", startX, startY, paint);
            startY += lineHeight;
        }

        // Finish the page and save the document
        document.finishPage(page);

        // Define the file path and name for the PDF file
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "resume.pdf";

        // Save the document to the specified file path
        try {
            File file = new File(filePath);
            FileOutputStream fos = new FileOutputStream(file);
            document.writeTo(fos);
            document.close();
            fos.close();
            Log.d("ResumePdfGenerator", "PDF generated and saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("ResumePdfGenerator", "Error generating PDF: " + e.getMessage());
        }
    }
}
