package br.com.exames.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import br.com.exames.dao.AgendamentoDAO;
import br.com.exames.entidade.Agendamento;

@SuppressWarnings("serial")
public class AgendamentoAction extends ActionSupport {
	private Agendamento agendamento;
	private List<Agendamento> agendamentos;
	private AgendamentoDAO dao;

	public String salvarAgendamento() throws Exception {
		dao = new AgendamentoDAO();
		dao.cadastrar(agendamento);
		return "Agendamento salvo com sucesso";
	}

	public String listarAgendamentos() throws Exception {
		dao = new AgendamentoDAO();
		agendamentos = dao.getAgendamentos();
		return "agendamentos";
	}

	public String removerAgendamento() throws Exception {
		dao = new AgendamentoDAO();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);
		dao.remove(Integer.parseInt(request.getParameter("id")));
		return "Agendamento removido com sucesso";
	}

	public String editarAgendamentoId() throws Exception {
		dao = new AgendamentoDAO();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);

		agendamento = dao.agendamentoId((Integer.parseInt(request.getParameter("id"))));

		return "Preparando";

	}
	
	public String editarAgendamentoPaciente() throws Exception {
		dao = new AgendamentoDAO();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(ServletActionContext.HTTP_REQUEST);

		agendamento = dao.agendamentoPaciente((String.valueOf(request.getParameter("nome"))));

		return "Preparando";

	}

	public String editarAgendamento() throws Exception {
		dao = new AgendamentoDAO();
		dao.editar(agendamento);
		return "Agendamento editado com sucesso";
	}
	
	

	public Agendamento getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(Agendamento agendamento) {
		this.agendamento = agendamento;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public String execute() throws Exception {
		if (agendamento != null) {
			return "success";
		} else {
			return "success";
		}
	}

	public void validate() {
		if (agendamento.getDataNascimento().equals(null)) {
			addFieldError("agendamento.getDataNascimento()", "A data da consulta é obrigatório!.");
		}

		if (agendamento.getPaciente().getId().equals(null)) {
			addFieldError("agendamento.getPaciente().getId()", "O Paciente é obrigatório!.");
		}

		if (agendamento.getExame().equals(null)) {
			addFieldError("agendamento.getExame().getId()", "O Exame é obrigatório");
		}
	}
}
