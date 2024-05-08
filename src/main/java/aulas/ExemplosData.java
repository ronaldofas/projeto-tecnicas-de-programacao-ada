package aulas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class ExemplosData {

    /* classes wrapper

    char -> Character

    byte -> Byte
    short -> Short
    int -> Integer
    long -> Long

    float -> Float
    double -> Double

    boolean -> Boolean

     */



    // elementos -> atributo, metodo
    //assinatura  --> retorno + nome + parametros
    // super x this ?
    // super -> referencia ao pai
    // this -> referencia propria classe
    /*
    super.getName();
    super() --> construtor
    this()  -->  construtor
     */

    // modificador de acesso: visibilidade
    /*
    -> public: visibilidade universal
    -> private: somente dentro da propria classe!!!!
    -> protected: pacote + classes filhas (heranca)
    -> "default"/ pacote: visibilidade dentro do mesmo pacote

    // qualificador
    -> static: pertence a Classe
    -> final: nao permite sobrescrita --> é o unico em variaveis locais
    -> abstract: exige a sobrescrita (interface = 100% abstrata)
    -> synchronized: controle de concorrência
    ...
     */

    // Date -> String -> Date
    public static void partesEspecificas() {
        LocalDate date = LocalDate.of(2022, Month.OCTOBER, 20);

        System.out.println(date.getDayOfWeek());
        System.out.println(date.getMonth());
        System.out.println(date.getYear());
        System.out.println(date.getDayOfYear());
    }

    //format
    public static void imprimir() {
        var dateTime = LocalDateTime.now();
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

        var formatterText =
                DateTimeFormatter
                        .ofPattern("'Usuario cadastrado em: 'dd/MM/yyyy hh:mm:ss");

        var prefixo = "Usuario cadastrado em: ";

        System.out.println(prefixo + dateTime.format(formatter));
        System.out.println(dateTime.format(formatter));
        System.out.println(formatterText.format(dateTime));

    }

    public static void fromString() {

        LocalDate dataInicio = LocalDate.of(2022, 01, 01);
        LocalDate dataFim = LocalDate.parse("2022-01-30");
        System.out.println("Ferias de " + dataInicio + " ate " + dataFim);


        LocalTime horaInicio = LocalTime.of(8,15,30);
        LocalTime horaFim = LocalTime.parse("09:47:55");
        System.out.println("Intervalo de " + horaInicio + " ate " + horaFim);

        LocalDateTime dataHoraInicio =
                LocalDateTime.of(2022, 12,15, 1, 22, 43);
        LocalDateTime dataHoraFim =
                LocalDateTime.parse("2022-12-20T05:45:43");
        System.out.println("Recesso de " + dataHoraInicio + " ate " + dataHoraFim);


        var df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        var date = df.parse("05-10-1994");
        System.out.println(date);
    }






}
