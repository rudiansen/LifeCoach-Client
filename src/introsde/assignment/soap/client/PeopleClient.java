package introsde.assignment.soap.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.ws.Holder;

import introsde.assignment.soap.ws.PeopleService;
import introsde.assignment.soap.ws.HealthMeasureHistory;
import introsde.assignment.soap.ws.LifeStatus;
import introsde.assignment.soap.ws.MeasureDefinition;
import introsde.assignment.soap.ws.People;
import introsde.assignment.soap.ws.Person;

public class PeopleClient{
	
	static int personId = 0;
	static String measureType = "weight";
	static int mid = 0;
	static int pIdToDelete;
	static String WSDL_URL = "https://lifecoach-181499.herokuapp.com/ws/people?wsdl";
	
    public static void main(String[] args) throws Exception { 
    	
        PeopleService service = new PeopleService();
        People people = service.getPeopleImplPort();                
       
        System.out.println("WSDL URL => "+WSDL_URL+"\n");
        
        //Method #1 getPeopleList() 
        List<Person> pList = people.getPeopleList();                    
        printHeader(1, "getPeopleList()");
        System.out.println("=> Results: ");
        
        for(int i=0; i<pList.size(); i++){
        	printPerson(pList.get(i));   
        	
        	//Store one of the personId
        	if(personId == 0)
        		personId = pList.get(i).getPersonId();         		
        	
        	pIdToDelete = pList.get(i).getPersonId(); 
        }    
        System.out.println("\n");
        
        //Method #2 readPerson(int personId)
        printHeader(2, "readPerson(int id)");
        System.out.println("Read person with Id = "+personId);
        System.out.println("=> Results: ");
        Person p = people.readPerson(personId);
        printPerson(p);        
        
        //Method #3 updatePerson(Person p)
        printHeader(3, "updatePerson(Person p)");
        System.out.println("Change the Firstname to 'Alexander' for the person with Id = "+personId);       
        p.setFirstname("Alexander");
        people.updatePerson(p);
        //Read again person with id personId
        p = people.readPerson(personId);
        System.out.println("=> Results: ");
        printPerson(p);       
        
        //Create a new person with its health profile
        Person newPerson = new Person();
        newPerson.setPersonId(50);
        newPerson.setFirstname("David");
        newPerson.setLastname("Beckam");
        newPerson.setBirthdate("1987-05-23");
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    	Date today = Calendar.getInstance().getTime();
    	
        LifeStatus ls1 = new LifeStatus();
        ls1.setDateRegistered(df.format(today));        
        ls1.setMeasureValue("87");
        
        MeasureDefinition md1 = new MeasureDefinition();
        md1.setMeasureName(measureType);
        ls1.setMeasureDefinition(md1);  
        newPerson.getLifeStatus().add(ls1);
                        
        //Method #4 createPerson(Person p)
        int result = people.createPerson(newPerson);
        printHeader(4, "createPerson(Person p)");
        System.out.println("Create a new person with Id = 50"); 
        System.out.println("=> Results: ");
        //result -1 means the creation of the new person is failed
        if(result == -1){
        	System.out.println("\tcreating a new person failed. The personId 50 already exists.");
        }
        else{
        	//Read the person which has just been created                        
            p = people.readPerson(50);            
            printPerson(p);
        }        
        System.out.println("\n");
        
        //Method #5 deletePerson(int personId)
        printHeader(5, "deletePerson(int personId)");
        System.out.println("Delete person with Id = "+pIdToDelete); 
        final Holder<Integer> holderInt = new Holder<Integer>(pIdToDelete);
        people.deletePerson(holderInt);
        System.out.println("\n");        
        
        //Method #6 readPersonHistory(int id, String measureType)
        printHeader(6, "readPersonHistory(int id, String measureType)");
        System.out.println("Read person History ("+ measureType +") with personId = "+personId);
        List<HealthMeasureHistory> measureList = people.readPersonHistory(personId, measureType);
        System.out.println("=> Results: ");
        for(int k=0; k<measureList.size(); k++){
        	System.out.println("\tMid\t\t: "+ measureList.get(k).getIdMeasureHistory());
        	System.out.println("\tMeasure type\t: "+ measureList.get(k).getMeasureDefinition().getMeasureName());
        	System.out.println("\tMeasure value\t: "+ measureList.get(k).getValue());
        	System.out.println("\tCreated date\t: "+ measureList.get(k).getTimestamp());
        	System.out.print("\n");
        	
        	if(mid == 0)
        		mid = measureList.get(k).getIdMeasureHistory();
        }
        
        //Method #7 readMeasureTypes()
        printHeader(7, "readMeasureTypes()");
        List<MeasureDefinition> mdList = people.readMeasureTypes();
        System.out.println("=> Results: ");
        for(int i=0; i<mdList.size(); i++){
        	System.out.println("\tMid\t\t: "+mdList.get(i).getIdMeasureDef());
        	System.out.println("\tMeasure type\t: "+mdList.get(i).getMeasureName());
        	System.out.println("\tMeasure val type: "+mdList.get(i).getMeasureType());
        	System.out.print("\n");
        }
        
        //Method #8 readPersonMeasure(int id, String measureType, Long mid)
        printHeader(8, "readPersonMeasure(int id, String measureType, Long mid)");
        System.out.println("Read person Measure ("+ measureType +") with personId = "+personId+" and mid = "+mid);
        HealthMeasureHistory hMeasure = people.readPersonMeasure(personId, measureType, mid);
        System.out.println("=> Results: ");
        if(hMeasure != null){
        	System.out.println("\tMid\t\t: "+hMeasure.getIdMeasureHistory());
        	System.out.println("\tMeasure type\t: "+hMeasure.getMeasureDefinition().getMeasureName());
        	System.out.println("\tMeasure value\t: "+hMeasure.getValue());
        	System.out.println("\tCreated date\t: "+hMeasure.getTimestamp());
        	System.out.print("\n");
        }
        
        //Method #9 savePersonMeasure(int id, Measure m)
        printHeader(9, "savePersonMeasure(int id, Measure m)");
        System.out.println("Save new person Measure with personId = "+personId+" and "+measureType+" = 93.8");
        //Create a new measure
        LifeStatus ls = new LifeStatus();       
        ls.setDateRegistered(df.format(today));        
        ls.setMeasureValue("93.8");
        
        MeasureDefinition md = new MeasureDefinition();        
        md.setMeasureName(measureType);
        ls.setMeasureDefinition(md); 
        final Holder<LifeStatus> holderLifeStatus = new Holder<LifeStatus>(ls);
        people.savePersonMeasure(personId, holderLifeStatus);
        //Get the person with the new measure that has just been added
        p = people.readPerson(personId);
        System.out.println("=> Results: ");
        printPerson(p);        
        
        //Method #10 updatePersonMeasure(int id, Measure m)
        printHeader(10, "updatePersonMeasure(int id, Measure m)");
        //Get one of the mid from the person with id = personId
        List<LifeStatus> lsList = p.getLifeStatus();
        for(LifeStatus lfS : lsList){
        	mid = lfS.getMid();
        	
        	//Update value of the measure to 1000
        	lfS.setMeasureValue("1000");
        	
        	System.out.println("Update person Measure with personId = "+personId+" and m.mid = "+mid+" with value = 1000");
            people.updatePersonHealthProfile(personId, lfS);
            
        	break;
        }        
        //Read the person profile that has just been updated
        p = people.readPerson(personId);
        System.out.println("=> Results: ");
        printPerson(p);
    }
    
