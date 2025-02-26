package com.validation;

public class Validator {

    public String ValidatorLength(String caracter) {
        if (caracter.length() < 8) return "must be higher the eigth caraters";
        return caracter;
    }

    public String ValidatorMayus(String minus) {
        if (minus.equals(minus.toLowerCase())) return minus.toUpperCase();
        return minus;
    }

    public String ValidatorSpace(String space) {
        String newText = "";
        for (int i = 0; i < space.length(); i++) {
            if (space.charAt(i) != ' ' && space.charAt(i) != '\n') {
                newText += space.charAt(i);
            }
        }
        return newText;
    }

}
