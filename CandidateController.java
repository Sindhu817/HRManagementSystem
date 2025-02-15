package com.hrmanagement.hrmanagementsystem.controllers;
import java.io.*;

import java.util.List;
import com.hrmanagement.hrmanagementsystem.entities.Candidate;
import com.hrmanagement.hrmanagementsystem.services.CandidateService;
import com.hrmanagement.hrmanagementsystem.servicesImpl.CandidateServiceImpl;
// Controller class to manage candidate-related operations
public class CandidateController 
{
    private BufferedReader br; // BufferedReader for reading user input
    private CandidateService candidateService; // Service layer for candidate operations
    // Constructor - Initializes BufferedReader and CandidateService
    public CandidateController() {
        br = new BufferedReader(new InputStreamReader(System.in));
        candidateService = new CandidateServiceImpl();
    }
    
    // Method to register a new candidate
    public void registerCandidate() throws IOException {
        System.out.println("----- Register New Candidate -----");
        // Read candidate details from user
        System.out.print("Candidate ID: ");
        int candidateId = Integer.parseInt(br.readLine());
        System.out.print("Candidate Name: ");
        String candidateName = br.readLine();
        System.out.print("Mobile Number: ");
        long mobileNumber = Long.parseLong(br.readLine());
        System.out.print("Email: ");
        String email = br.readLine();
        // Read resume as multi-line input until "END" is entered
        System.out.println("Enter Resume (type 'END' to finish):");
        StringBuilder resumeBuilder = new StringBuilder();
        String line;
        while (!(line = br.readLine()).equals("END")) {
            resumeBuilder.append(line).append("\n");
        }
        String resume = resumeBuilder.toString();
        System.out.print("Position Applied For: ");
        String positionAppliedFor = br.readLine();
        System.out.print("Job Posting ID: ");
        int jobPostingId = Integer.parseInt(br.readLine());
        // Create Candidate object
        Candidate candidate = new Candidate(candidateId, candidateName, mobileNumber, email, resume, positionAppliedFor, null);
        // Insert candidate using service layer
        int result = candidateService.insertCandidate(candidate, jobPostingId);
        System.out.println(result > 0 ? "Candidate registered successfully!" : "Failed to register candidate.");
    }
    // Method to update candidate details
    public void updateCandidateDetails() throws IOException {
        System.out.println("----- Update Candidate Details -----");
        System.out.print("Candidate ID: ");
        int candidateId = Integer.parseInt(br.readLine().trim()); // Trim input to remove spaces
        System.out.print("New Candidate Name: ");
        String candidateName = br.readLine().trim();
        System.out.print("New Mobile Number: ");
        long mobileNumber = Long.parseLong(br.readLine().trim());
        System.out.print("New Email: ");
        String email = br.readLine().trim();
        // Read updated resume
        System.out.println("Enter New Resume (type 'END' to finish):");
        StringBuilder resumeBuilder = new StringBuilder();
        String line;
        while (!(line = br.readLine().trim()).equalsIgnoreCase("END")) {
            resumeBuilder.append(line).append("\n");
        }
        String resume = resumeBuilder.toString().trim();
        System.out.print("New Position Applied For: ");
        String positionAppliedFor = br.readLine().trim();
        System.out.print("New Job Posting ID: ");
        int jobPostingId;
        try {
            jobPostingId = Integer.parseInt(br.readLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Job Posting ID. Please enter a valid number.");
            return;
        }
        // Update candidate details using service layer
        int result = candidateService.updateCandidateDetails(candidateId, candidateName, mobileNumber, email, resume, positionAppliedFor, jobPostingId);
        System.out.println(result > 0 ? "Candidate updated successfully!" : "Failed to update candidate.");
    }
    // Method to delete a candidate by ID
    public void deleteCandidate() throws IOException {
        System.out.println("----- Delete Candidate -----");
        System.out.print("Candidate ID: ");
        int candidateId = Integer.parseInt(br.readLine());
        // Call service to delete candidate
        int result = candidateService.deleteCandidate(candidateId);
        System.out.println(result > 0 ? "Candidate deleted successfully!" : "Failed to delete candidate.");
    }
    // Method to display all candidates
    public void displayAllCandidates() {
        System.out.println("----- All Candidates -----");
        // Fetch list of candidates
        List<Candidate> candidates = candidateService.getAllCandidates();
        // Display each candidate's details
        for (Candidate candidate : candidates) {
            System.out.println(candidate);
        }
    }
    // Method to display details of a specific candidate
    public void displayCandidateDetails() throws IOException {
        System.out.println("----- Candidate Details -----");
        System.out.print("Candidate ID: ");
        int candidateId = Integer.parseInt(br.readLine());
        // Fetch candidate details from service
        Candidate candidate = candidateService.getCandidateDetails(candidateId);
        // Print candidate details or show not found message
        System.out.println(candidate != null ? candidate : "Candidate not found.");
    }
}
