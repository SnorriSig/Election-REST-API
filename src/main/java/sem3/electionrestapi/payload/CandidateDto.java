package sem3.electionrestapi.payload;

import lombok.Data;

@Data
public class CandidateDto {
    private int id;
    private String firstName;
    private String lastName;
}
