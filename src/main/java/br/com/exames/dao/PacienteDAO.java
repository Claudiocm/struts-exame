package br.com.exames.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.exames.connection.ConexaoBD;
import br.com.exames.entidade.Paciente;

public class PacienteDAO {
	private ConexaoBD conexao;

	public PacienteDAO() throws Exception {
		conexao.getConnection();
	}

	public void cadastrar(Paciente paciente) throws Exception {
		PreparedStatement statement = conexao.getConnection()
				.prepareStatement("insert into paciente (nome, dataNascimento, endereco, telefone, sexo) "
						+ "values (?,?,?, ?,?)");

		try {
			statement.setString(1, paciente.getNome());
			statement.setDate(2, (Date) paciente.getDataNascimento());
			statement.setString(3, paciente.getEndereco());
			statement.setString(4, paciente.getTelefone());
			statement.setString(5, paciente.getSexo());
			statement.execute();
			statement.close();
			conexao.getConnection().close();
		} catch (SQLException e) {
			System.out.println("ERRO NO MÈTODO CADASTRAR" + "  "

					+ e.getMessage());
		}
	}

	public List<Paciente> getPacientes() throws Exception {
		PreparedStatement statement = conexao.getConnection().prepareStatement("SELECT * FROM paciente");
		ResultSet rs = statement.executeQuery();
		List<Paciente> pacientes = new ArrayList<Paciente>();
		Paciente paciente;

		while (rs.next()) {
			paciente = new Paciente();
			paciente.setId(rs.getInt("id"));
			paciente.setNome(rs.getString("nome"));
			paciente.setDataNascimento(rs.getDate("dataNascimento"));
			paciente.setEndereco(rs.getString("endereco"));
			paciente.setSexo(rs.getString("sexo"));
			paciente.setTelefone(rs.getString("telefone"));
			pacientes.add(paciente);
		}

		rs.close();
		statement.close();
		conexao.getConnection().close();

		return pacientes;
	}

	public void remove(int id) throws Exception {
		PreparedStatement statement = conexao.getConnection().prepareStatement("DELETE FROM Paciente WHERE ID=?");
		statement.setInt(1, id);
		statement.execute();
		statement.close();
	}

	public Paciente editar(Paciente paciente) throws Exception {
		PreparedStatement statement = conexao.getConnection().prepareStatement(
				"UPDATE paciente SET NOME=?,DATANASCIMENTO=?,SEXO=?, TELEFONE=?, ENDERECO=? WHERE ID=?");
		statement.setString(1, paciente.getNome());
		statement.setDate(2, (Date) paciente.getDataNascimento());
		statement.setString(3, paciente.getEndereco());
		statement.setString(4, paciente.getTelefone());
		statement.setString(5, paciente.getSexo());
		statement.setInt(5, paciente.getId());
		statement.execute();
		statement.close();
		conexao.getConnection().close();
		return paciente;
	}

	public Paciente pacienteId(int id) throws SQLException {

		Paciente paciente = new Paciente();
		PreparedStatement statement = conexao.getConnection().prepareStatement("SELECT * FROM Paciente WHERE ID=?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			paciente.setId(rs.getInt("id"));
			paciente.setNome(rs.getString("nome"));
			paciente.setDataNascimento(rs.getDate("dataNascimento"));
			paciente.setEndereco(rs.getString("endereco"));
			paciente.setSexo(rs.getString("sexo"));
			paciente.setTelefone(rs.getString("telefone"));
		}

		rs.close();
		statement.close();
		conexao.getConnection().close();

		return paciente;

	}

}
