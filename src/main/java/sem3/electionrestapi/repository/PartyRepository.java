package sem3.electionrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sem3.electionrestapi.entity.Party;

public interface PartyRepository extends JpaRepository<Party,Integer> {
}
