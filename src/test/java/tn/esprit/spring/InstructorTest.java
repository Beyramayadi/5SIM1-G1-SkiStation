/*package tn.esprit.spring;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.entities.Instructor;
import tn.esprit.spring.services.IInstructorServices;
import java.time.LocalDate;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InstructorTest {
    @Autowired
    IInstructorServices instructorService;

    @Test
    public void testAddInstructor() {
        List<Instructor> instructors = instructorService.retrieveAllInstructors();
        int expected = instructors.size();
        Instructor instructor = new Instructor();
        instructor.setFirstName("John");
        instructor.setLastName("Doe");
        instructor.setDateOfHire(LocalDate.now());
        Instructor savedInstructor = instructorService.addInstructor(instructor);

        assertEquals(expected + 1, instructorService.retrieveAllInstructors().size());
        assertNotNull(savedInstructor.getNumInstructor());
        instructorService.retrieveInstructor(savedInstructor.getNumInstructor());
    }

    @Test
    public void testAddInstructorOptimized() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("Jane");
        instructor.setLastName("Smith");
        instructor.setDateOfHire(LocalDate.now());
        Instructor savedInstructor = instructorService.addInstructor(instructor);
        assertNotNull(savedInstructor.getNumInstructor());
        assertEquals("Jane", savedInstructor.getFirstName());
        assertEquals("Smith", savedInstructor.getLastName());
        instructorService.retrieveInstructor(savedInstructor.getNumInstructor());
    }

    @Test
    public void testDeleteInstructor() {
        Instructor instructor = new Instructor();
        instructor.setFirstName("Alice");
        instructor.setLastName("Johnson");
        instructor.setDateOfHire(LocalDate.now());
        Instructor savedInstructor = instructorService.addInstructor(instructor);
        instructorService.retrieveInstructor(savedInstructor.getNumInstructor());
        assertNull(instructorService.retrieveInstructor(savedInstructor.getNumInstructor()));
    }
}*/