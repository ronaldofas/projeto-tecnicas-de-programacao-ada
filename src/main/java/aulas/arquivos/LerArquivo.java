package aulas.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LerArquivo {

    public static void main(String[] args) throws IOException {
//        lerArquivoIO("c:/temp/song.txt");
//        lerArquivoNIO("c:/temp/song.txt");
        learArquivoBonus("c:/temp/song.txt");
    }

    private static void learArquivoBonus(final String filename) throws IOException {

        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            lines.forEach(line -> {
                System.out.println(line);
            });
        }

    }

    private static void lerArquivoNIO(final String filename) throws IOException {
        final Path path = Paths.get(filename);
        var lines = Files.readAllLines(path);

//        System.out.println(lines);


        lines.forEach(System.out::println);


    }

    private static void lerArquivoIO(final String filename) throws IOException {
        File arquivo = new File(filename);
        FileReader reader = new FileReader(arquivo);
        BufferedReader buffer = new BufferedReader(reader);

        String line;
        while ((line = buffer.readLine()) != null) {
            System.out.println(line); //Hitchhiker;$10

            for(String campo : line.split(";")) {
                System.out.println("\t\t" + campo);
            }
        }
    }


}
