package com.salesflow.configuration;

import com.salesflow.domain.port.*;
import com.salesflow.domain.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    AjustarEstoque ajustarEstoque(ProdutoRepository produtoRepository) {
        return new AjustarEstoque(produtoRepository);
    }
    @Bean
    AlterarMinhaSenhaUsuario alterarMinhaSenhaUsuario(UserRepository userRepository, SenhaEncoderPort encoder) {
        return new AlterarMinhaSenhaUsuario(userRepository, encoder);
    }
    @Bean
    AlterarSenhaUsuario alterarSenhaUsuario(UserRepository userRepository, SenhaEncoderPort encoder) {
        return new AlterarSenhaUsuario(userRepository, encoder);
    }
    @Bean
    AprovarOuRejeitarPedido aprovarOuRejeitarPedido(PedidoRepository pedidoRepository) {
        return new AprovarOuRejeitarPedido(pedidoRepository);
    }
    @Bean
    AtivarUsuario ativarUsuario(UserRepository userRepository) {
        return new AtivarUsuario(userRepository);
    }
    @Bean
    AtualizarUsuario atualizarUsuario(UserRepository userRepository) {
        return new AtualizarUsuario(userRepository);
    }
    @Bean
    AutenticarUsuario autenticarUsuario(UserRepository userRepository, SenhaEncoderPort encoder, JwtProviderPort jwt) {
        return new AutenticarUsuario(userRepository, encoder, jwt);
    }
    @Bean
    ConsultarMeuUsuario consultarMeuUsuario(UserRepository userRepository) {
        return new ConsultarMeuUsuario(userRepository);
    }
    @Bean
    CriarUsuario criarUsuario(UserRepository userRepository, SenhaEncoderPort encoder) {
        return new CriarUsuario(userRepository, encoder);
    }
    @Bean
    DesativarUsuario desativarUsuario(UserRepository userRepository) {
        return new DesativarUsuario(userRepository);
    }
    @Bean
    ExcluirProduto excluirProduto(ProdutoRepository produtoRepository) {
        return new ExcluirProduto(produtoRepository);
    }
    @Bean
    GerarRelatorioVendas gerarRelatorioVendas(ReportPort reportPort) {
        return new GerarRelatorioVendas(reportPort);
    }
    @Bean
    ListarMeusPedidos listarMeusPedidos(PedidoRepository pedidoRepository) {
        return new ListarMeusPedidos(pedidoRepository);
    }
    @Bean
    ListarProdutos listarProdutos(ProdutoRepository produtoRepository) {
        return new ListarProdutos(produtoRepository);
    }
    @Bean
    ListarTodosPedidos listarTodosPedidos(PedidoRepository pedidoRepository) {
        return new ListarTodosPedidos(pedidoRepository);
    }
    @Bean
    ListarUsuarios listarUsuarios(UserRepository userRepository) {
        return new ListarUsuarios(userRepository);
    }
    @Bean
    NotificarStatusPedidos notificarStatusPedidos(NotificacaoPort notificacaoPort) {
        return new NotificarStatusPedidos(notificacaoPort);
    }
    @Bean
    RanquearProdutosMaisVendidos ranquearProdutosMaisVendidos(ProdutoReportPort produtoReportPort) {
        return new RanquearProdutosMaisVendidos(produtoReportPort);
    }
    @Bean
    RenovarToken renovarToken(JwtProviderPort jwt) {
        return new RenovarToken(jwt);
    }
    @Bean
    RetornarPedidosPendentes retornarPedidosPendentes(PedidoRepository pedidoRepository) {
        return new RetornarPedidosPendentes(pedidoRepository);
    }
    @Bean
    SalvarPedido salvarPedido(UserRepository userRepository, ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        return new SalvarPedido(userRepository, produtoRepository, pedidoRepository);
    }
    @Bean
    SeriesTemporaisVendas seriesTemporaisVendas(SeriesTemporaisVendasPort seriesTemporaisVendasPort) {
        return new SeriesTemporaisVendas(seriesTemporaisVendasPort);
    }
    @Bean
    AlterarProduto alterarProduto(ProdutoRepository produtoRepository) {
        return new AlterarProduto(produtoRepository);
    }
    @Bean
    CancelarPedido cancelarPedido(PedidoRepository pedidoRepository) {
        return new CancelarPedido(pedidoRepository);
    }
    @Bean
    ConcluirPedido concluirPedido(PedidoRepository pedidoRepository, NotificacaoPort notificacaoPort) {
        return new ConcluirPedido(pedidoRepository, notificacaoPort);
    }
    @Bean
    DetalharProduto detalharProduto(ProdutoRepository produtoRepository) {
        return new DetalharProduto(produtoRepository);
    }
    @Bean
    ListarClienteMaisAtivos listarClienteMaisAtivos(ReportPort reportPort) {
        return new ListarClienteMaisAtivos(reportPort);
    }
}