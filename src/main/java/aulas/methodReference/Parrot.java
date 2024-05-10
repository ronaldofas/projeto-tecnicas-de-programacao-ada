package aulas.methodReference;

public class Parrot {

    public static void makeSound(final String word) {
        final LearnToSpeak learner = s -> System.out.println(s);
        ParrotHelper.teacher(word, learner);
    }

    public static void makeSoundMethodReference(final String word) {
        final LearnToSpeak learner = System.out::println;
        ParrotHelper.teacher(word, learner);
    }

    public static void makeSoundUppercase(final String word) {
        final LearnToSpeak learner = s -> {
            s = "ALO - " + s.toUpperCase();
            System.out.println(s);
        };
        ParrotHelper.teacher(word, learner);
    }


    public static void main(String[] args) {
        makeSound("Hoje é sexta feira");
        makeSoundMethodReference("Hoje é sexta feira");
        makeSoundUppercase("Hoje é sexta feira");
    }


}
