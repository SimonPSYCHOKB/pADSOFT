import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {  
    	Application app = new Application();
    	
        try{
            BufferedReader buf = new BufferedReader(new FileReader("/home/blanca/workspace/padsof/src/data.txt"));
            List<String> words = new ArrayList<>();
            String lineJustFetched = null;
            String[] wordsArray;
            
            buf.readLine();
            while(true){
                lineJustFetched = buf.readLine();
                if(lineJustFetched == null){  
                    break; 
                }else{
                    wordsArray = lineJustFetched.split(";");
                    words.clear();
                    for(String each : wordsArray){
                        if(!"".equals(each)){
                            words.add(each);
                        }
                    }
                    if(words.isEmpty() == false){
	                    String fname = words.get(0);
	                    String lname=  words.get(1);
	                    String password = words.get(4);
	                    String email = words.get(2);
	                    String manu = words.get(3);
	                    //app.addStudent(new Student(fname, lname, password, email, manu));
	                    //System.out.println(students.size());
                    }
                }
            }

            buf.close();

        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(students.size());
        
        //System.out.println(app.students());
        Student student = new Student("Luis Gallego", "Peres" ,"s.ll", "Luis.Gallego=esdu.es", "1264" );
        if(app.logIn(student, "s.ll") == false)
        	System.out.println("Usuario no existente");
        
        
       
    }
}

