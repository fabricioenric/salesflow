package com.salesflow.domain.usecases;

import com.salesflow.adapter.dto.Item;
import com.salesflow.domain.model.Papel;
import com.salesflow.domain.model.Pedido;
import com.salesflow.domain.model.Produto;
import com.salesflow.domain.model.User;
import com.salesflow.domain.port.PedidoRepository;
import com.salesflow.domain.port.ProdutoRepository;
import com.salesflow.domain.port.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalvarPedidoTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private SalvarPedido salvarPedido;

    private User cliente;
    private Produto produto;

    @BeforeEach
    void setUp() {
        cliente = new User(1L, "cliente", "senha", Papel.CLIENTE, true);
        produto = new Produto(1L, "Produto Teste", 100.0, 10);
    }

    @Test
    @DisplayName("Deve salvar um pedido com sucesso")
    void shouldSaveOrderSuccessfully() {
        // Arrange
        Item itemPedido = new Item(1L, 2);
        when(userRepository.findByUsuario("cliente")).thenReturn(cliente);
        when(produtoRepository.findById(1L)).thenReturn(produto);

        // Act
        Pedido pedidoSalvo = salvarPedido.execute("cliente", List.of(itemPedido));

        // Assert
        // Verify that the stock was decreased
        assertEquals(8, produto.getEstoque());

        // Capture the Pedido object passed to the repository
        ArgumentCaptor<Pedido> pedidoCaptor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidoRepository, times(1)).salvar(pedidoCaptor.capture());
        Pedido pedidoCapturado = pedidoCaptor.getValue();

        assertEquals("cliente", pedidoCapturado.getCliente().getUsuario());
        assertEquals(1, pedidoCapturado.getItens().size());
        assertEquals(100.0, pedidoCapturado.getItens().get(0).getPrecoUnitario());
        assertEquals(2, pedidoCapturado.getItens().get(0).getQuantidade());
        assertEquals(200.0, pedidoCapturado.valorTotal());

        // Verify that the product repository was also called to save the new stock
        verify(produtoRepository, times(1)).salvar(produto);
    }

    @Test
    @DisplayName("Deve lançar IllegalStateException por estoque insuficiente")
    void shouldThrowExceptionForInsufficientStock() {
        // Arrange
        Item itemPedido = new Item(1L, 11); // Requesting more than available
        when(userRepository.findByUsuario("cliente")).thenReturn(cliente);
        when(produtoRepository.findById(1L)).thenReturn(produto);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            salvarPedido.execute("cliente", List.of(itemPedido));
        });

        // Verify that the order was not saved
        verify(pedidoRepository, never()).salvar(any(Pedido.class));
    }
}