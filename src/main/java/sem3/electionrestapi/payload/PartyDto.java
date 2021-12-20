package sem3.electionrestapi.payload;

import lombok.Data;
import sem3.electionrestapi.entity.Candidate;

import javax.persistence.Column;
import java.util.Set;

@Data
public class PartyDto {
    private int id;
    private String name;
    private int vote;

    //private Set<CandidateDto> candidateDtos;
}
