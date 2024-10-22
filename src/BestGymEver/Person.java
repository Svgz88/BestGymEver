package BestGymEver;

public class Person {

    private String name;
    private String secondName;
    private String personID;

    public Person(String name, String secondName, String personID) {
        this.name = name;
        this.secondName = secondName;
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

}
