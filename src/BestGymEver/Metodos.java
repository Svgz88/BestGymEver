package BestGymEver;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Metodos {
    //metodo que lee el archivo y retorna una lista de tipo Medlem.
    public List<Medlem> lectorDeArchivo(String file) {
        String primeraLinea;
        String segundaLinea;

        List<Medlem> listaDeMiembros = new ArrayList<>();

        String[] splitPrimeraLinea;
        String[] splitSegundaLinea;

        try (BufferedReader lector = new BufferedReader(new FileReader(file))) {
            while ((primeraLinea = lector.readLine()) != null) {
                segundaLinea = lector.readLine();

                String s1 = primeraLinea.replace(" ", ",");

                splitPrimeraLinea = s1.split(",");
                splitSegundaLinea = segundaLinea.split(",");

                String personID = splitPrimeraLinea[0].trim();
                String name = splitPrimeraLinea[2].trim();
                String secondName = splitPrimeraLinea[3].trim();
                String membershipDate = splitSegundaLinea[0].trim();

                Medlem nameSecondnamePersonIDMembership = new Medlem(name, secondName, personID, membershipDate);

                listaDeMiembros.add(nameSecondnamePersonIDMembership);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Filen kunde inte hittas.");
            e.printStackTrace();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Fel att läsa filen");
            e.printStackTrace();
            System.exit(0);
        }
        return listaDeMiembros;
    }

    //Metodo que lee si las fecha dada es mayor a 1 año.
    //Returnerar en lista med kunder som varit inskriven mer än ett år.
    public List<Medlem> getDateAfterOneYear(List<Medlem> list) {

        List<Medlem> listaMasDeUnAño = new ArrayList<>();
        LocalDate ld = LocalDate.now();

        for (Medlem m : list) {
            //ms = membership, ld = localdate.
            LocalDate ms = LocalDate.parse(m.getMembershipDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //si ms es mayor o igual a la fecha de ahora.
            if (ChronoUnit.YEARS.between(ms, ld) > 1) {
                listaMasDeUnAño.add(m);
            }
        }
        return listaMasDeUnAño;
    }
    //returnerar en lista med kunder som varit inskriven mindre än ett år
    public List<Medlem> getDateBeforeOneYear(List<Medlem> list) {

        List<Medlem> listaMenosDeUnAño = new ArrayList<>();
        LocalDate ld = LocalDate.now();

        for (Medlem m : list) {
            //ms = membership, ld = localdate.
            LocalDate ms = LocalDate.parse(m.getMembershipDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //si ms es menor a un año.
            if (ChronoUnit.YEARS.between(ms, ld) <= 1) {
                listaMenosDeUnAño.add(m);
            }
        }
        return listaMenosDeUnAño;
    }

    //Imput del usuario es igual a (nombre + apellido) o (personnumer).
    //Användaren skriver namn + efternamn eller personnummer.
    public boolean ValidarMiembro(String s, Medlem m) {
        String nameSecondname = m.getName() + " " + m.getSecondName();
        //SEBBE//Haber formateado este personnummer para que siempre sea (input.length() == 11 || input.length() < 10 || input.length() > 12))
        // o si lo hice???
        return s.equalsIgnoreCase(nameSecondname) || s.equalsIgnoreCase(m.getPersonID());
    }

    //chequeado.
    //SEBBE// Se pude crerar un metodo que retorne una List<Medlem> y en la clase pricipal llamarla con el metodo escritorDearchivo(String file, List<Medlem>listaTillPT)
    public void getKundenMedlem(String input, List<Medlem> lista) {
        Metodos mt = new Metodos();
        MetodosDate md = new MetodosDate();
        boolean found = false;

        List<Medlem> listaTillPT = new ArrayList<>();
        for (Medlem miembro : lista) {
            if (mt.ValidarMiembro(input, miembro)) {
                System.out.println("Kunden är en nuvarande medlem.");

                //System.out.println("Välkomen " + miembro.getName() + " du blir sparad i PT:s fil");

                //Här skappas filen till PM.

                String i = miembro.setMembershipDate(md.getStockholmTodaysTime());
                Medlem nuvarandeMedlemmar = new Medlem(miembro.getName(), miembro.getSecondName(), miembro.getPersonID(), i);
                listaTillPT.add(nuvarandeMedlemmar);
                String fileToWrite = "C:\\Users\\Sebbe\\Desktop\\Java Utbildning\\BestGymEver\\src\\BestGymEver\\KundersDataTillPT.txt";
                mt.escritorDeArchivo(fileToWrite, listaTillPT);
                found = true;
                break;
            }
        }
    }

    //chequeado.
    public void getKundenEjMedlem(String input, List<Medlem> lista) {
        Metodos mt = new Metodos();
        boolean found = false;
        for (Medlem miembro : lista) {
            if (mt.ValidarMiembro(input, miembro)) {
                System.out.println("Kunden är en före detta kund.");
                found = true;
                break;
            }
        }
    }
    //chequeado, pero no se podria mejorar.
    public void getKundenNotFound(String input, List<Medlem> lista) {
        Metodos mt = new Metodos();
        boolean found = false;
        for (Medlem miembro : lista) {
            if (mt.ValidarMiembro(input, miembro)) {
                found = true;
                break;
            }

        }
        if (!found) {
            if(input.matches("\\d+") && (input.length() == 11 || input.length() < 10 || input.length() > 12)){
                System.out.println("Skriv ett giltigt namn eller personnummer.");
            }else {
                System.out.println("Personen finns inte i filen och har sålunda aldrig varit medlem och är obehörig");
            }
        }
    }
    //FileWriter.
    public void escritorDeArchivo(String file, List<Medlem> list) {
        try (PrintWriter escritor = new PrintWriter(new BufferedWriter(new FileWriter(file, true)))) {
            for (Medlem miembro : list) {
                MetodosDate md = new MetodosDate();
                escritor.println(miembro.getName() + " " + miembro.getSecondName() + ", " + miembro.getPersonID() + ", " + md.getStockholmTodaysTime());

            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Kunde inte skriva till filen.");
        }
    }
    //aldrig använd.
    public void InputIsEmpty(String input) {
        if (input.isEmpty() || input.matches("\\d+")) {
            System.out.println("Skriv ett giltigt namn eller personnummer.");
        }
    }
    //aldrig använd.
    public String StringIsEmpty(String s) {
        if (s.isEmpty() || s.matches("\\d+")) {
            return "Skriv ett giltigt namn eller personnummer.";
        } else return null;
    }
}

