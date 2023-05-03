package fungeye.cloud.domain.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "boxes")
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "box", fetch = FetchType.LAZY)
    private Set<Grow> grows = new LinkedHashSet<>();

    @OneToMany(mappedBy = "box")
    private Set<MeasuredCondition> measuredConditions = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Grow> getGrows() {
        return grows;
    }

    public void setGrows(Set<Grow> grows) {
        this.grows = grows;
    }

    public Set<MeasuredCondition> getMeasuredConditions() {
        return measuredConditions;
    }

    public void setMeasuredConditions(Set<MeasuredCondition> measuredConditions) {
        this.measuredConditions = measuredConditions;
    }
}