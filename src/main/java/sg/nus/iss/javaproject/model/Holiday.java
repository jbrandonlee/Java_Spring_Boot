package sg.nus.iss.javaproject.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Holiday {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	public Holiday() {}
	private LocalDate day;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDay() {
		return day;
	}
	public void setDay(LocalDate day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "Holiday [id=" + id + ", day=" + day + "]";
	}
	public Holiday(int id, LocalDate day) {
		super();
		this.id = id;
		this.day = day;
	}
}
