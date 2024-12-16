package org.example;

import org.example.Emprestimo;
import org.example.Item;
import org.example.Livro;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class EmprestimoTestRefatorado {

    @Test
    public void setDataEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        Date novaData = new Date();
        emprestimo.setDataEmprestimo(novaData);
        assertEquals("Data de empréstimo não corresponde à data esperada.",
                novaData, emprestimo.getDataEmprestimo());
    }

    @Test
    public void calculaDataDevolucao() {
        Emprestimo emprestimo = new Emprestimo();

        List<Item> itens = new ArrayList<>();
        itens.add(new Item(new Livro(1)));  // 2 dias
        itens.add(new Item(new Livro(2)));  // 3 dias + 2
        itens.add(new Item(new Livro(3)));  // 4 dias + 2

        emprestimo.setItens(itens);

        Date dataDevolucao = emprestimo.CalculaDataDevolucao();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 6);  // Soma total dos dias: 6

        Date dataEsperada = calendar.getTime();
        assertEquals("A data de devolução calculada está incorreta.",
                dataDevolucao.toString(), dataEsperada.toString());
    }
    @Test
    public void calculaDataDevolucaoComItensVariados() {
        int[][] livrosData = {{1, 2}, {5, 6}, {3, 1}};
        for (int[] livroData : livrosData) {
            Emprestimo emprestimo = new Emprestimo();
            List<Item> itens = new ArrayList<>();


            for (int livro : livroData) {
                itens.add(new Item(new Livro(livro)));
            }
            emprestimo.setItens(itens);


            Date dataDevolucao = emprestimo.CalculaDataDevolucao();

            int prazoMaximo = 0;
            for (int i = 0; i < livroData.length; i++) {
                int prazo = livroData[i];
                if (livroData.length > 2 && i > 1) {
                    prazo += 2;  // Adiciona 2 dias aos livros após o segundo
                }
                if (prazo > prazoMaximo) {
                    prazoMaximo = prazo ;  // Seleciona o maior prazo
                }
            }

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, prazoMaximo + 1);

            Date dataEsperada = calendar.getTime();

            assertEquals("A data de devolução calculada está incorreta para os livros " + Arrays.toString(livroData),
                    dataDevolucao.toString(), dataEsperada.toString());
        }
    }


}
