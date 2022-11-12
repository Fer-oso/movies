package movies.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="personajes")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private int edad;
    private int peso;
    private String historia;
    
    
    @ManyToMany
    @JsonIgnoreProperties("personajes")
    @JoinTable(name="personaje_produccion",joinColumns = @JoinColumn(name="id_personaje",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_produccion",referencedColumnName = "id"))
    private List<Produccion> producciones = new ArrayList<>();
    
    @OneToOne
    @JoinTable(name="personaje_imagen",joinColumns = @JoinColumn(name="id_perosnaje",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="id_image",referencedColumnName = "id"))
    private Imagen imagen;
}