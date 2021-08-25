package com.projectcourse.service;

import com.projectcourse.model.InformationStudentByCourse;
import com.projectcourse.model.Student;
import com.projectcourse.repository.InformationStudentRepository;
import com.projectcourse.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InformationStudentService {

    private final InformationStudentRepository informationStudentRepository;
    private final StudentRepository studentRepository;

    public Student addMissing (Student student){
        InformationStudentByCourse informationStudentByCourse = informationStudentRepository.getById(student.getIdStudent());

        informationStudentByCourse.setMissedClass(informationStudentByCourse.getMissedClass() + 1);
        informationStudentRepository.save(informationStudentByCourse);

        return studentRepository.findById(informationStudentByCourse.getIdInfoStudent()).get();
    }

    public Student removeMissing (Student student) {
        InformationStudentByCourse informationStudentByCourse = informationStudentRepository.getById(student.getIdStudent());

        if(informationStudentByCourse.getGrade() > 0) {
            informationStudentByCourse.setMissedClass(informationStudentByCourse.getMissedClass() - 1);
            informationStudentRepository.save(informationStudentByCourse);
        }

        return studentRepository.findById(informationStudentByCourse.getIdInfoStudent()).get();
    }

    public void save(Student sInformationStudent){

        InformationStudentByCourse informationStudentByCourse = new InformationStudentByCourse();
        informationStudentByCourse.setStudent(sInformationStudent);
        informationStudentByCourse.setGrade(0);
        informationStudentByCourse.setMissedClass(0);
        informationStudentByCourse.setCourse(sInformationStudent.getCourses()
                .stream().reduce((first, second) -> second).orElse(null));

        informationStudentRepository.save(informationStudentByCourse);
    }
}