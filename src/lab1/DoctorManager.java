/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DoctorManager {
    private List<Doctor> doctors = new ArrayList<>();
    private int countId = 1;
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String PHONE_REGEX = "^\\(\\d{3}\\)-\\d{3}-\\d{4}$";
    private static final String DATE_REGEX = "^\\d{2}/\\d{2}/\\d{4}$";

    public boolean addDoctor(String name, String dob, String specialization, int availability, String email, String mobile) {
        try {
            // Kiểm tra định dạng ngày tháng trước khi parse
            if (!dob.matches(DATE_REGEX)) {
                return false;
            }
            Date dateOfBirth = new SimpleDateFormat(DATE_FORMAT).parse(dob);
            if (validateDoctor(name, dateOfBirth, specialization, availability, email, mobile)) {
                doctors.add(new Doctor(countId++, name, dateOfBirth, specialization, availability, email, mobile));
                return true;
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
        return false;
    }

    public boolean editDoctor(int id, String name, String dob, String specialization, int availability, String email, String mobile) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                try {
                    if (!dob.matches(DATE_REGEX)) {
                        return false;
                    }
                    Date dateOfBirth = new SimpleDateFormat(DATE_FORMAT).parse(dob);
                    if (validateDoctor(name, dateOfBirth, specialization, availability, email, mobile)) {
                        doctor.setName(name);
                        doctor.setDateOfBirth(dateOfBirth);
                        doctor.setSpecialization(specialization);
                        doctor.setAvailability(availability);
                        doctor.setEmail(email);
                        doctor.setMobile(mobile);
                        return true;
                    }
                } catch (ParseException e) {
                    System.out.println("Invalid date format.");
                }
            }
        }
        return false;
    }

    public boolean deleteDoctor(int id) {
        return doctors.removeIf(doctor -> doctor.getId() == id);
    }

    public Doctor searchDoctorById(int id) {
        return doctors.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    public List<Doctor> searchDoctorByName(String name) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(doctor);
            }
        }
        return result;
    }

    public void sortDoctorsByDOB() {
        doctors.sort(Comparator.comparing(Doctor::getDateOfBirth));
    }

    public void displayDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        doctors.forEach(System.out::println);
    }

    private boolean validateDoctor(String name, Date dob, String specialization, int availability, String email, String mobile) {
        // Thêm kiểm tra tên rỗng
        return name != null && !name.trim().isEmpty() && 
               name.length() <= 50 && 
               specialization.length() <= 255 && 
               (availability >= 0 && availability <= 3) &&
               email.matches(EMAIL_REGEX) && 
               mobile.matches(PHONE_REGEX);
    }
}
