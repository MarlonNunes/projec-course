package com.projectcourse.service;

import com.projectcourse.model.InformationStudent;
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
        InformationStudent informationStudent = informationStudentRepository.getById(student.getIdStudent());

        informationStudent.setMissedClass(informationStudent.getMissedClass() + 1);
        informationStudentRepository.save(informationStudent);

        return studentRepository.findById(informationStudent.getIdInfoStudent()).get();
    }

    public Student removeMissing (Student student) {
        InformationStudent informationStudent = informationStudentRepository.getById(student.getIdStudent());

        if(informationStudent.getGrade() > 0) {
            informationStudent.setMissedClass(informationStudent.getMissedClass() - 1);
            informationStudentRepository.save(informationStudent);
        }

        return studentRepository.findById(informationStudent.getIdInfoStudent()).get();
    }

    public void save(Student sInformationStudent){

        InformationStudent informationStudent = new InformationStudent();
        informationStudent.setStudent(sInformationStudent);
        informationStudent.setGrade(0);
        informationStudent.setMissedClass(0);
        informationStudent.setCourse(sInformationStudent.getCourses()
                .stream().reduce((first, second) -> second).orElse(null));

        informationStudentRepository.save(informationStudent);
    }
}