package br.com.exames.entidade;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estagio
 */

public class Agendamento implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dataNascimento;
	private Paciente paciente;
	private Exame exame;

	public Agendamento() {
	}

	public Agendamento(Integer id) {
		this.id = id;
	}

	public Agendamento(Integer id, Date dataNascimento) {
		this.id = id;
		this.dataNascimento = dataNascimento;
		paciente = new Paciente(paciente.getId());
		exame = new Exame(exame.getId());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Agendamento)) {
			return false;
		}
		Agendamento other = (Agendamento) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return id + " ";
	}

}
