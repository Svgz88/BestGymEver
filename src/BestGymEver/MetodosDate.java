package BestGymEver;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MetodosDate {
    DateTimeFormatter horaFechaFormateada = DateTimeFormatter.ofPattern("dd-MM-yyyy"+" "+"HH:mm:ss");
    //Este metodo retorna la fecha y hora completa utilizando la variable de formateo.
    public String getStockholmDateTime() {
        Instant i = Instant.now();
        ZoneId zoneEurope = ZoneId.of("Europe/Stockholm");
        ZonedDateTime zDateStockholm = i.atZone(zoneEurope);
        String dateStockholm = horaFechaFormateada.format(zDateStockholm);
        return dateStockholm;
    }
    //Este metodo retorna la fecha y hora completa sin utilizar la variable de formateo(horaFechaFormateada).
    public String getStockholmTodaysTime(){
        Instant instant = Instant.now();
        ZoneId zonaEuropa = ZoneId.of("Europe/Stockholm");
        ZonedDateTime zonaHorariaEstocolmo = instant.atZone(zonaEuropa);
        String resultado2 = zonaHorariaEstocolmo.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"+" "+"HH:mm:ss"));
        return resultado2;
    }

    public String getStockholmFormatedTime(ZonedDateTime zdt){
        //Este metodo retorna la fecha y hora formateada.
        String fechaHoraFormateada = horaFechaFormateada.format(zdt);
        return fechaHoraFormateada;

    }

    public ZonedDateTime getZoneDateTime(ZoneId z){
        //Este metodo retorna un ZonedFateTime.
        Instant i = Instant.now();
        return i.atZone(z);
    }

    public ZoneId getStockolmZoneId(){
        //Este metodo retorna un ZoneId.
        return ZoneId.of("Europe/Stockholm");

    }
}
