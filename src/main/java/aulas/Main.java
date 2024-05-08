package aulas;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {

    public static void main(String[] args) {
        var dateTime = ZonedDateTime.now();

        ZonedDateTime zonedDateTimeSP = dateTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime zonedDateTimeAC = dateTime.withZoneSameInstant(ZoneId.of("Brazil/Acre"));
        ZonedDateTime zonedDateTimePT = dateTime.withZoneSameInstant(ZoneId.of("Europe/Lisbon"));

        System.out.println("Data hora fuso SP: " + zonedDateTimeSP);
        System.out.println("Data hora fuso AC: " + zonedDateTimeAC);
        System.out.println("Data hora fuso PT: " + zonedDateTimePT);
    }
}
