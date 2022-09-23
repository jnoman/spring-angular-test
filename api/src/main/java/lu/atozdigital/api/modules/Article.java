package lu.atozdigital.api.modules;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
	@Column(nullable = false)
    private String name;
	@Column(nullable = false)
    private double price;
	@Lob
    private byte [] picture ;
}
