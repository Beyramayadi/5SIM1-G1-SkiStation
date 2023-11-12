package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static tn.esprit.spring.entities.Support.SKI;
import static tn.esprit.spring.entities.Support.SNOWBOARD;
import static tn.esprit.spring.entities.TypeCourse.COLLECTIVE_CHILDREN;
@ExtendWith(MockitoExtension.class)

public class CoursServicesImplTest {
    @InjectMocks
    private CourseServicesImpl courseServices;
    @Mock
    private ICourseRepository iCourseRepository;

    Course prototype = new Course(1L, 2, COLLECTIVE_CHILDREN, SKI, (float) 1, 2, null);
    Course prototype2 = new Course(2L, 2, COLLECTIVE_CHILDREN, SNOWBOARD, (float) 1, 2, null);

    @Test
    void retrieveAllCourses() {
        when(iCourseRepository.findAll()).thenReturn(Stream.of(prototype, prototype2).collect(Collectors.toList()));
        assertEquals(2, courseServices.retrieveAllCourses().size());
    }

    @Test
    void addCourse() {
        when(iCourseRepository.save(Mockito.any(Course.class))).thenReturn(prototype);
        Course result = courseServices.addCourse(prototype);
        assertEquals(prototype, result);
    }

    @Test
    void updateCourse() {
        Course existingCourse = new Course(3L, 2, COLLECTIVE_CHILDREN, SKI, (float) 1, 2, null);
        Course updatedCourse = new Course(3L, 2, COLLECTIVE_CHILDREN, SNOWBOARD, (float) 1, 2, null);
        when(iCourseRepository.save(existingCourse)).thenReturn(existingCourse);
        Course course = courseServices.addCourse(existingCourse);
        when(iCourseRepository.save(updatedCourse)).thenReturn(updatedCourse);
        Course result = courseServices.updateCourse(updatedCourse);
        assertEquals(updatedCourse, result);
    }

    @Test
    void retrieveCourse() {
        when(iCourseRepository.findById(1L)).thenReturn(Optional.of(prototype));
        Optional<Course> retrievedCourse = Optional.ofNullable(courseServices.retrieveCourse(1L));

        assertTrue(retrievedCourse.isPresent());
        assertEquals(prototype, retrievedCourse.get());
    }
}
