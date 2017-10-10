package server.logic.handlers;


import server.logic.handler.model.Output;
import server.logic.tables.UserTable;

public class OutputHandler {
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;
    public static final int CLERK = 2;
    public static final int USER = 3;
    public static final int CREATEUSER=4;
    
    public Output createUser(String input) {
		Output output=new Output("",0);
		String[] strArray = null;   
        strArray = input.split(",");
        boolean email=strArray[0].contains("@");
        Object result="";
        if(strArray.length!=2 || email!=true){
        	output.setOutput("Your input should in this format:'username,password'");
        	output.setState(CREATEUSER);
        }else{
        	result=UserTable.getInstance().createuser(strArray[0], strArray[1]);
        	if(result.equals(true)){
        		output.setOutput("Success!");
        	}else{
        		output.setOutput("The User Already Exists!");
        	}
        	output.setState(CLERK);
        }
		return output;
	}
}