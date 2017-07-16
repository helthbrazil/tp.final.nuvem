package br.com.mercado.entity;

public class Produto {
	private Integer id;
	private String nome;
	private String fabricante;
	private Double embalagem;
	private Double preco;

	public Produto() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Double getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(Double embalagem) {
		this.embalagem = embalagem;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
