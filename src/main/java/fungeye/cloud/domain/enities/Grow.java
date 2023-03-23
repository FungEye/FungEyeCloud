package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "grows")
public class Grow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "date_started", nullable = false)
    private LocalDate dateStarted;

    @Size(max = 255)
    @NotNull
    @Column(name = "development_stage", nullable = false)
    private String developmentStage;

    @Column(name = "is_active")
    private Boolean isActive;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "box_id", nullable = false)
    private Box box;

    @OneToMany(mappedBy = "grow")
    private Set<Mushroom> mushrooms = new LinkedHashSet<>();

}