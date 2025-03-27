/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class DoctorManagerTest {
    private DoctorManager manager;
    private static final String VALID_DATE = "01/01/1980";
    private static final String VALID_DATE_2 = "31/12/1990";
    private static final String INVALID_DATE_FORMAT = "01-01-1980";
    private static final String INVALID_DATE_VALUE = "30/02/1980";
    private static final String FUTURE_DATE = "01/01/2030";

    @Before
    public void setUp() {
        manager = new DoctorManager();
    }

    // Name validation tests
    @Test
    public void testAddDoctor_EmptyName() {
        boolean result = manager.addDoctor(
                "",
                VALID_DATE,
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890");
        assertFalse(result);
    }

    @Test
    public void testAddDoctor_NameExactly50Chars() {
        // Create a string of exactly 50 'a' characters
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 50; i++) {
            name.append('a');
        }
        boolean result = manager.addDoctor(
                name.toString(),
                VALID_DATE,
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890");
        assertTrue(result);
    }

    @Test
    public void testAddDoctor_NameExceeds50Chars() {
        // Create a string of 51 'a' characters
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 51; i++) {
            name.append('a');
        }
        boolean result = manager.addDoctor(
                name.toString(),
                VALID_DATE,
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890");
        assertFalse(result);
    }

    // Date validation tests
    @Test
    public void testAddDoctor_ValidDateEdgeCases() {
        assertTrue(manager.addDoctor(
                "John Doe",
                "01/01/1900",
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890"));
        assertTrue(manager.addDoctor(
                "Jane Doe",
                "31/12/1999",
                "Cardiology",
                2,
                "jane@email.com",
                "(123)-456-7891"));
    }

    @Test
    public void testAddDoctor_InvalidDateFormats() {
        assertFalse(manager.addDoctor(
                "John Doe",
                "01-01-1980",
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890"));
        assertFalse(manager.addDoctor(
                "John Doe",
                "1980/01/01",
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890"));
    }

    // Specialization validation tests
    @Test
    public void testAddDoctor_SpecializationExactly255Chars() {
        // Create a string of exactly 255 'a' characters
        StringBuilder specialization = new StringBuilder();
        for (int i = 0; i < 255; i++) {
            specialization.append('a');
        }
        boolean result = manager.addDoctor(
                "John Doe",
                VALID_DATE,
                specialization.toString(),
                2,
                "john@email.com",
                "(123)-456-7890");
        assertTrue(result);
    }

    @Test
    public void testAddDoctor_SpecializationExceeds255Chars() {
        // Create a string of 256 'a' characters
        StringBuilder specialization = new StringBuilder();
        for (int i = 0; i < 256; i++) {
            specialization.append('a');
        }
        boolean result = manager.addDoctor(
                "John Doe",
                VALID_DATE,
                specialization.toString(),
                2,
                "john@email.com",
                "(123)-456-7890");
        assertFalse(result);
    }

    // Availability validation tests
    @Test
    public void testAddDoctor_AvailabilityBoundaryTests() {
        // Test minimum valid value (0)
        assertTrue(manager.addDoctor(
                "John Doe",
                VALID_DATE,
                "Cardiology",
                0,
                "john@email.com",
                "(123)-456-7890"));

        // Test maximum valid value (3)
        assertTrue(manager.addDoctor(
                "Jane Doe",
                VALID_DATE,
                "Neurology",
                3,
                "jane@email.com",
                "(123)-456-7891"));

        // Test invalid values
        assertFalse(manager.addDoctor(
                "Jim Doe",
                VALID_DATE,
                "Pediatrics",
                -1,
                "jim@email.com",
                "(123)-456-7892"));

        assertFalse(manager.addDoctor(
                "Jack Doe",
                VALID_DATE,
                "Orthopedics",
                4,
                "jack@email.com",
                "(123)-456-7893"));
    }

    // Email validation tests
    @Test
    public void testAddDoctor_EmailValidationTests() {
        // Valid email formats
        assertTrue(manager.addDoctor(
                "John Doe",
                VALID_DATE,
                "Cardiology",
                2,
                "john.doe@email.com",
                "(123)-456-7890"));
        assertTrue(manager.addDoctor(
                "Jane Doe",
                VALID_DATE,
                "Neurology",
                2,
                "jane_doe123@sub.email.com",
                "(123)-456-7891"));

        // Invalid email formats
        assertFalse(manager.addDoctor(
                "Jim Doe",
                VALID_DATE,
                "Pediatrics",
                2,
                "invalid.email",
                "(123)-456-7892"));
        assertFalse(manager.addDoctor(
                "Jack Doe",
                VALID_DATE,
                "Orthopedics",
                2,
                "@invalid.com",
                "(123)-456-7893"));
    }

    // Phone validation tests
    @Test
    public void testAddDoctor_PhoneValidationTests() {
        // Valid phone formats
        assertTrue(manager.addDoctor(
                "John Doe",
                VALID_DATE,
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890"));

        // Invalid phone formats
        assertFalse(manager.addDoctor(
                "Jane Doe",
                VALID_DATE,
                "Neurology",
                2,
                "jane@email.com",
                "123-456-7890"));
        assertFalse(manager.addDoctor(
                "Jim Doe",
                VALID_DATE,
                "Pediatrics",
                2,
                "jim@email.com",
                "(123)4567890"));
    }

    // Search and sort tests
    @Test
    public void testSearchDoctorByName_CaseInsensitive() {
        manager.addDoctor("John Doe", VALID_DATE, "Cardiology", 2, "john@email.com", "(123)-456-7890");

        List<Doctor> result1 = manager.searchDoctorByName("JOHN");
        List<Doctor> result2 = manager.searchDoctorByName("john");
        List<Doctor> result3 = manager.searchDoctorByName("John");

        assertEquals(1, result1.size());
        assertEquals(1, result2.size());
        assertEquals(1, result3.size());
    }

    @Test
    public void testSearchDoctorByName_PartialMatch() {
        manager.addDoctor("John Doe", VALID_DATE, "Cardiology", 2, "john@email.com", "(123)-456-7890");
        manager.addDoctor("Johnny Smith", VALID_DATE, "Neurology", 2, "johnny@email.com", "(123)-456-7891");

        List<Doctor> result = manager.searchDoctorByName("John");
        assertEquals(2, result.size());
    }

    @Test
    public void testSortDoctorsByDOB_MultipleRecords() {
        manager.addDoctor("John", "01/01/1980", "Cardiology", 2, "john@email.com", "(123)-456-7890");
        manager.addDoctor("Jane", "01/01/1970", "Neurology", 2, "jane@email.com", "(123)-456-7891");
        manager.addDoctor("Jim", "01/01/1990", "Pediatrics", 2, "jim@email.com", "(123)-456-7892");

        manager.sortDoctorsByDOB();
        List<Doctor> doctors = manager.searchDoctorByName("");

        assertEquals("Jane", doctors.get(0).getName());
        assertEquals("John", doctors.get(1).getName());
        assertEquals("Jim", doctors.get(2).getName());
    }
    // ... (giữ nguyên các test case cũ)

    @Test
    public void testAddDoctor_NullNameField() {
        boolean result = manager.addDoctor(
                null,
                VALID_DATE,
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890");
        assertFalse(result);
    }

    @Test
    public void testAddDoctor_EmptySpecialization() {
        boolean result = manager.addDoctor(
                "John Doe",
                VALID_DATE,
                "",
                2,
                "john@email.com",
                "(123)-456-7890");
        // Theo logic hiện tại, empty specialization được chấp nhận vì chỉ check length
        // <= 255
        assertTrue(result);
    }

    @Test
    public void testEditDoctor_NonExistentId() {
        // Đầu tiên thêm một bác sĩ hợp lệ
        manager.addDoctor(
                "John Doe",
                VALID_DATE,
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890");

        // Thử sửa với ID không tồn tại
        boolean result = manager.editDoctor(
                999,
                "John Smith",
                VALID_DATE,
                "Neurology",
                2,
                "smith@email.com",
                "(123)-456-7891");
        assertFalse(result);
    }

    @Test
    public void testSearchDoctorByName_EmptyDatabase() {
        // Test tìm kiếm khi chưa có bác sĩ nào trong database
        List<Doctor> result = manager.searchDoctorByName("John");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSortDoctorsByDOB_EmptyAndSingleElement() {
        // Test sort với database rỗng
        manager.sortDoctorsByDOB();
        List<Doctor> emptyResult = manager.searchDoctorByName("");
        assertTrue(emptyResult.isEmpty());

        // Test sort với 1 phần tử
        manager.addDoctor(
                "John Doe",
                VALID_DATE,
                "Cardiology",
                2,
                "john@email.com",
                "(123)-456-7890");
        manager.sortDoctorsByDOB();
        List<Doctor> singleResult = manager.searchDoctorByName("");
        assertEquals(1, singleResult.size());
        assertEquals("John Doe", singleResult.get(0).getName());
    }
}