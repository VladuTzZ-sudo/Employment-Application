package com.TEMA;

enum Departments {
    IT,
    Management,
    Marketing,
    Finance
}

public class DepartmentFactory {
    public Departament getDepartment(Departments type) {
        if (type != null) {
            return switch (type) {
                case IT -> new IT();
                case Management -> new Management();
                case Marketing -> new Marketing();
                case Finance -> new Finance();
            };
        }
        return null;
    }
}
