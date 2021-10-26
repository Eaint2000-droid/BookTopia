package dailyForm;

import javax.persistence.*;
import com.collab.g5.demo.companies.Company;
import com.collab.g5.demo.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DailyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int FID;

    @NotNull
    private LocalDate dateTime;

    @NotNull(message = "Temperature should not be null")
    private float temperature;

    private boolean symptoms;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_useremail",foreignKey = @ForeignKey(name="fk1_DailyForm"))
    private User user;


}
