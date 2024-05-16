package aulas.streams;

import com.ada.cliente.CPF;
import com.ada.cliente.Classificacao;
import com.ada.cliente.Cliente;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo {


    public static void main(String[] args) {
        //source
        List<String> lista = List.of("azul",
                                       "preto",
                                       "vermelho",
                                       "branco",
                                       "azul",
                                       "verde",
                                       "rosa",
                                       "lilas",
                                       "amarelo",
                                       "ciano",
                                       "marrom",
                                       "laranja",
                                       "cinza");

        Stream<String> cores =
                Stream.of("azul",
                          "preto",
                          "vermelho",
                          "branco",
                          "azul",
                          "verde",
                          "rosa",
                          "lilas",
                          "amarelo",
                          "ciano",
                          "marrom",
                          "laranja",
                          "cinza");

        var stream2 = lista.stream();

        var stream3 = Stream.empty();

        Stream<Integer> infinita =
                Stream.generate(() -> new Random().nextInt(100));

        Stream<Integer> counter =
                Stream.iterate(1, x -> x+1);


//        System.out.println(stream);
//        System.out.println(lista);

//        infinita.forEach(System.out::println);
        //infinita.forEach(x -> System.out.println("Valor randomico: " + x));
//        counter.forEach(System.out::println);


        // da stream infinita, imprimir somente valores com alguma logica
//        infinita
//                .filter(x -> x%2 == 0)
//                .distinct()
//                .limit(10)
//                .forEach(System.out::println);








//        infinita.limit(10).forEach(System.out::println);
        // da stream infinita, imprimir um texto customizado

//        infinita
//                .limit(10)
//                .map(x -> "Valor randomico: " + x)
//                .forEach(System.out::println);



//        cores
//                .skip(3)
//                .limit(6)
//                .distinct()
//                .sorted();
//                .forEach(System.out::println);

        /*
       "azul", "preto", "vermelho", "branco", "azul", "verde", "rosa", "lilas",
       "amarelo", "ciano", "marrom", "laranja", "cinza" */

//        listaClientes()
//                .stream()
//                .distinct()
//                .sorted(Comparator.comparing(Cliente::getNome))
//                .forEach(System.out::println);


//        System.out.println("@".compareTo("a"));

//        System.out.println("a".compareTo("a"));

//        listaClientes()
//                .stream()
//                .distinct()
//                .sorted(Comparator.comparing(Cliente::getNome))
//                .map(cliente -> cliente.getNome())
//                .forEach(System.out::println);

//        System.out.println(cores.distinct().count());
          var y = infinita
                .limit(10)
                .min(Comparator.comparingInt(x -> x));

//        y.ifPresent(System.out::println);


        var nomes =
                listaClientes().stream()
                .map(cliente -> cliente.getNome())
                .collect(Collectors.toList());

//        System.out.println(nomes);

        var nomesCsv =
                listaClientes().stream()
                        .map(Cliente::getNome)
                        .collect(Collectors.joining(";"));
        System.out.println(nomesCsv);











    }


    public static List<Cliente> listaClientes() {
        Cliente jose = new Cliente(new CPF("12345678901"),
                                   Classificacao.PF , "jose");

        Cliente joao = new Cliente(new CPF("12345678902"),
                                   Classificacao.PF , "joao");

        Cliente maria = new Cliente(new CPF("12345678900"),
                                    Classificacao.PF , "maria");

        Cliente joseClone = new Cliente(new CPF("12345678901"),
                                        Classificacao.PF , "jose");

        return List.of(joao, jose, maria, joseClone);
    }





}
