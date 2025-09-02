package com.dev.simpleMvp.demo.utils;

import java.time.LocalDate;
import java.time.Period;

public class UserUtils {
    public Integer calculaIdade(LocalDate dataNascUser){
        if (dataNascUser == null){
            throw new RuntimeException("Digite sua data ne nascimento");
        }
        LocalDate localDate = LocalDate.now();
        return Period.between(dataNascUser, localDate).getYears();
    }
}
