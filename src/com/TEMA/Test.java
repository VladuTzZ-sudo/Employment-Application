package com.TEMA;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        Application aplication = Application.getInstance();
        int nrUsers = 0, nrEmployees = 0, nrRecruiters = 0;

        JSONParser parser = new JSONParser();
        try {
            File f = new File("src/companies.json");
            String absolute = f.getAbsolutePath();
            FileReader reader = new FileReader(absolute);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray companiesList = (JSONArray) jsonObject.get("companies");
            for (Object o : companiesList) {
                JSONObject company = (JSONObject) o;
                String name = (String) company.get("name");

                JSONArray departmentsList = (JSONArray) company.get("departments");
                ArrayList<Departament> departaments = new ArrayList<>();
                DepartmentFactory departmentFactory = new DepartmentFactory();

                for (Object dep : departmentsList) {
                    JSONObject department = (JSONObject) dep;

                    String type = (String) department.get("type");
                    Departments departmentType;
                    switch (type){
                        case "IT":
                            departmentType = Departments.IT;
                            departaments.add(departmentFactory.getDepartment(departmentType));
                            break;
                        case "Management":
                            departmentType = Departments.Management;
                            departaments.add(departmentFactory.getDepartment(departmentType));
                            break;
                        case "Marketing":
                            departmentType = Departments.Marketing;
                            departaments.add(departmentFactory.getDepartment(departmentType));
                            break;
                        case "Finance":
                            departmentType = Departments.Finance;
                            departaments.add(departmentFactory.getDepartment(departmentType));
                            break;
                    }
                }

                Departament ITDepartment = null;
                for (Departament departament : departaments) {
                    if (departament.getType().compareTo("IT") == 0) {
                        ITDepartment = departament;
                    }
                }

                aplication.add(new Company(name, departaments));

                JSONArray jobsList = (JSONArray) company.get("jobs");
                for (Object job1 : jobsList) {
                    JSONObject job = (JSONObject) job1;

                    String nameJob = (String) job.get("name");
                    Long nrPosition = (Long) job.get("noPosition");
                    int noPosition = Math.toIntExact(nrPosition);

                    Long salariu = (Long) job.get("salary");
                    double salary = salariu;

                    Long graduationMin = (Long) job.get("graduation-min");
                    Long graduationMax = (Long) job.get("graduation-max");
                    double gradMax, gradMin;
                    if (graduationMax != null) {
                        gradMax = graduationMax;
                    } else {
                        gradMax = 3000;
                    }
                    if (graduationMin != null) {
                        gradMin = graduationMin;
                    } else {
                        gradMin = -1;
                    }
                    Long experienceMin = (Long) job.get("experience-min");
                    Long experienceMax = (Long) job.get("experience-max");
                    double expMax, expMin;
                    if (experienceMax != null) {
                        expMax = experienceMax;
                    } else {
                        expMax = 3000;
                    }
                    if (experienceMin != null) {
                        expMin = experienceMin;
                    } else {
                        expMin = -1;
                    }

                    double avMin, avMax;
                    try {
                        Double averageMin = (Double) job.get("average-min");
                        Double averageMax = (Double) job.get("average-max");
                        if (averageMin != null) {
                            avMin = averageMin;
                        } else {
                            avMin = -1;
                        }
                        if (averageMax != null) {
                            avMax = averageMax;
                        } else {
                            avMax = 3000;
                        }
                    } catch (Exception e) {
                        Long averageMin = (Long) job.get("average-min");
                        if (averageMin != null) {
                            avMin = averageMin;
                        } else {
                            avMin = -1;
                        }
                        Long averageMax = (Long) job.get("average-max");
                        if (averageMax != null) {
                            avMax = averageMax;
                        } else {
                            avMax = 3000;
                        }
                    }
                    Constraint anAbsolvire = new Constraint(gradMin, gradMax);
                    Constraint anExperienta = new Constraint(expMin, expMax);
                    Constraint medie = new Constraint(avMin, avMax);
                    ITDepartment.add(new Job(nameJob, name, true, anAbsolvire, anExperienta, medie, salary, noPosition));
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        try {
            File f = new File("src/consumers.json");
            String absolute = f.getAbsolutePath();
            FileReader reader = new FileReader(absolute);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray employeeList = (JSONArray) jsonObject.get("employees");

            int contor = 0;
            for (Object o : employeeList) {
                JSONObject employee = (JSONObject) o;
                contor++;

                Resume.ResumeBuilder resumeBuilder = new Resume.ResumeBuilder();
                Information information = new Information();

                String name = (String) employee.get("name");
                String[] result = name.split(" ");
                information.setNume(result[0]);
                information.setPrenume(result[1]);

                String email = (String) employee.get("email");
                information.setEmail(email);

                String phone = (String) employee.get("phone");
                information.setTelefon(phone);

                String date_of_birth = (String) employee.get("date_of_birth");
                information.setData_de_nastere(date_of_birth);

                String sex = (String) employee.get("genre");
                information.setSex(sex);

                JSONArray languages = (JSONArray) employee.get("languages");
                JSONArray languages_level = (JSONArray) employee.get("languages_level");

                Iterator<String> iterator = languages_level.iterator();
                for (Object lang : languages) {
                    String limba = (String) lang;
                    String limba_level = iterator.next();

                    information.add(limba, limba_level);
                }

                resumeBuilder.information(information);
                double salary;
                try {
                    double grade = (Double) employee.get("salary");
                    salary = grade;
                } catch (Exception e) {
                    Long grade = (Long) employee.get("salary");
                    salary = (double) grade;
                }

                JSONArray educatie = (JSONArray) employee.get("education");
                for (Object edu : educatie) {
                    JSONObject education = (JSONObject) edu;

                    String level = (String) education.get("level");

                    String nameEducation = (String) education.get("name");

                    String start_date = (String) education.get("start_date");
                    Calendar calendarStart = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendarStart.setTime(sdf.parse(start_date));
                    calendarStart.add(Calendar.DATE, 1);

                    String end_date = (String) education.get("end_date");
                    Calendar calendarEnd;
                    if (end_date != null) {
                        calendarEnd = Calendar.getInstance();
                        calendarEnd.setTime(sdf.parse(end_date));
                        calendarEnd.add(Calendar.DATE, 1);
                    } else {
                        calendarEnd = null;
                    }
                    double grade1;
                    try {
                        double grade = (Double) education.get("grade");
                        grade1 = grade;
                    } catch (Exception e) {
                        Long grade = (Long) education.get("grade");
                        grade1 = (double) grade;
                    }
                    resumeBuilder.addEducation(new Education(calendarStart, calendarEnd, nameEducation, level, grade1));
                }

                JSONArray experienta = (JSONArray) employee.get("experience");
                for (Object exp : experienta) {
                    JSONObject experience = (JSONObject) exp;

                    String company = (String) experience.get("company");

                    String position = (String) experience.get("position");

                    String department = (String) experience.get("department");

                    String start_exp_date = (String) experience.get("start_date");
                    Calendar calendarStart = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendarStart.setTime(sdf.parse(start_exp_date));
                    calendarStart.add(Calendar.DATE, 1);

                    Calendar calendarEnd;
                    String end_exp_date = (String) experience.get("end_date");
                    if (end_exp_date != null) {
                        calendarEnd = Calendar.getInstance();
                        calendarEnd.setTime(sdf.parse(end_exp_date));
                        calendarEnd.add(Calendar.DATE, 1);
                    } else {
                        calendarEnd = null;
                    }

                    resumeBuilder.addExperience(new Experience(calendarStart, calendarEnd, position, company, department));
                }
                Resume resume = resumeBuilder.build();

                Company employeeCompany = aplication.getCompany(resume.experiences.first().company);
                //System.out.println(employeeCompany.name + resume.information.getNume());
                for (Departament departament1 : employeeCompany.departaments) {
                    if (departament1.getType().compareTo(resume.experiences.first().department) == 0) {
                        //System.out.println(resume.information.getNume());
                        //if (departament1.getType().compareTo("IT") != 0) {
                        //System.out.println(resume.information.getNume() + " " + resume.experiences.first().company);
                        departament1.add(new Employee(resume, resume.experiences.first().company, salary, contor));
                        //}
                        // else{
                        //     departament1.add(new Recruiter(resume, resume.experiences.first().company, salary));
                        // }
                    }
                }
            }
            nrEmployees = contor;

            contor = 0;
            JSONArray recruitersList = (JSONArray) jsonObject.get("recruiters");
            for (Object o : recruitersList) {
                JSONObject recruiter = (JSONObject) o;
                contor++;
                Resume.ResumeBuilder resumeBuilder = new Resume.ResumeBuilder();
                Information information = new Information();

                String name = (String) recruiter.get("name");
                String[] result = name.split(" ");
                information.setNume(result[0]);
                information.setPrenume(result[1]);

                String email = (String) recruiter.get("email");
                information.setEmail(email);

                String phone = (String) recruiter.get("phone");
                information.setTelefon(phone);

                String date_of_birth = (String) recruiter.get("date_of_birth");
                information.setData_de_nastere(date_of_birth);

                String sex = (String) recruiter.get("genre");
                information.setSex(sex);

                JSONArray languages = (JSONArray) recruiter.get("languages");
                JSONArray languages_level = (JSONArray) recruiter.get("languages_level");

                Iterator<String> iterator = languages_level.iterator();
                for (Object lang : languages) {
                    String limba = (String) lang;
                    String limba_level = iterator.next();

                    information.add(limba, limba_level);
                }

                resumeBuilder.information(information);
                double salary;
                try {
                    double grade = (Double) recruiter.get("salary");
                    salary = grade;
                } catch (Exception e) {
                    Long grade = (Long) recruiter.get("salary");
                    salary = (double) grade;
                }

                JSONArray educatie = (JSONArray) recruiter.get("education");
                for (Object edu : educatie) {
                    JSONObject education = (JSONObject) edu;

                    String level = (String) education.get("level");

                    String nameEducation = (String) education.get("name");

                    String start_date = (String) education.get("start_date");
                    Calendar calendarStart = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendarStart.setTime(sdf.parse(start_date));
                    calendarStart.add(Calendar.DATE, 1);

                    String end_date = (String) education.get("end_date");
                    Calendar calendarEnd;
                    if (end_date != null) {
                        calendarEnd = Calendar.getInstance();
                        calendarEnd.setTime(sdf.parse(end_date));
                        calendarEnd.add(Calendar.DATE, 1);
                    } else {
                        calendarEnd = null;
                    }
                    double grade1;
                    try {
                        double grade = (Double) education.get("grade");
                        grade1 = grade;
                    } catch (Exception e) {
                        Long grade = (Long) education.get("grade");
                        grade1 = (double) grade;
                    }
                    resumeBuilder.addEducation(new Education(calendarStart, calendarEnd, nameEducation, level, grade1));
                }

                JSONArray experienta = (JSONArray) recruiter.get("experience");

                for (Object exp : experienta) {
                    JSONObject experience = (JSONObject) exp;

                    String company = (String) experience.get("company");

                    String position = (String) experience.get("position");

                    String start_exp_date = (String) experience.get("start_date");
                    Calendar calendarStart = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendarStart.setTime(sdf.parse(start_exp_date));
                    calendarStart.add(Calendar.DATE, 1);

                    Calendar calendarEnd;
                    String end_exp_date = (String) experience.get("end_date");
                    if (end_exp_date != null) {
                        calendarEnd = Calendar.getInstance();
                        calendarEnd.setTime(sdf.parse(end_exp_date));
                        calendarEnd.add(Calendar.DATE, 1);
                    } else {
                        calendarEnd = null;
                    }
                    resumeBuilder.addExperience(new Experience(calendarStart, calendarEnd, position, company, "IT"));
                }
                Resume resume = resumeBuilder.build();

                Company recruiterCompany = aplication.getCompany(resume.experiences.first().company);
                Recruiter recruiter1 = new Recruiter(resume, resume.experiences.first().company, salary, contor);
                recruiterCompany.add(recruiter1);
                for (Departament departament : recruiterCompany.departaments) {
                    if (departament.getType().compareTo("IT") == 0) {
                        recruiterCompany.add(recruiter1, departament);
                    }
                }
            }
            nrRecruiters = contor;
            contor = 0;
            JSONArray usersList = (JSONArray) jsonObject.get("users");
            for (Object o : usersList) {
                JSONObject user = (JSONObject) o;
                contor++;

                Resume.ResumeBuilder resumeBuilder = new Resume.ResumeBuilder();
                Information information = new Information();

                String name = (String) user.get("name");
                String[] result = name.split(" ");
                information.setNume(result[0]);
                information.setPrenume(result[1]);

                String email = (String) user.get("email");
                information.setEmail(email);

                String phone = (String) user.get("phone");
                information.setTelefon(phone);

                String date_of_birth = (String) user.get("date_of_birth");
                information.setData_de_nastere(date_of_birth);

                String sex = (String) user.get("genre");
                information.setSex(sex);

                JSONArray languages = (JSONArray) user.get("languages");
                JSONArray languages_level = (JSONArray) user.get("languages_level");

                Iterator<String> iterator = languages_level.iterator();
                for (Object lang : languages) {
                    String limba = (String) lang;
                    String limba_level = iterator.next();

                    information.add(limba, limba_level);
                }

                resumeBuilder.information(information);

                JSONArray interested_companies = (JSONArray) user.get("interested_companies");
                ArrayList<String> companies = new ArrayList();
                for (Object comp : interested_companies) {
                    String company = (String) comp;
                    companies.add(company);
                }

                JSONArray educatie = (JSONArray) user.get("education");
                for (Object edu : educatie) {
                    JSONObject education = (JSONObject) edu;

                    String level = (String) education.get("level");

                    String nameEducation = (String) education.get("name");

                    String start_date = (String) education.get("start_date");
                    Calendar calendarStart = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendarStart.setTime(sdf.parse(start_date));
                    calendarStart.add(Calendar.DATE, 1);

                    String end_date = (String) education.get("end_date");
                    Calendar calendarEnd;
                    if (end_date != null) {
                        calendarEnd = Calendar.getInstance();
                        calendarEnd.setTime(sdf.parse(end_date));
                        calendarEnd.add(Calendar.DATE, 1);
                    } else {
                        calendarEnd = null;
                    }
                    double grade1;
                    try {
                        double grade = (Double) education.get("grade");
                        grade1 = grade;
                    } catch (Exception e) {
                        Long grade = (Long) education.get("grade");
                        grade1 = (double) grade;
                    }
                    resumeBuilder.addEducation(new Education(calendarStart, calendarEnd, nameEducation, level, grade1));
                }

                JSONArray experienta = (JSONArray) user.get("experience");
                for (Object exp : experienta) {
                    JSONObject experience = (JSONObject) exp;

                    String company = (String) experience.get("company");

                    String position = (String) experience.get("position");

                    String start_exp_date = (String) experience.get("start_date");
                    Calendar calendarStart = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendarStart.setTime(sdf.parse(start_exp_date));
                    calendarStart.add(Calendar.DATE, 1);

                    Calendar calendarEnd;
                    String end_exp_date = (String) experience.get("end_date");
                    if (end_exp_date != null) {
                        calendarEnd = Calendar.getInstance();
                        calendarEnd.setTime(sdf.parse(end_exp_date));
                        calendarEnd.add(Calendar.DATE, 1);
                    } else {
                        calendarEnd = null;
                    }
                    resumeBuilder.addExperience(new Experience(calendarStart, calendarEnd, position, company));
                }
                Resume resume = resumeBuilder.build();

                aplication.add(new User(resume, companies, contor));
            }
            nrUsers = contor;
            JSONArray managersList = (JSONArray) jsonObject.get("managers");
            for (Object o : managersList) {
                JSONObject manager = (JSONObject) o;

                Resume.ResumeBuilder resumeBuilder = new Resume.ResumeBuilder();
                Information information = new Information();

                String name = (String) manager.get("name");
                String[] result = name.split(" ");
                information.setNume(result[0]);
                information.setPrenume(result[1]);

                String email = (String) manager.get("email");
                information.setEmail(email);

                String phone = (String) manager.get("phone");
                information.setTelefon(phone);

                String date_of_birth = (String) manager.get("date_of_birth");
                information.setData_de_nastere(date_of_birth);

                String sex = (String) manager.get("genre");
                information.setSex(sex);

                JSONArray languages = (JSONArray) manager.get("languages");
                JSONArray languages_level = (JSONArray) manager.get("languages_level");

                Iterator<String> iterator = languages_level.iterator();
                for (Object lang : languages) {
                    String limba = (String) lang;
                    String limba_level = iterator.next();

                    information.add(limba, limba_level);
                }

                resumeBuilder.information(information);
                double salary;
                try {
                    double grade = (Double) manager.get("salary");
                    salary = grade;
                } catch (Exception e) {
                    Long grade = (Long) manager.get("salary");
                    salary = (double) grade;
                }

                JSONArray educatie = (JSONArray) manager.get("education");
                for (Object edu : educatie) {
                    JSONObject education = (JSONObject) edu;

                    String level = (String) education.get("level");

                    String nameEducation = (String) education.get("name");

                    String start_date = (String) education.get("start_date");
                    Calendar calendarStart = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendarStart.setTime(sdf.parse(start_date));
                    calendarStart.add(Calendar.DATE, 1);

                    String end_date = (String) education.get("end_date");
                    Calendar calendarEnd;
                    if (end_date != null) {
                        calendarEnd = Calendar.getInstance();
                        calendarEnd.setTime(sdf.parse(end_date));
                        calendarEnd.add(Calendar.DATE, 1);
                    } else {
                        calendarEnd = null;
                    }
                    double grade1;
                    try {
                        double grade = (Double) education.get("grade");
                        grade1 = grade;
                    } catch (Exception e) {
                        Long grade = (Long) education.get("grade");
                        grade1 = (double) grade;
                    }
                    resumeBuilder.addEducation(new Education(calendarStart, calendarEnd, nameEducation, level, grade1));
                }

                JSONArray experienta = (JSONArray) manager.get("experience");

                for (Object exp : experienta) {
                    JSONObject experience = (JSONObject) exp;

                    String company = (String) experience.get("company");

                    String position = (String) experience.get("position");

                    String start_exp_date = (String) experience.get("start_date");
                    Calendar calendarStart = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                    calendarStart.setTime(sdf.parse(start_exp_date));
                    calendarStart.add(Calendar.DATE, 1);

                    Calendar calendarEnd;
                    String end_exp_date = (String) experience.get("end_date");
                    if (end_exp_date != null) {
                        calendarEnd = Calendar.getInstance();
                        calendarEnd.setTime(sdf.parse(end_exp_date));
                        calendarEnd.add(Calendar.DATE, 1);
                    } else {
                        calendarEnd = null;
                    }
                    resumeBuilder.addExperience(new Experience(calendarStart, calendarEnd, position, company, "IT"));
                }
                Resume resume = resumeBuilder.build();

                Company managerCompany = aplication.getCompany(resume.experiences.first().company);
                managerCompany.addManager(new Manager(resume, resume.experiences.first().company, salary));
            }
        } catch (ParseException | IOException | java.text.ParseException e) {
            e.printStackTrace();
        }

        try {
            File f = new File("src/reteaSociala.json");
            String absolute = f.getAbsolutePath();
            FileReader reader = new FileReader(absolute);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray TypeOfSubject = (JSONArray) jsonObject.get("users");
            for (Object o : TypeOfSubject) {
                JSONObject numbers = (JSONObject) o;
                for (Integer i = 1; i <= nrUsers + 1; i++) {
                    JSONArray nr = (JSONArray) numbers.get(i.toString());
                    User user = aplication.getUserbyCode(i);
                    if (user != null && nr != null) {
                        for (Object type : nr) {
                            JSONObject oneNumber = (JSONObject) type;

                            JSONArray forU = (JSONArray) oneNumber.get("U");
                            for (Object answer : forU) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                User user1 = aplication.getUserbyCode(Code);
                                user.add(user1);                         //la un user am adaugat alt user
                            }
                            JSONArray forE = (JSONArray) oneNumber.get("E");
                            for (Object answer : forE) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                Employee employee = aplication.getEmployeebyCode(Code);
                                user.add(employee);
                            }
                            JSONArray forR = (JSONArray) oneNumber.get("R");
                            for (Object answer : forR) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                Recruiter recruiter = aplication.getRecruiterbyCode(Code);
                                user.add(recruiter);
                            }
                        }
                    }
                }
            }

            JSONArray TypeOfSubject2 = (JSONArray) jsonObject.get("employee");
            for (Object o : TypeOfSubject2) {
                JSONObject numbers = (JSONObject) o;
                for (Integer i = 1; i <= nrEmployees + 1; i++) {
                    JSONArray nr = (JSONArray) numbers.get(i.toString());
                    Employee employee = aplication.getEmployeebyCode(i);
                    if (employee != null && nr != null) {
                        for (Object type : nr) {
                            JSONObject oneNumber = (JSONObject) type;

                            JSONArray forU = (JSONArray) oneNumber.get("U");
                            for (Object answer : forU) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                User user1 = aplication.getUserbyCode(Code);
                                employee.add(user1);                          //la un employee am adaugat un user
                            }
                            JSONArray forE = (JSONArray) oneNumber.get("E");
                            for (Object answer : forE) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                Employee employee1 = aplication.getEmployeebyCode(Code);
                                employee.add(employee1);
                            }
                            JSONArray forR = (JSONArray) oneNumber.get("R");
                            for (Object answer : forR) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                Recruiter recruiter1 = aplication.getRecruiterbyCode(Code);
                                employee.add(recruiter1);
                            }
                        }
                    }
                }
            }

            JSONArray TypeOfSubject1 = (JSONArray) jsonObject.get("recruiter");
            for (Object o : TypeOfSubject1) {
                JSONObject numbers = (JSONObject) o;
                for (Integer i = 1; i <= nrRecruiters + 1; i++) {
                    JSONArray nr = (JSONArray) numbers.get(i.toString());
                    Recruiter recruiter = aplication.getRecruiterbyCode(i);
                    if (recruiter != null && nr != null) {
                        for (Object type : nr) {
                            JSONObject oneNumber = (JSONObject) type;

                            JSONArray forU = (JSONArray) oneNumber.get("U");
                            for (Object answer : forU) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                User user1 = aplication.getUserbyCode(Code);
                                recruiter.add(user1);                          //la un recruiter am adaugat un user
                            }
                            JSONArray forE = (JSONArray) oneNumber.get("E");
                            for (Object answer : forE) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                Employee employee = aplication.getEmployeebyCode(Code);
                                recruiter.add(employee);
                            }
                            JSONArray forR = (JSONArray) oneNumber.get("R");
                            for (Object answer : forR) {
                                long numar = (Long) answer;
                                int Code = (int) numar;
                                Recruiter recruiter1 = aplication.getRecruiterbyCode(Code);
                                recruiter.add(recruiter1);
                            }
                        }
                    }
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            GUIInterface guiInterface = new GUIInterface("Job Finder Software");
        } catch (Exception e){
            e.printStackTrace();
        }

        for (User user : aplication.Users) {
            ArrayList<Job> jobs = aplication.getJobs(user.companies);
            for (Job job : jobs) {
                job.apply(user);
            }
        }

        for (Company company : aplication.getCompanies()) {
            Collections.sort(company.manager.requests, new RequestsComparator());
        }
/*
        for (Company company : aplication.getCompanies()) {
            for (Job job : company.getJobs()){
                company.manager.process(job);
            }
        }*/

        System.out.println(aplication.Users);
        System.out.println(aplication.getCompanies());



    }
}
