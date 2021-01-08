package com.TEMA;

public class Constraint {
    double inferior;
    double superior;

    public Constraint(double inferior, double superior){
        this.inferior = inferior;
        this.superior = superior;
    }

    @Override
    public String toString() {
        return "Constraint{" +
                "inferior=" + inferior +
                ", superior=" + superior +
                '}';
    }
}
