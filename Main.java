import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital("My Hospital", "City XYZ");
        Scanner scanner = new Scanner(System.in);

        // Sample Doctors and Patients
        Doctor doctor1 = new Doctor("Dr. Smith", "Cardiologist", 10);
        Doctor doctor2 = new Doctor("Dr. Johnson", "Dermatologist", 8);
        hospital.addDoctor(doctor1);
        hospital.addDoctor(doctor2);

        Patient patient1 = new Patient("John Doe", 30, "Male");
        Patient patient2 = new Patient("Jane Smith", 25, "Female");
        hospital.addPatient(patient1);
        hospital.addPatient(patient2);

        while (true) {
            System.out.println("1. Book an appointment");
            System.out.println("2. View all doctors");
            System.out.println("3. View all patients");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bookAppointment(hospital, scanner);
                    break;
                case 2:
                    displayDoctors(hospital);
                    break;
                case 3:
                    displayPatients(hospital);
                    break;
                case 4:
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void bookAppointment(Hospital hospital, Scanner scanner) {
        displayDoctors(hospital);
        System.out.print("Enter the doctor's name for the appointment: ");
        String doctorName = scanner.nextLine();
        Doctor selectedDoctor = hospital.findDoctorByName(doctorName);
        if (selectedDoctor == null) {
            System.out.println("Doctor not found.");
            return;
        }

        displayPatients(hospital);
        System.out.print("Enter the patient's name for the appointment: ");
        String patientName = scanner.nextLine();
        Patient selectedPatient = hospital.findPatientByName(patientName);
        if (selectedPatient == null) {
            System.out.println("Patient not found.");
            return;
        }

        System.out.print("Enter the appointment date and time (yyyy-MM-dd HH:mm): ");
        String dateTimeString = scanner.nextLine();
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateTimeString);
        } catch (Exception e) {
            System.out.println("Invalid date and time format. Appointment booking failed.");
            return;
        }

        Appointment appointment = new Appointment(selectedDoctor, selectedPatient, dateTime);
        hospital.addAppointment(appointment);
        System.out.println("Appointment booked successfully!");
    }

    private static void displayDoctors(Hospital hospital) {
        System.out.println("List of Doctors:");
        for (Doctor doctor : hospital.getDoctors()) {
            System.out.println(doctor.getName() + " (" + doctor.getSpecialization() + ")");
        }
    }

    private static void displayPatients(Hospital hospital) {
        System.out.println("List of Patients:");
        for (Patient patient : hospital.getPatients()) {
            System.out.println(patient.getName() + " (" + patient.getAge() + " years old)");
        }
    }
}

class Patient {
    private String name;
    private int age;
    private String gender;

    public Patient(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Other getters and setters
}


class Doctor {
    private String name;
    private String specialization;
    private int experience;

    public Doctor(String name, String specialization, int experience) {
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    // Other getters and setters
}

class Hospital {
    private String name;
    private String location;
    private List<Doctor> doctors;
    private List<Patient> patients;
    private List<Appointment> appointments;

    public Hospital(String name, String location) {
        this.name = name;
        this.location = location;
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public Doctor findDoctorByName(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(name)) {
                return doctor;
            }
        }
        return null;
    }

    public Patient findPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                return patient;
            }
        }
        return null;
    }
}

class Appointment {
    private Doctor doctor;
    private Patient patient;
    private LocalDateTime dateTime;

    public Appointment(Doctor doctor, Patient patient, LocalDateTime dateTime) {
        this.doctor = doctor;
        this.patient = patient;
        this.dateTime = dateTime;
    }

    // Getters and setters
}
