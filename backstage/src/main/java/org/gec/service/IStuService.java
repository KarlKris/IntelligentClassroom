package org.gec.service;

import org.gec.model.Asset;
import org.gec.model.PageModel;
import org.gec.model.Student;

/**
 * @Author LZM
 * @Date 2018/12/6 14:44
 **/
public interface IStuService {

    void addStu(Student student);

    PageModel findStu(Integer page, Integer rows);

    Student getStu(String id);

    void updateStu(Student student);

    void deleteStu(String id);
}
