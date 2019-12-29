package com.ajeff.course;

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
import com.ajeff.course.domain.Produto;
import com.ajeff.course.domain.enums.TipoCliente;
import com.ajeff.course.repositories.CategoriaRepository;
import com.ajeff.course.repositories.CidadeRepository;
import com.ajeff.course.repositories.ClienteRepository;
import com.ajeff.course.repositories.EnderecoRepository;
import com.ajeff.course.repositories.EstadoRepository;
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

	}

}
