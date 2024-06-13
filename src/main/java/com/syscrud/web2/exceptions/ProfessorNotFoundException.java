package com.syscrud.web2.exceptions;

public class ProfessorNotFoundException extends RuntimeException{
    public ProfessorNotFoundException(Long id){
        super("Teacher not found by id: " + id);
    }
}
