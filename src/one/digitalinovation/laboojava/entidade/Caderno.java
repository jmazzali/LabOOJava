package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Materia;

public class Caderno extends Produto {
	/**
	 * Quantidade de matérias do caderno
	 */
	private Materia materia;

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "Caderno [materia=" + materia + "]";
	}
	
	
	
}
