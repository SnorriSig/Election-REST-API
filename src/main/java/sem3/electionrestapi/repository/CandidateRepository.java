package sem3.electionrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.electionrestapi.entity.Candidate;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    List<Candidate> findByPartyId(int partyId);
}
