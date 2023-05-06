package fungeye.cloud.domain.dtos;

import fungeye.cloud.domain.enities.IdealCondition;


public class SpeciesCreationDTO {
    String name;

    IdealCondition idealCondition;

    public SpeciesCreationDTO(String name, IdealCondition condition) {
        this.name = name;
        this.idealCondition = condition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdealCondition getIdealCondition() {
        return idealCondition;
    }

    public void setIdealCondition(IdealCondition idealCondition) {
        this.idealCondition = idealCondition;
    }
    
    @Override
    public String toString() {
        return "SpeciesDTO{" +
                "name='" + name + '\'' +
                ", idealCondition=" + idealCondition +
                '}';
    }
}
