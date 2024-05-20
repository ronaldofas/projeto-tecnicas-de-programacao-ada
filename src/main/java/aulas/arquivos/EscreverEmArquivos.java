package aulas.arquivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class EscreverEmArquivos {

    public static void main(String[] args) throws IOException {
//        escreverNoArquivoIO(Song.getSongs(), "c:/temp/song.txt");
//        escreverNoArquivoIOV2(Song.getSongs(), "c:/temp/song.txt");
//        escreverNoArquivoNIO(Song.getSongs(), "c:/temp/song.txt");

    }

    private static void escreverNoArquivoNIO(final List<Song> songs,
                                             final String filename) throws IOException {

        Path path = Paths.get(filename);

        var content = songs.stream()
                .map(song -> song.getArtist() + ";" + song.getTitle())
                .collect(Collectors.joining("\n"));

        Files.writeString(path, content);

    }


    private static void escreverNoArquivoIOV2(final List<Song> songs,
                                              final String filename)
            throws IOException {

        File arquivo = new File(filename);
        FileWriter fileWriter = new FileWriter(arquivo);
        PrintWriter buffer = new PrintWriter(fileWriter);

        for(Song song : songs) {
            final String linha = song.getArtist() + ";" + song.getTitle();
            buffer.println(linha);
        }

        buffer.flush();
        buffer.close();

        System.out.println("Arquivo Gerado");


    }

    private static void escreverNoArquivoIO(final List<Song> songs,
                                            final String filename)
            throws IOException {

        File arquivo = new File(filename);
        FileWriter fileWriter = new FileWriter(arquivo);
        BufferedWriter buffer = new BufferedWriter(fileWriter);

        for(Song song : songs) {
            final String linha = song.getArtist() + ";" + song.getTitle();
            buffer.write(linha);
            buffer.newLine();
        }

        buffer.flush();
        buffer.close();

        System.out.println("Arquivo Gerado");
    }


}
