package BestGymEver;

public class Medlem extends Person{

    private String membershipDate;

    public Medlem(String name, String secondName, String personID, String membershipDate){
        super(name, secondName, personID);
        this.membershipDate = membershipDate;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public String setMembershipDate(String membershipDate) {
       return this.membershipDate = membershipDate;
    }

    @Override
    public String toString() {
        return "namn: "+getName() + " efternamn: " +getSecondName()+ " personnumer: " +getPersonID() +" medlemskap: "+ getMembershipDate() + " ";

    }
}
