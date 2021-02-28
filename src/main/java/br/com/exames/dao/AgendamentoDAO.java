package br.com.exames.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.exames.connection.ConexaoBD;
import br.com.exames.entidade.Agendamento;

public class AgendamentoDAO {
	private ConexaoBD conexao;

	public AgendamentoDAO() {
		conexao.getConnection();
	}

	public void cadastrar(Agendamento agendamento) throws Exception {
		PreparedStatement statement = conexao.getConnection()
				.prepareStatement("insert into agendamento (data, exame, paciente) " + "values (?,?,?)");

		try {
			statement.setString(1, agendamento.getDataNascimento().toString());
			statement.setInt(2, agendamento.getExame().getId());
			statement.setInt(3, agendamento.getPaciente().getId());

			statement.execute();
			statement.close();
			conexao.getConnection().close();
		} catch (SQLException e) {
			System.out.println("ERRO NO MÈTODO CADASTRAR" + "  "

					+ e.getMessage());
		}
	}

	public List<Agendamento> getAgendamentos() throws Exception {

		PreparedStatement statement = conexao.getConnection().prepareStatement("SELECT * FROM Agendamento");
		ResultSet rs = statement.executeQuery();
		List<Agendamento> agendamentos = new ArrayList<Agendamento>();
		Agendamento agendamento;

		while (rs.next()) {
			agendamento = new Agendamento();
			agendamento.setId(rs.getInt("id"));
			agendamento.setDataNascimento(rs.getDate("dataNascimento"));
			agendamento.getExame().setId(rs.getInt("exame"));
			agendamento.getPaciente().setId(rs.getInt("paciente"));
			
			agendamentos.add(agendamento);
		}

		rs.close();
		statement.close();
		conexao.getConnection().close();

		return agendamentos;
	}

	public void remove(int id) throws Exception {
		PreparedStatement statement = conexao.getConnection().prepareStatement("DELETE FROM Agendamento WHERE ID=?");
		statement.setInt(1, id);
		statement.execute();
		statement.close();
	}

	public Agendamento editar(Agendamento agendamento) throws Exception {
		PreparedStatement statement = conexao.getConnection()
				.prepareStatement("UPDATE Agendamento SET DATANASCIMENTO=?,EXAME=?, PACIENTE=? WHERE ID=?");
		statement.setDate(1, (java.sql.Date) agendamento.getDataNascimento());
		statement.setInt(2, agendamento.getExame().getId());
		statement.setInt(3, agendamento.getPaciente().getId());

		statement.execute();
		statement.close();
		conexao.getConnection().close();
		return agendamento;
	}

	public Agendamento agendamentoId(int id) throws SQLException {

		Agendamento agendamento = new Agendamento();
		PreparedStatement statement = conexao.getConnection().prepareStatement("SELECT * FROM Agendamento WHERE ID=?");
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			agendamento.setId(rs.getInt("id"));
			agendamento.setDataNascimento(rs.getDate("dataNascimento"));
			agendamento.getExame().setId(rs.getInt("exame"));
			agendamento.getPaciente().setId(rs.getInt("paciente"));
		}

		rs.close();
		statement.close();
		conexao.getConnection().close();

		return agendamento;

	}
	
	public Agendamento agendamentoPaciente(String nome) throws SQLException {

		Agendamento agendamento = new Agendamento();
		PreparedStatement statement = conexao.getConnection().prepareStatement("SELECT * FROM Agendamento a WHERE a.paciente.nome =?");
		statement.setString(1, nome);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			agendamento.setId(rs.getInt("id"));
			agendamento.setDataNascimento(rs.getDate("dataNascimento"));
			agendamento.getExame().setId(rs.getInt("exame"));
			agendamento.getPaciente().setId(rs.getInt("paciente"));
		}

		rs.close();
		statement.close();
		conexao.getConnection().close();

		return agendamento;

	}
}
