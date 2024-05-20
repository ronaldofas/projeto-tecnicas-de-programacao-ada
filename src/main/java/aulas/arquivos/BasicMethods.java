package aulas.arquivos;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BasicMethods {

    public static void fileAndDirectoryInfo(final String filename)
            throws IOException {

        Path path = Paths.get(filename);

        if (Files.exists(path)) {
            System.out.println(path.getFileName());
            System.out.println("É um diretorio? " + Files.isDirectory(path));
            System.out.println("Path absoluto? " + path.isAbsolute());
            System.out.println("Modificado em "+ Files.getLastModifiedTime(path));
            System.out.println("Tamanho " + Files.size(path));
            System.out.println("Absoluto " + path.toAbsolutePath());

            if (Files.isDirectory(path)) {
                DirectoryStream<Path> directoryStream =
                        Files.newDirectoryStream(path);
                for(var p : directoryStream) {
                    System.out.println(p);
                }
            }
        }
    }
}
