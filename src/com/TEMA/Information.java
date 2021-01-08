package com.TEMA;

import java.util.ArrayList;

class Limbi {
    private String Limba;
    private int Beginner = 0;
    private int Advanced = 0;
    private int Experienced = 0;

    public String getLevel() {
        String Level = "";
        if (Beginner == 1) {
            Level = "Beginner";
        }
        if (Advanced == 1) {
            Level = "Advanced";
        }
        if (Experienced == 1) {
            Level = "Experienced";
        }
        return Level;
    }

    public String getLimba() {
        return Limba;
    }

    public void setAdvanced(int advanced) {
        Advanced = advanced;
    }

    public void setBeginner(int beginner) {
        Beginner = beginner;
    }

    public void setExperienced(int experienced) {
        Experienced = experienced;
    }

    public void setLimba(String limba) {
        Limba = limba;
    }

    @Override
    public String toString() {
        return '\n'
                + "Limba = " + Limba + '\n' + '\t' + '\t' + '\t' + '\t'
                + "Beginner = " + Beginner + '\n' + '\t' + '\t' + '\t' + '\t'
                + "Advanced = " + Advanced + '\n' + '\t' + '\t' + '\t' + '\t'
                + "Experienced = " + Experienced + '\n' + '\t' + '\t' + '\t' + '\t';
    }
}

public class Information {
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private String data_de_nastere;
    private String sex;
    private ArrayList<Limbi> languages;

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setData_de_nastere(String data_de_nastere) {
        this.data_de_nastere = data_de_nastere;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getData_de_nastere() {
        return data_de_nastere;
    }

    public void add(String limba, String level) {
        if (languages == null) {
            languages = new ArrayList<>();
        }
        Limbi limbi = new Limbi();
        limbi.setLimba(limba);
        if (level.compareTo("Beginner") == 0) {
            limbi.setBeginner(1);
        }
        if (level.compareTo("Advanced") == 0) {
            limbi.setAdvanced(1);
        }
        if (level.compareTo("Experienced") == 0) {
            limbi.setExperienced(1);
        }
        languages.add(limbi);
    }

    public void setLanguages(ArrayList<Limbi> languages) {
        this.languages = languages;
    }

    public String getEmail() {
        return email;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getSex() {
        return sex;
    }

    public String getTelefon() {
        return telefon;
    }

    public ArrayList<Limbi> getLanguages() {
        return languages;
    }

    @Override
    public String toString() {
        return "-Information-" + '\n' + '\t' + '\t' + '\t' + '\t'
                + "nume = " + getNume() + '\n' + '\t' + '\t' + '\t' + '\t'
                + "prenume = " + getPrenume() + '\n' + '\t' + '\t' + '\t' + '\t'
                + "email = " + getEmail() + '\n' + '\t' + '\t' + '\t' + '\t'
                + "telefon = " + getTelefon() + '\n' + '\t' + '\t' + '\t' + '\t'
                + "data_de_nastere = " + getData_de_nastere() + '\n' + '\t' + '\t' + '\t' + '\t'
                + "sex = " + getSex() + '\n' + '\t' + '\t' + '\t' + '\t'
                + "languages = " + getLanguages();
    }
}
