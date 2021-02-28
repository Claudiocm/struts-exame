package br.com.exames.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import br.com.exames.dao.PacienteDAO;
import br.com.exames.entidade.Paciente;

public class PacienteAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private List<Paciente> pacientes;
	private PacienteDAO dao;

	@Action("salvar")
	public String salvarPaciente() throws Exception {
		dao = new PacienteDAO();
		dao.cadastrar(paciente);
		return "Paciente salvo com sucesso";
	}

	@Action("listarPaciente")
	public String listarPacientes() throws Exception {
		dao = new PacienteDAO();
		pacientes = dao.getPacientes();
		return "paciente";
	}

	@Action("removerPaciente")
	public String removerPaciente() throws Exception {
		dao = new PacienteDAO();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		dao.remove(Integer.parseInt(request.getParameter("id")));
		return "Paciente removido com sucesso";
	}

	@Action("editarId")
	public String editarId() throws Exception {

		dao = new PacienteDAO();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);

		paciente = dao.pacienteId((Integer.parseInt(request.getParameter("id"))));

		return "Preparando";

	}

	@Action("editarPaciente")
	public String editarPaciente() throws Exception {
		dao = new PacienteDAO();
		dao.editar(paciente);
		return "Paciente editado com sucesso";
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public String execute() throws Exception {
		if (paciente != null) {
			return "success";
		} else {
			return "fail";
		}
	}

	public void validate() {
		if (paciente.getNome().length() == 0) {
			addFieldError("paciente.getPctNome()", "O nome é obrigatório!.");
		}

		if (paciente.getDataNascimento().equals(null)) {
			addFieldError("paciente.getPctNascimento()", "A data de nascimento é obrigatório!.");
		}

		if (paciente.getSexo().length() == 0) {
			addFieldError("paciente.getPctSexo()", "O Sexo do paciente é obrigatório");
		}

		if (paciente.getTelefone().length() == 0) {
			addFieldError("paciente.getPctFone()", "O Telefone é obrigatório!");
		}
	}
}
