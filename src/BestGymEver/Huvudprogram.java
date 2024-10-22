package BestGymEver;

import java.util.List;
import java.util.Scanner;

public class Huvudprogram {
    public Huvudprogram(){

        Scanner scan = new Scanner(System.in);
        //Filen jag fick av Sigrun.
        String fileToRead = "C:\\Users\\Sebbe\\Desktop\\Java Utbildning\\BestGymEver\\src\\BestGymEver\\KundersData.txt";
        //Filen jag skappade, och den är inte använd i denna klass.
        String fileToWrite = "src/KundersDataTillPT.txt";

        Metodos mt = new Metodos();

        //Lista med alla kunder
        List<Medlem> todoLosMiembros = mt.lectorDeArchivo(fileToRead);
        //Lista med kunder med en medlemskap mindre än ett år.
        List<Medlem> menosDeUnAno = mt.getDateBeforeOneYear(todoLosMiembros);
        //Lista med kunder med en medlemskap mer än ett år.
        List<Medlem> masDeUnAno = mt.getDateAfterOneYear(todoLosMiembros);

        do {
            System.out.println("Sök namnet eller personnummer (eller exit för att avsluta): ");
            String input = scan.nextLine();
            
            if (input.equalsIgnoreCase("exit")) {
                System.exit(0);
            }

            {
                mt.getKundenNotFound(input,todoLosMiembros);
            }

            {
                mt.getKundenMedlem(input,menosDeUnAno);
            }

            {
                mt.getKundenEjMedlem(input,masDeUnAno);
            }

        }while (true) ;
    }

    public static void main(String[] args) {

        Huvudprogram hp = new Huvudprogram();
    }
}