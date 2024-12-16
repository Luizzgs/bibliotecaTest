import org.example.Controle;
import org.example.Aluno;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControleIntegracaoTest {

    private Controle controle;
    private static final String RA_VALIDO = "12345";
    private static final String RA_COM_DEBITO = "4";

    @Before
    public void setUp() {
        controle = new Controle();
        controle.cadastrarAluno(RA_VALIDO);
        controle.cadastrarAluno(RA_COM_DEBITO);
    }

    @Test
    public void testFluxoCompletoEmprestimoComSucesso() {
        controle.registrarDebito(RA_VALIDO, false); // Sem débito
        int[] prazos = {7, 14};

        boolean emprestimoRealizado = controle.emprestar(RA_VALIDO, prazos, 2);
        assertTrue("Fluxo completo deveria permitir empréstimo para aluno válido", emprestimoRealizado);
    }

    @Test
    public void testFluxoCompletoEmprestimoComFalha() {
        controle.registrarDebito(RA_COM_DEBITO, true); // Com débito
        int[] prazos = {3};

        boolean emprestimoRealizado = controle.emprestar(RA_COM_DEBITO, prazos, 1);
        assertFalse("Fluxo completo não deveria permitir empréstimo para aluno com débito", emprestimoRealizado);
    }

    @Test
    public void testFluxoCompletoEmprestimoAlunoNaoCadastrado() {
        String raNaoCadastrado = "99999";
        int[] prazos = {5, 10};

        boolean emprestimoRealizado = controle.emprestar(raNaoCadastrado, prazos, 2);
        assertFalse("Fluxo completo não deveria permitir empréstimo para aluno não cadastrado", emprestimoRealizado);
    }
}
