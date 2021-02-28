package br.com.exames.entidade;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author estagio
 */

public class Exame implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String descricao;
	private Double valor;
	private List<Agendamento> consultasList;

	public Exame() {
	}

	public Exame(Integer id) {
		this.id = id;
	}

	public Exame(Integer id, String descricao, Double valor) {
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@XmlTransient
	public List<Agendamento> getConsultasList() {
		return consultasList;
	}

	public void setConsultasList(List<Agendamento> consultasList) {
		this.consultasList = consultasList;
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
		if (!(object instanceof Exame)) {
			return false;
		}
		Exame other = (Exame) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return descricao + " ";
	}

}
