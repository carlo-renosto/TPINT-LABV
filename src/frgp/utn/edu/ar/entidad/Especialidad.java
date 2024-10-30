package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="Especialidades")
public class Especialidad implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name="Id")
    private String id;
    
    @Column(name="Nombre")
    private String nombre;
    
    @OneToMany(mappedBy="especialidad")
    private Set<Medico> medicos;
    
	@Column(name="estado")
	private boolean estado;
    
    public Especialidad() {
    }
    
    public Especialidad(String id) {
    	this.id = id;
    	this.estado = true;
    }
    
    public Especialidad(String id, String nombre) {
    	this.id = id;
    	this.nombre = nombre;
    	this.estado = true;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(Set<Medico> medicos) {
        this.medicos = medicos;
    }

    public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
    public String toString() {
        return "Especialidad [id=" + id + ", nombre=" + nombre + "]";
    }
}
