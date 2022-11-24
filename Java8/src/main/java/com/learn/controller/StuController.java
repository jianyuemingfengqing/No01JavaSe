package com.learn.controller;

import com.learn.entity.Student;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class StuController {
    public static void main(String[] args) {
        Student[] students = {
                new  Student("张三",18,60),
                new  Student("坤坤",20,98),
                new  Student("益丰",33,45)
        };

        Arrays.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        Arrays.sort(students, (o1, o2) -> o1.getAge() - o2.getAge());

        Arrays.sort(students, Comparator.comparingInt(Student::getAge));
    }
}
