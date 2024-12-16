import org.example.Controle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControleTest {

    private Controle controle;
    private static final String RA_VALIDO = "12345";
    private static final String RA_INEXISTENTE = "10";
    private static final String RA_COM_DEBITO = "4";

    @Before
    public void setUp() {
        controle = new Controle();
    }

    @Test
    public void testEmprestarFalhaParaAlunoInexistente() {
        int[] prazos = {5, 7};
        boolean resultado = controle.emprestar(RA_INEXISTENTE, prazos, 1);
        assertFalse("Empréstimo deveria falhar para aluno inexistente", resultado);
    }

    @Test
    public void testEmprestarFalhaParaAlunoComDebito() {
        int[] prazos = {3, 4};
        boolean resultado = controle.emprestar(RA_COM_DEBITO, prazos, 2);
        assertFalse("Empréstimo deveria falhar para aluno com débito", resultado);
    }

    @Test
    public void testEmprestarSucessoParaAlunoValido() {
        int[] prazos = {3, 5};
        boolean resultado = controle.emprestar(RA_VALIDO, prazos, 2);
        assertTrue("Empréstimo deveria ser permitido para aluno válido", resultado);
    }
}
