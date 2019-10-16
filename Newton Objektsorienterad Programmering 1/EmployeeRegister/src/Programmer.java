public class Programmer extends Employee {


    private String[] programingLangueges;


    public Programmer(String fn, String ln , int number, int yearsOld) {
        super(fn, ln, "Programmer", number, yearsOld);

        programingLangueges = new String[0];

    }
    public Programmer(String fn, String ln, String pos, int number, int yearsOld, String[] languages) {
        super(fn, ln, pos, number, yearsOld);

        programingLangueges = languages;
    }

    public void addLanguegeProficiency(String language) {

        for (int i = 0; i < programingLangueges.length; i++) {

            if (language.equalsIgnoreCase(programingLangueges[i])) {

                System.out.println(getFirstName() + " " + getLastName() + " already has a registered proficiency in " + language);
                break;

            }
        }
        String[] newArray = new String[programingLangueges.length + 1];
        for (int i = 0; i < programingLangueges.length; i ++) {
            newArray[i] = programingLangueges[i];
        }

        newArray[programingLangueges.length] = language;
        programingLangueges = newArray;
    }


}
