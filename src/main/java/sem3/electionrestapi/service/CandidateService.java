package sem3.electionrestapi.service;

import sem3.electionrestapi.payload.CandidateDto;
import sem3.electionrestapi.payload.PartyDto;

import java.util.List;

public interface CandidateService {

    CandidateDto createCandidate(int partyId, CandidateDto candidateDto);

    List<CandidateDto> getCandidatesByPartyId(int partyId);

    CandidateDto updateCandidate(int partyId, int candidateId, CandidateDto candidateRequest);

    void deleteCandidate(int partyId, int candidateId);
}
