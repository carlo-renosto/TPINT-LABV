package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Turnos")
public class Turno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="medico_id")
    private Medico medico;

    @ManyToOne
    @JoinColumn(name="paciente_id")
    private Paciente paciente;

    @Column(name="dia")
    private String dia;
    
    @Column(name="diaFecha")
    private String diaFecha;

    @Column(name="hora_inicio")
    private int horaInicio;
    
    @Column(name="hora_fin")
    private int horaFin;

    @Column(name="estado_turno")
    private String estadoTurno;
    
    @Column(name="observacion")
    private String observacion;
    
	@Column(name="estado")
	private boolean estado;

    public Turno() {
    	
    }
    
    public Turno(String medico, String paciente, int horaInicio, int horaFin) {
    	this.medico = new Medico(Integer.parseInt(medico));
    	this.paciente = new Paciente(paciente);
    	this.horaInicio = horaInicio;
    	this.horaFin = horaFin;
    	this.estado = true;
    }
    
    public Turno(String medico, String paciente, String dia, int horaInicio, int horaFin) {
    	this.medico = new Medico(Integer.parseInt(medico));
    	this.paciente = new Paciente(paciente);
    	this.dia = dia;
    	this.horaInicio = horaInicio;
    	this.horaFin = horaFin;
    	this.estado = true;
    }
    
    public Turno(Long id, String dia, int horaInicio, int horaFin, String observacion, String estadoTurno) {
    	this.id = id;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.observacion = observacion;
        this.estadoTurno = estadoTurno;
        this.estado = true;
    }
    
    public Turno(Long id, Medico medico, Paciente paciente, String dia, int horaInicio, int horaFin, String observacion, String estadoTurno) {
    	this.id = id;
        this.medico = medico;
        this.paciente = paciente;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.observacion = observacion;
        this.estadoTurno = estadoTurno;
        this.estado = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getDiaFecha() {
		return diaFecha;
	}

	public void setDiaFecha(String diaFecha) {
		this.diaFecha = diaFecha;
	}

	public int getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public int getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(int horaFin) {
		this.horaFin = horaFin;
	}

	public String getEstadoTurno() {
        return estadoTurno;
    }

    public void setEstadoTurno(String estadoTurno) {
        this.estadoTurno = estadoTurno;
    }
    
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public void set(String medico, String paciente, String dia, int horaInicio, int horaFin, String observacion, String estadoTurno) {
		this.medico = new Medico(Integer.parseInt(medico));
    	this.paciente = new Paciente(paciente);
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.observacion = observacion;
        this.estadoTurno = estadoTurno;
        this.estado = true;
    }
	
	public void limpiar() {
    	this.id = (long) 0;
        this.medico = null;
        this.paciente = null;
        this.dia = "";
        this.horaInicio = 0;
        this.horaFin = 0;
        this.observacion = "";
        this.estadoTurno = "";
        this.estado = false;
    }

	@Override
    public String toString() {
        return "Turno [id=" + id + ", medico=" + medico + ", paciente=" + paciente + ", dia=" + dia + ", hora="
                + horaInicio + "-" + horaFin + ", observacion=" + observacion + ", estado=" + estado + "]";
    }
}
