package com.ajeff.course;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ajeff.course.domain.Categoria;
import com.ajeff.course.domain.Cidade;
import com.ajeff.course.domain.Cliente;
import com.ajeff.course.domain.Endereco;
import com.ajeff.course.domain.Estado;
import com.ajeff.course.domain.ItemPedido;
import com.ajeff.course.domain.Pagamento;
import com.ajeff.course.domain.PagamentoComBoleto;
import com.ajeff.course.domain.PagamentoComCartao;
import com.ajeff.course.domain.Pedido;
import com.ajeff.course.domain.Produto;
import com.ajeff.course.domain.enums.EstadoPagamento;
import com.ajeff.course.domain.enums.TipoCliente;
import com.ajeff.course.repositories.CategoriaRepository;
import com.ajeff.course.repositories.CidadeRepository;
import com.ajeff.course.repositories.ClienteRepository;
import com.ajeff.course.repositories.EnderecoRepository;
import com.ajeff.course.repositories.EstadoRepository;
import com.ajeff.course.repositories.ItemPedidoRepository;
import com.ajeff.course.repositories.PagamentoRepository;
import com.ajeff.course.repositories.PedidoRepository;
import com.ajeff.course.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository repoCategoria;
	@Autowired
	private ProdutoRepository repoProduto;
	@Autowired
	private EstadoRepository repoEstado;
	@Autowired
	private CidadeRepository repoCidade;
	@Autowired
	private ClienteRepository repoCliente;
	@Autowired
	private EnderecoRepository repoEndereco;
	@Autowired
	private PedidoRepository repoPedido;
	@Autowired
	private PagamentoRepository repoPagamento;
	@Autowired
	private ItemPedidoRepository repoItemPedido;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria (null, "Informática");
		Categoria cat2 = new Categoria (null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		repoCategoria.saveAll(Arrays.asList(cat1,cat2));
		repoProduto.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		repoEstado.saveAll(Arrays.asList(e1,e2));
		
		Cidade c1 = new Cidade(null, "Uberlândia", e1);
		Cidade c2 = new Cidade(null, "São Paulo", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		repoCidade.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Araujo", "maria@gmail.com", "1248545478", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("154545151", "164656265"));
		Endereco end1 = new Endereco(null, "Rua A", "245", null, "Centro", "3154545", cli1, c1);
		Endereco end2 = new Endereco(null, "Rua B", "2154", null, "Centro", "12154545", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		repoCliente.save(cli1);
		repoEndereco.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), end1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), end2, cli1);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped1.setPagamento(pgto1);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		repoCliente.save(cli1);
		repoPedido.saveAll(Arrays.asList(ped1, ped2));
		repoPagamento.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		repoItemPedido.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
