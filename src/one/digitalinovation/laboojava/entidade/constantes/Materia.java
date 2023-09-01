package one.digitalinovation.laboojava.entidade.constantes;

public enum Materia {
    M2(2),

    M5(5),

    M10(10);

    private int materias;

    /**
     * Construtor.
     * @param fator Valor por tipo que influencia no cálculo do frete.
     */
    private Materia(int materias) {
        this.materias = materias / 100;
    }

    public int getFator() {
        return materias;
    }
}
