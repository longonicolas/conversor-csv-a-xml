package csvtoxml.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ROW_INFO_BATCH")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Row {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "funcion")
    private String funcion;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "script")
    private String script;

    @Column(name = "prueba")
    private String prueba;

    @Column(name = "resultado")
    private String resultado;

    @Column(name = "formato")
    private String formato;

    @Column(name = "ambiente")
    private String ambiente;

    @Column(name = "evidencia")
    private String evidencia;


    public Row(String funcion, String tipo, String script, String prueba, String resultado, String formato, String ambiente, String evidencia) {
        this.funcion = funcion;
        this.tipo = tipo;
        this.script = script;
        this.prueba = prueba;
        this.resultado = resultado;
        this.formato = formato;
        this.ambiente = ambiente;
        this.evidencia = evidencia;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFuncion() {
        return funcion;
    }

    public String getPrueba() {
        return prueba;
    }

    public String getScript() {
        return script;
    }

    public String getResultado() {
        return resultado;
    }

    public String getFormato() {
        return formato;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public String getEvidencia() {
        return evidencia;
    }
}
