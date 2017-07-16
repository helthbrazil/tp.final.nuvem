package br.com.mercado.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.mercado.connection.Conexao;
import br.com.mercado.entity.Produto;
import br.com.mercado.interfaces.IDao;

public class ProdutoDao implements IDao<Produto> {

	@Override
	public boolean save(Produto produto) {
		Connection con = Conexao.getConnection();
		try {
			String query = "INSERT INTO produto (nome, fabricante , embalagem, preco) Values (?,?,?,?) ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getFabricante());
			ps.setDouble(3, produto.getEmbalagem());
			ps.setDouble(4, produto.getPreco());

			ps.execute();
			con.commit();
			System.out.println("Produto inserido com sucesso");
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
				System.out.println("Rollback Efetuado");
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					con.close();
					System.out.println("Conexão fechada");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			return false;
		}

	}

	@Override
	public Produto find(Integer id) {
		String query = "SELECT * FROM produto WHERE id = ?";
		Connection con = Conexao.getConnection();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.first()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setEmbalagem(rs.getDouble("embalagem"));
				produto.setPreco(rs.getDouble("preco"));

				return produto;
			} else {
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public boolean delete(Integer id) {
		String query = "DELETE FROM produto WHERE id = ? ";
		Connection con = Conexao.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			boolean resultado = ps.execute();
			con.commit();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	public List<Produto> getAll() {
		List<Produto> lista = new ArrayList<Produto>();
		String query = "SELECT * FROM produto";
		Connection con = Conexao.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setFabricante(rs.getString("fabricante"));
				produto.setEmbalagem(rs.getDouble("embalagem"));
				produto.setPreco(rs.getDouble("preco"));

				lista.add(produto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return lista;
		}

	}

	@Override
	public boolean update(Produto produto) {
		Connection con = Conexao.getConnection();
		String query = "UPDATE produto SET nome = ?, fabricante = ?, embalagem = ?, preco = ? WHERE id = ? ";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, produto.getNome());
			ps.setString(2, produto.getFabricante());
			ps.setDouble(3, produto.getEmbalagem());
			ps.setDouble(4, produto.getPreco());
			ps.setInt(5, produto.getId());
			ps.execute();
			con.commit();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		return false;
		
	}

}
