class Meeting {

    /**
     * Solution to Codewars Kata: 6K-Meeting
     * https://www.codewars.com/kata/59df2f8f08c6cec835000012
     *
     * Author: Sebastian LIndfors
     */

    public static String meeting(String s) {

         String[] fullName = s.split(";");
         String[] firstName = new String[fullName.length];
         String[] lastName = new String[fullName.length];

        for (int i = 0; i < fullName.length; i++) {
            String[] nameSplit = fullName[i].split(":");
            firstName[i] = nameSplit[0];
            lastName[i] = nameSplit[1];
        }

        for (int i = 1; i < lastName.length; i++) {
            for (int j = i; j > 0; j--) {
                int order = lastName[j].toLowerCase().compareTo(lastName[j - 1].toLowerCase());
                if (order < 0) {
                    String temp = lastName[j-1];
                    lastName[j-1] = lastName[j];
                    lastName[j] = temp;

                    temp = firstName[j-1];
                    firstName[j-1] = firstName[j];
                    firstName[j] = temp;
                }
                else if (order == 0) {
                    if (firstName[j].toLowerCase().compareTo(firstName[j-1].toLowerCase()) < 0) {
                        String temp = lastName[j-1];
                        lastName[j-1] = lastName[j];
                        lastName[j] = temp;

                        temp = firstName[j-1];
                        firstName[j-1] = firstName[j];
                        firstName[j] = temp;
                    }
                }
                else {
                    break;
                }
            }
        }
        String outputString = "";
        for (int i = 0; i < lastName.length; i++) {
            outputString += "(" + lastName[i].toUpperCase() + ", " + firstName[i].toUpperCase() + ")";
        }
        return outputString;
    }
}
