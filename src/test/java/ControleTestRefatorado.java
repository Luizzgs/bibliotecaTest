import org.example.Aluno;
import org.example.Controle;
import org.example.Livro;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ControleTestRefatorado {

    private Controle controle;

    @Before
    public void setUp() {
        controle = new Controle();
    }

    @Test
    public void testNaoEmprestaLivrosParaAlunoNaoVerificado() {
        Aluno aluno = new Aluno("10");
        assertFalse("Aluno não verificado, esperado false", aluno.verficaAluno());

        int[] prazos = {1, 2, 3};
        boolean retorno = controle.emprestar(aluno.getRA(), prazos, prazos.length);
        assertFalse("Deveria retornar false para aluno não verificado", retorno);
    }

    @Test
    public void testNaoEmprestaLivrosParaAlunoComDebito() {
        Aluno aluno = new Aluno("4");
        assertFalse("Aluno com débito, esperado false", aluno.verificaDebito());

        int[] prazos = {1, 2, 3};
        boolean retorno = controle.emprestar(aluno.getRA(), prazos, prazos.length);
        assertFalse("Deveria retornar false para aluno com débito", retorno);
    }

    @Test
    public void testEmprestimoLivros() {
        Aluno aluno = new Aluno("123");
        int[] prazos = {1, 3, 5, 6};
        boolean retorno = controle.emprestar(aluno.getRA(), prazos, prazos.length);
        assertTrue("Deveria retornar true para empréstimo bem-sucedido", retorno);
    }

    @Test
    public void testEmprestimoComLimiteExcedido() {
        Aluno aluno = new Aluno("456");
        int[] prazos = {1, 2, 3, 4, 5, 6};  // Excedendo limite
        boolean retorno = controle.emprestar(aluno.getRA(), prazos, prazos.length);
        assertFalse("Não deveria permitir empréstimo com limite excedido", retorno);
    }


    
}
