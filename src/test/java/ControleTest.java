
import org.example.Aluno;
import org.example.Controle;
import org.example.Livro;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ControleTest {

    @Test
    public void testNaoEmprestaLivrosParaAlunoNaoVerificado() {
        String ra = "10";
        Aluno aluno = new Aluno(ra);

        assertFalse(aluno.verficaAluno());

        int[] prazos = {1,2,3};
        Controle c = new Controle();
        boolean retorno = c.emprestar(ra, prazos, prazos.length);
        assertFalse(retorno);
    }


    @Test
    public void testNaoEmprestaLivrosParaAlunoComDebito() {
        String ra = "4";
        Aluno aluno = new Aluno(ra);

        assertFalse(aluno.verificaDebito());

        int[] prazos = {1,2,3};
        Controle c = new Controle();
        boolean retorno = c.emprestar(ra, prazos, prazos.length);
        assertFalse(retorno);
    }


    @Test
    public void testEmprestimoLivros() {
        String ra = "123";
        int[] prazos = {1,3,5,6};
        Controle c = new Controle();
        boolean retorno = c.emprestar(ra, prazos, prazos.length);
        assertTrue(retorno);
    }
}
