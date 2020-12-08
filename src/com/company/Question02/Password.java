package com.company.Question02;

public class Password {

    char targetChar;
    String password;
    int numArg1, numArg2;

    public Password(String policies) {
        String[] policiesArray = policies.split(" ");

        targetChar = policiesArray[1].charAt(0);
        password = policiesArray[2];

        String[] numberArgsStr = policiesArray[0].split("-");

        numArg1 = Integer.parseInt(numberArgsStr[0]);
        numArg2 = Integer.parseInt(numberArgsStr[1]);
    }

    public boolean isValid(int checkType) {
        switch (checkType){
            case 1:
                //part 1
                int occurrence = 0;
                for (char pwdChar : password.toCharArray()){
                    if (pwdChar == targetChar) {
                        occurrence++;
                    }
                }
                return occurrence >= numArg1 && occurrence <= numArg2;

            case 2:
                //part 2
                char[] pwdCharArray = password.toCharArray();
                return pwdCharArray[numArg1 - 1] == targetChar ^ pwdCharArray[numArg2 - 1] == targetChar;

            default:
                return false;
        }
    }

}
