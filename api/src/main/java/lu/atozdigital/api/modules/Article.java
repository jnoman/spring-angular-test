package lu.atozdigital.api.modules;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@NotNull
    private String name;
	@NotNull
    private double price;
	@NotNull
	private String picture;
}
