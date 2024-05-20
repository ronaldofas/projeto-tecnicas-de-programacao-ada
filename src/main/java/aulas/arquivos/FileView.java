package aulas.arquivos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileView {

    public static void main(String[] args) throws IOException {
        System.out.println("**********************");
        System.out.println("Directory");
        System.out.print("**********************");
        BasicMethods.fileAndDirectoryInfo("C:/temp");

        System.out.println("\n**********************");
        System.out.println("File");
        System.out.print("**********************");
        BasicMethods.fileAndDirectoryInfo("C:/temp/junit.pdf");

        System.out.println("\n**********************");
        System.out.println("Directory - Relative Path");
        System.out.print("**********************");
        BasicMethods.fileAndDirectoryInfo("/turma213");

        System.out.println("\n**********************");
        System.out.println("Relative Path");
        System.out.print("**********************");
        BasicMethods.fileAndDirectoryInfo(".");

        System.out.println("\n**********************");
        System.out.println("Relative Path 2");
        System.out.print("**********************");
        BasicMethods.fileAndDirectoryInfo("..\\");

        System.out.println("\n**********************");
        System.out.println("Directory not exists");
        System.out.print("**********************");
        BasicMethods.fileAndDirectoryInfo("C:/some-directory");

        System.out.println("\n**********************");
        System.out.println("File not exists");
        System.out.print("**********************");
        BasicMethods.fileAndDirectoryInfo("C:/temp/some-file.txt");

        System.out.println("\n**********************");
        System.out.println("Creating a File");
        System.out.print("**********************");
        Files.createFile(Paths.get("C:/temp/temporaryFile.txt"));

        System.out.println("\n**********************");
        System.out.println("Removing a File");
        System.out.print("**********************");
        Files.deleteIfExists(Paths.get("C:/temp/temporaryFile.txt"));

        System.out.println();
    }


}
