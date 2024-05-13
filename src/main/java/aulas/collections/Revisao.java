package aulas.collections;

import com.ada.cliente.CPF;
import com.ada.cliente.Classificacao;
import com.ada.cliente.Cliente;
import com.ada.cliente.Identificador;
import com.ada.conta.Conta;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Revisao {

    public static void main(String[] args) {

        Cliente jose = new Cliente(new CPF("12345678901"),
                                      Classificacao.PF , "jose");

        Cliente joao = new Cliente(new CPF("12345678902"),
                                      Classificacao.PF , "joao");

        Cliente maria = new Cliente(new CPF("12345678903"),
                                      Classificacao.PF , "maria");

        Cliente joseClone = new Cliente(new CPF("12345678909"),
                                   Classificacao.PF , "jose");


        /*
        Lista
            pros: mantem ordem de insercao, indexado
            cons: mantem elementos repetidos, busca sequencial
            classes: ArrayList, Vector (sincronizado), LinkedList
         */
        List<Cliente> lista = List.of(joao, jose, maria, joseClone);

        /*
        Conjuntos
            pros: nao permite elemento repetido, busca eficiente
            cons: nao garante a leitura na ordem de insercao
            classes: HashSet, TreeSet, LinkedHashSet
         */
        Set<Cliente> conjunto =  new HashSet<>();  //Set.of(joao, jose, maria, joseClone);
        conjunto.add(joao);
        conjunto.add(jose);
        conjunto.add(maria);
        conjunto.add(joseClone);

        /*
        Mapas
          pros: estrutura dicionario (chave-valor), busca
          regra: nao permite chaves repetidas
          classes: HashMap, TreeMap, LinkedHashMap
         */
        Map<Identificador, Cliente> mapa = Map.of(
                joao.getIdentificador(), joao,
                maria.getIdentificador(), maria,
                jose.getIdentificador(), jose,
                joseClone.getIdentificador(), joseClone);


//        System.out.println("###################");
//        System.out.println(lista);
//        System.out.println("###################");
//        System.out.println(mapa);
//
//        System.out.println("###################");
//        System.out.println(conjunto);


        final var nomeExistente = "jose";
        final var nomeInexistente = "zezim";

        final  Optional<Cliente> optionalCliente = findCliente(lista, nomeInexistente);

//        final var algumCliente =
//                optionalCliente.orElse(new Cliente(new CPF("12345678902"), Classificacao.PF , "joao"));



//        optionalCliente.ifPresentOrElse(
//                clienteRetornado -> System.out.println(clienteRetornado.getIdentificador()),
//                () -> System.out.println("CLIENTE NAO EXISTE"));



//        if (optionalCliente.isPresent()) {
//            final Cliente clienteRetornado = optionalCliente.get();
//            System.out.println(clienteRetornado.getIdentificador());
//        } else {
//            System.out.println("CLIENTE NAO EXISTE");
//        }

//        optionalCliente.ifPresent(
//                clienteRetornado -> System.out.println(clienteRetornado.getIdentificador())
//                                 );

    }



    public static Optional<Cliente> findCliente(final List<Cliente> lista,
                                                final String nome) {
        for(final var cliente: lista) {
            if (cliente.getNome().equals(nome)) {
                return Optional.of(cliente);
            }
        }

        return Optional.empty();
    }







}
