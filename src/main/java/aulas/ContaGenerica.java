package aulas;

public class ContaGenerica {

    private Double saldo;

    public ContaGenerica(final Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "ContaGenerica{" + "saldo=" + saldo + '}';
    }

    public static void main(String[] args) {
        int x = Integer.parseInt("01010", 2);
        System.out.println(x);
    }
}
