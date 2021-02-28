package br.com.exames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.exames.connection.ConexaoBD;
import br.com.exames.entidade.Exame;

public class ExameDAO {
	private ConexaoBD conexao;

	public ExameDAO() throws Exception {
		conexao.getConnection();
	}

	public void cadastrar(Exame exame) throws Exception {
		PreparedStatement statement = conexao.getConnection()
				.prepareStatement("insert into exame (descricao, valor) " + "values (?,?)");

		try {
			statement.setString(1, exame.getDescricao());
			statement.setDouble(2, exame.getValor());
			statement.execute();
			statement.close();
			conexao.getConnection().close();
		} catch (SQLException e) {
			System.out.println("ERRO NO MÈTODO CADASTRAR" + "  "

					+ e.getMessage());
		}
	}

	public List<Exame> getExames() throws Exception {

		PreparedStatement statement = conexao.getConnection().prepareStatement("SELECT * FROM Exame");
		ResultSet rs = statement.executeQuery();
		List<Exame> exames = new ArrayList<Exame>();
		Exame exame;

		while (rs.next()) {
			exame = new Exame();
			exame.setId(rs.getInt("id"));
			exame.setDescricao(rs.getString("descricao"));
			exame.setValor(rs.getDouble("valor"));
			exames.add(exame);
		}

		rs.close();
		statement.close();
		conexao.getConnection().close();

		return exames;
	}

	public void remove(int id) throws Exception {
		PreparedStatement statement = conexao.getConnection().prepareStatement("DELETE FROM Exame WHERE ID=?");
		statement.setInt(1, id);
		statement.execute();
		statement.close();
	}

	public Exame editar(Exame exame) throws Exception {
		PreparedStatement statement = conexao.getConnection()
				.prepareStatement("UPDATE exame SET DESCRICAO=?,VALOR=? WHERE ID=?");
		statement.setString(1, exame.getDescricao());
		statement.setDouble(2, exame.getValor());

		statement.execute();
		statement.close();
		conexao.getConnection().close();
		return exame;
	}

	public Exame exameId(int id) throws SQLException {

		Exame exame = new Exame();
		PreparedStatement statement = conexao.getConnection().prepareStatement("SELECT * FROM Exame WHERE ID=?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			exame.setId(rs.getInt("id"));
			exame.setDescricao(rs.getString("descricao"));
		}

		rs.close();
		statement.close();
		conexao.getConnection().close();

		return exame;

	}
}
