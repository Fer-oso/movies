package movies.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="producciones")
public class Produccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private int calificacion;
    private String fechaCreacion;
    
    @ManyToMany
    @JoinTable(name="personaje_produccion",joinColumns = @JoinColumn(name="id_produccion",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_personaje",referencedColumnName = "id"))
    @JsonIgnoreProperties("producciones")
    private List<Personaje> personajes = new ArrayList<>();
    
    @OneToOne
    @JoinTable(name="produccion_imagen",joinColumns = @JoinColumn(name="id_produccion",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_imagen",referencedColumnName = "id"))
    private Imagen imagen;
    
    @ManyToMany
    @JoinTable(name="produccion_genero",joinColumns = @JoinColumn(name="id_produccion",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_genero",referencedColumnName = "id"))
    private List<Genero> genero = new ArrayList<>();
}