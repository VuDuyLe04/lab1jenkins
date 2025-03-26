/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Lab1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DoctorManager manager = new DoctorManager();
        while (true) {
            System.out.println("\nDoctor Management System");
            System.out.println("1. Add Doctor");
            System.out.println("2. Edit Doctor");
            System.out.println("3. Delete Doctor");
            System.out.println("4. Search Doctor by ID");
            System.out.println("5. Search Doctor by Name");
            System.out.println("6. Sort Doctors by Date of Birth");
            System.out.println("7. Display Doctors");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter DOB (dd/MM/yyyy): ");
                    String dob = scanner.nextLine();
                    System.out.print("Enter specialization: ");
                    String spec = scanner.nextLine();
                    System.out.print("Enter availability (0-3): ");
                    int avail = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter mobile ((xxx)-xxx-xxxx): ");
                    String mobile = scanner.nextLine();
                    manager.addDoctor(name, dob, spec, avail, email, mobile);
                    break;

                case 2:
                    System.out.print("Enter Doctor ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter DOB (dd/MM/yyyy): ");
                    dob = scanner.nextLine();
                    System.out.print("Enter specialization: ");
                    spec = scanner.nextLine();
                    System.out.print("Enter availability (0-3): ");
                    avail = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter mobile ((xxx)-xxx-xxxx): ");
                    mobile = scanner.nextLine();
                    manager.editDoctor(id, name, dob, spec, avail, email, mobile);
                    break;

                case 3:
                    System.out.print("Enter Doctor ID: ");
                    id = scanner.nextInt();
                    manager.deleteDoctor(id);
                    break;

                case 4:
                    System.out.print("Enter Doctor ID: ");
                    id = scanner.nextInt();
                    Doctor doc = manager.searchDoctorById(id);
                    if (doc != null) System.out.println(doc);
                    break;

                case 5:
                    System.out.print("Enter Doctor name: ");
                    name = scanner.nextLine();
                    manager.searchDoctorByName(name).forEach(System.out::println);
                    break;

                case 6:
                    manager.sortDoctorsByDOB();
                    manager.displayDoctors();
                    System.out.println("Empty list");
                    break;

                case 7:
                    manager.displayDoctors();
                    break;

                case 8:
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option.");
            }

        }
    }
}