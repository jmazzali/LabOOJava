package one.digitalinovation.laboojava.entidade.constantes;

public enum Materia {
    M2(2),

    M5(5),

    M10(10);

    private double materias;

    /**
     * Construtor.
     * @param fator Valor por tipo que influencia no cálculo do frete.
     */
    private Materia(double materias) {
        this.materias = materias / 100;
    }

    public double getFator() {
        return materias;
    }
}
