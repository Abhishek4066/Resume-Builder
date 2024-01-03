package com.resume.ResumeBuilder.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Experience {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String company;
    private String designation;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private boolean CurrentJob;
    private List<String> responsibilities = new ArrayList();
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public List<String> getResponsibilities() {
		return responsibilities;
	}
	public void setResponsibilities(List<String> responsibilities) {
		this.responsibilities = responsibilities;
	}
	public boolean isCurrentJob() {
		return CurrentJob;
	}
	public void setCurrentJob(boolean currentJob) {
		CurrentJob = currentJob;
	}
    
	public String getFormattedStartDate() {
        return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy -"));
    }

    public String getFormattedEndDate() {
        return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }
	@Override
	public String toString() {
		return "Experience [id=" + id + ", company=" + company + ", designation=" + designation + ", startDate="
				+ startDate + ", endDate=" + endDate + ", CurrentJob=" + CurrentJob + ", responsibilities="
				+ responsibilities + "]";
	}

}
