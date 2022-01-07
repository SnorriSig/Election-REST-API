package sem3.electionrestapi.service;

import sem3.electionrestapi.payload.CandidateDto;
import sem3.electionrestapi.payload.PartyDto;

import java.util.List;

public interface CandidateService {

    CandidateDto createCandidate(int partyId, CandidateDto candidateDto);

    List<CandidateDto> getAllCandidates();

    CandidateDto getCandidateByPartyIdAndCandidateId(int partyId, int candidateId);

    List<CandidateDto> getCandidatesByPartyId(int partyId);

    CandidateDto updateCandidate(int partyId, int candidateId, CandidateDto candidateRequest);

    void deleteCandidateById(int candidateId);

    //void deleteCandidate(int partyId, int candidateId);
}
