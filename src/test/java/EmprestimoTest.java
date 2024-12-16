package org.example;

import org.example.Emprestimo;
import org.example.Item;
import org.example.Livro;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EmprestimoTest {

    @Test
    public void setDataEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        Date novaData = new Date();

        emprestimo.setDataEmprestimo(novaData);

        System.out.println(emprestimo.getDataEmprestimo());
        assertEquals(novaData, emprestimo.getDataEmprestimo());
    }

    @Test
    public void calculaDataDevolucao1Item() {
        Emprestimo emprestimo = new Emprestimo();

        Livro l1 = new Livro(5);
        Item item = new Item(l1);

        emprestimo.item.add(item); // Usa a lista `item` diretamente

        Date dataDevolucao = emprestimo.CalculaDataDevolucao();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 6);

        Date dataEsperada = calendar.getTime();

        assertEquals(dataDevolucao.toString(), dataEsperada.toString());
    }

    @Test
    public void calculaDataDevolucao2Itens() {
        Emprestimo emprestimo = new Emprestimo();

        Livro l1 = new Livro(1);
        Livro l2 = new Livro(2);

        Item item1 = new Item(l1);
        Item item2 = new Item(l2);

        emprestimo.item.add(item1); // Usa a lista `item` diretamente
        emprestimo.item.add(item2);

        Date dataDevolucao = emprestimo.CalculaDataDevolucao();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 3);

        Date dataEsperada = calendar.getTime();

        assertEquals(dataDevolucao.toString(), dataEsperada.toString());
    }

    @Test
    public void calculaDataDevolucaoMaisItens() {
        Emprestimo emprestimo = new Emprestimo();

        Livro l1 = new Livro(1); // 2 dias
        Livro l2 = new Livro(2); // 3 dias + 2
        Livro l3 = new Livro(3); // 4 dias + 2

        Item item1 = new Item(l1);
        Item item2 = new Item(l2);
        Item item3 = new Item(l3);

        emprestimo.item.add(item1);
        emprestimo.item.add(item2);
        emprestimo.item.add(item3);

        Date dataDevolucao = emprestimo.CalculaDataDevolucao();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 6);

        Date dataEsperada = calendar.getTime();

        assertEquals(dataDevolucao.toString(), dataEsperada.toString());
    }
}