    private static void printHeader(int number, String methodName){		
		System.out.println("Request #"+number+": "+methodName);			
	}
    
    private static void printPerson(Person person){
    	try{
    		System.out.println("-------------------------------------------------------");
    		System.out.println("\tPerson ID\t: "+ person.getPersonId());
    		System.out.println("\tFirstname\t: "+ person.getFirstname());
    		System.out.println("\tLastname\t: "+ person.getLastname());
    		System.out.println("\tBirthdate\t: "+ person.getBirthdate());
    		System.out.print("\tHealth profile\t: ");
    		
    		List<LifeStatus> lifeStatusList = person.getLifeStatus();
    		if(lifeStatusList.size() == 0)
    			System.out.print("N/A\n");
    		else{
    			System.out.println("");
    			for(int j=0; j<lifeStatusList.size(); j++){
        			System.out.println("\t\tmid\t\t: "+ lifeStatusList.get(j).getMid());        			
        			System.out.println("\t\tMeasure type\t: "+ lifeStatusList.get(j).getMeasureDefinition().getMeasureName());
        			System.out.println("\t\tMeasure value\t: "+ lifeStatusList.get(j).getMeasureValue());
        			System.out.println("\t\tRegistered date\t: "+ lifeStatusList.get(j).getDateRegistered()+"\n");
        		}
    		}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
