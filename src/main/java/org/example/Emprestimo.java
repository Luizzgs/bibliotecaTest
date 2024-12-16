package org.example;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Emprestimo {

	// Removida a propriedade não utilizada
	List<Item> item = new ArrayList<>();
	Date dataEmprestimo = new Date();
	Date dataPrevista = new Date();
	Date data_aux = new Date();
	String nome;

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public boolean emprestar(List<Livro> livros) {
		for (Livro livro : livros) {
			item.add(new Item(livro));
		}

		CalculaDataDevolucao();
		System.out.print("\nNúmero de Livros Emprestados: " + livros.size());
		System.out.print("\nData de Empréstimo: " + this.dataEmprestimo);
		System.out.print("\nData de Devolução: " + this.dataPrevista);
		return true;
	}

	public Date CalculaDataDevolucao() {
		Date date = new Date();

		for (Item it : item) {
			data_aux = it.calculaDataDevolucao(date);
			if (dataPrevista.compareTo(data_aux) < 0)
				dataPrevista = data_aux;
		}

		if (item.size() > 2) {
			int tam = item.size() - 2;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataPrevista);
			calendar.add(Calendar.DATE, tam * 2);
			dataPrevista = calendar.getTime();
		}

		for (Item it : item) {
			it.setDataDevolucao(dataPrevista);
		}

		return dataPrevista;
	}

	public void setItens(List<Item> item) {
		this.item = item;
	}
}
