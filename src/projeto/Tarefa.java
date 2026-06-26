package projeto;

public class Tarefa {
	///Atributos da nossa tarefa
	private String titulo;
	private String descricao;
	private String status;
	
	///Método construção da nossa classe
	public Tarefa(String Titulo, String Descrição) {
		this.titulo = Titulo;
		this.descricao = descricao;
		this.status = "Pendente";
		
		
		
	}
	public String getTitulo() {
		return titulo;
	}public String getDescricao() {
		return descricao;
	}
	public String getStatus() {
		return status;
	
	}
	public void concluir() {
		this.status = "Concluída";
	}
	@Override
	public String toString() {
		return titulo + "-"+ status;
		
	}

}
