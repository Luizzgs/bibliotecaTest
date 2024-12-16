import org.example.Aluno;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AlunoTest {

    private Aluno aluno;
    private final String ra;
    private final boolean esperado;

    public AlunoTest(String ra, boolean esperado) {
        this.ra = ra;
        this.esperado = esperado;
    }

    @Parameterized.Parameters
    public static Object[][] dados() {
        return new Object[][]{
            {"12345", true},  // RA válido
            {"10", false},    // RA inexistente
            {"", false},      // RA inválido (vazio)
            {"4", true}       // RA com débito
        };
    }

    @Before
    public void setUp() {
        aluno = new Aluno(ra);
    }

    @Test
    public void testVerificaAluno() {
        assertEquals("Validação de existência do aluno falhou para RA: " + ra, esperado, aluno.verificaAluno());
    }

    @Test
    public void testAlunoRAInvalidoLancaExcecao() {
        try {
            new Aluno(""); // RA inválido
            fail("Deveria ter lançado IllegalArgumentException para RA inválido");
        } catch (IllegalArgumentException e) {
            assertEquals("RA inválido", e.getMessage());
        }
    }
}
