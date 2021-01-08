package com.TEMA;

import java.util.SortedSet;
import java.util.TreeSet;

public class Resume {
    Information information;
    TreeSet<Education> educations;
    TreeSet<Experience> experiences;

    private Resume(ResumeBuilder builder) throws ResumeIncompleteException {
        if (builder.information == null || builder.educations == null) {
            throw new ResumeIncompleteException("Informatii incomplete | Fara educatie.");
        }
        this.information = builder.information;
        this.educations = builder.educations;
        this.experiences = builder.experiences;
    }

    @Override
    public String toString() {
        return "(Resume)" + '\n' + '\t' + '\t' + information
                + "\n"  + '\t' + '\t' + educations
                + "\n"  + '\t' + '\t' + experiences;
    }

    public static class ResumeBuilder {
        private Information information;
        private TreeSet<Education> educations;
        private TreeSet<Experience> experiences;

        public ResumeBuilder(Information information, TreeSet<Education> education) {
            this.educations = education;
            this.information = information;
        }

        public ResumeBuilder() {
            information = new Information();
            educations = new TreeSet<Education>();
            experiences = new TreeSet<Experience>();
        }

        public ResumeBuilder information(Information information){
            this.information = information;
            return this;
        }

        public ResumeBuilder education(TreeSet<Education> educations){
            this.educations = educations;
            return this;
        }

        public ResumeBuilder experiences(TreeSet<Experience> experiences){
            this.experiences = experiences;
            return this;
        }

        public void addEducation(Education education){
            educations.add(education);
        }

        public void addExperience(Experience experience){
            experiences.add(experience);
        }

        public com.TEMA.Resume build() {
            return new com.TEMA.Resume(this);
        }
    }
}
