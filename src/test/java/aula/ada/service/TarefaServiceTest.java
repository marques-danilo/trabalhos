package aula.ada.service;

import aula.ada.domain.PrioridadeTarefa;
import aula.ada.domain.StatusTarefa;
import aula.ada.dto.TarefaRequest;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TarefaServiceTest {

    @Inject
    TarefaService tarefaService;

    @Test
    void listar() {
        assertFalse(this.tarefaService.listar().isEmpty(), "o listar deve retornar uma lista de dados");
    }

    @Test
    void buscar() {
        assertThrows(TarefaNaoEncontradaException.class, () -> this.tarefaService.buscar(-1));
        try {
            this.tarefaService.buscar(1);
        } catch (TarefaNaoEncontradaException e) {
            fail("ao usar um id valido, deve retornar os dados corretamente");
        }
    }

    @Test
    @TestTransaction
    void criar() {
        TarefaRequest tarefaRequest = new TarefaRequest();
        tarefaRequest.setDescricao("teste criação");
        tarefaRequest.setPrioridade(PrioridadeTarefa.ALTA);
        tarefaRequest.setStatus(StatusTarefa.ABERTA);
        tarefaRequest.setTitulo("teste criação");
        try {
            assertNotNull(this.tarefaService.criar(tarefaRequest));
        } catch (Exception e) {
            fail("ao informar dados corretos, deve permitir o salvamento");
        }
    }

    @Test
    @TestTransaction
    void atualizar() {
        TarefaRequest tarefaRequest = new TarefaRequest();
        tarefaRequest.setDescricao("teste criação");
        tarefaRequest.setPrioridade(PrioridadeTarefa.ALTA);
        tarefaRequest.setStatus(StatusTarefa.ABERTA);
        tarefaRequest.setTitulo("teste criação");
        this.tarefaService.atualizar(1, tarefaRequest);
    }

    @Test
    @TestTransaction
    void excluir() {
        int qtd = this.tarefaService.listar().size();
        this.tarefaService.excluir(1);
        assertTrue(this.tarefaService.listar().size() < qtd, "Deve realizar a exclusão corretamente ao passar um ID valido");
    }
}