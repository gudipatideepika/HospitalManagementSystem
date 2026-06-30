

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class App {

    public static void main(String[] args) {

        // Configure Hibernate
        Configuration conf = new Configuration();
        conf.configure("hibernate.cfg.xml");
        conf.addAnnotatedClass(Patient.class);
        conf.addAnnotatedClass(Doctor.class);

        System.out.println("Configuration done");

        // Create SessionFactory
        SessionFactory factory = conf.buildSessionFactory();
        System.out.println("SessionFactory created");

        // Open Session
        Session session = factory.openSession();
        System.out.println("Session object created");

        // Begin Transaction
        Transaction transaction = session.beginTransaction();
        System.out.println("Transaction started");

        // Create Patient object
        Patient patient = new Patient();
        patient.setId(1);
        patient.setName("Deepika");
        patient.setAge(22);
        patient.setGender("Female");
        patient.setDisease("Fever");

        // Create Doctor object
        Doctor doctor = new Doctor();
        doctor.setId(101);
        doctor.setName("Rajesh");
        doctor.setSpecialization("Cardiologist");
        doctor.setExperience(10);

        // Save objects
        session.persist(patient);
        session.persist(doctor);

        // Commit Transaction
        transaction.commit();

        System.out.println("Data inserted successfully.");

        // Fetch all Patients
        Query<Patient> pQuery = session.createQuery("from Patient", Patient.class);
        List<Patient> patients = pQuery.list();

        System.out.println("\nPatients in Database:");
        for (Patient p : patients) {
            System.out.println(p);
        }

        // Fetch all Doctors
        Query<Doctor> dQuery = session.createQuery("from Doctor", Doctor.class);
        List<Doctor> doctors = dQuery.list();

        System.out.println("\nDoctors in Database:");
        for (Doctor d : doctors) {
            System.out.println(d);
        }

        // Close resources
        session.close();
        factory.close();
    }
}