package fungeye.cloud.domain.enities;

import fungeye.cloud.domain.enities.users.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.LinkedHashSet;
import java.util.Objects;
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
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Box box = (Box) o;
        return getId() != null && Objects.equals(getId(), box.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}