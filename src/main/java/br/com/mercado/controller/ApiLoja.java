package br.com.mercado.controller;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.mercado.dao.ProdutoDao;
import br.com.mercado.entity.Produto;
import io.swagger.annotations.ApiOperation;

@SpringBootApplication
@RestController
@RequestMapping("/produto")
public class ApiLoja {
	

	@ApiOperation(value = "Salva um produto no catalogo de produtos")
	@RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json")
	public Produto salvar(@RequestBody Produto produto) {
		ProdutoDao produtoDao = new ProdutoDao();
		if (produtoDao.save(produto)) {
			return produto;
		} else {
			return null;
		}
	}

	@ApiOperation(value = "Busca um produto pelo id")
	@RequestMapping(value = "/buscar/{id}", method = RequestMethod.GET, produces = "application/json")
	public Produto buscar(@PathVariable("id") Integer id) {
		ProdutoDao produtoDao = new ProdutoDao();
		return produtoDao.find(id);
	}

	@ApiOperation(value = "Exclui um produto pelo id")
	@RequestMapping(value = "/excluir/{id}", method = RequestMethod.GET, produces = "application/json")
	public String excluir(@PathVariable("id") Integer id) {
		ProdutoDao produtoDao = new ProdutoDao();
		if (produtoDao.delete(id))
			return "Exclusão realizada com sucesso";
		else
			return "Erro ao excluir produto";
	}

	@ApiOperation(value = "Atualiza um produto")
	@RequestMapping(value = "/atualizar", method = RequestMethod.POST, produces = "application/json")
	public String atualizar(@RequestBody Produto produto) {
		ProdutoDao produtoDao = new ProdutoDao();
		if (produtoDao.update(produto)) {
			return "Item atualizado";
		} else {
			return "Erro na operação";
		}

	}

	@ApiOperation(value = "Busca todos os produtos")
	@RequestMapping(value = "/buscarTodos", method = RequestMethod.GET, produces = "application/json")
	public List<Produto> buscarTodos() {
		return new ProdutoDao().getAll();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApiLoja.class, args);
	}

}
