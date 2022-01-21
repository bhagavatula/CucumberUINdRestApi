package com.apitest.automation.compare;

public class CompareDetails {
    @Override
    public String toString(){
        return "CompareDetails {message="+ message+"]\n";
    }
    public String message;
    public String getMessage(){
        return message;
    }
    public static final Character OPENING_CHARACTER = '[';
    public static final Character CLOSING_CHARACTER = ']';

    public static final String ASSERT_LEFT = "actual "+ OPENING_CHARACTER;
    public static final String ASSERT_LEFT2 = "expected not same "+ OPENING_CHARACTER;
    public static final String ASSERT_MIDDLE = CLOSING_CHARACTER +" != "+ OPENING_CHARACTER;
    public static final String ASSERT_RIGHT = Character.toString(CLOSING_CHARACTER);
    public static final String ASSERT_MIDDLE_PASS = CLOSING_CHARACTER+ " = "+OPENING_CHARACTER;

    public CompareDetails(Object actual, Object expected, String message, boolean pass){
        String formatted = "";
        if (null!= message){
            formatted = message+ " " ;
        }
        if (pass){
            this.message = formatted+OPENING_CHARACTER+actual+ASSERT_MIDDLE_PASS+expected+ASSERT_RIGHT;
        }else{
            this.message= formatted+OPENING_CHARACTER+actual+ASSERT_MIDDLE+ASSERT_RIGHT;
        }
    }
}
