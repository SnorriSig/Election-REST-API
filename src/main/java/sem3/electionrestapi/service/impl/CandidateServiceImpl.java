package sem3.electionrestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sem3.electionrestapi.entity.Candidate;
import sem3.electionrestapi.entity.Party;
import sem3.electionrestapi.exception.ElectionAPIException;
import sem3.electionrestapi.exception.ResourceNotFoundException;
import sem3.electionrestapi.payload.CandidateDto;
import sem3.electionrestapi.repository.CandidateRepository;
import sem3.electionrestapi.repository.PartyRepository;
import sem3.electionrestapi.service.CandidateService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    private CandidateRepository candidateRepository;
    private PartyRepository partyRepository;
    private ModelMapper mapper;

    public CandidateServiceImpl(CandidateRepository candidateRepository, PartyRepository partyRepository, ModelMapper mapper) {
        this.candidateRepository = candidateRepository;
        this.partyRepository = partyRepository;
        this.mapper = mapper;
    }

    @Override
    public CandidateDto createCandidate(int partyId, CandidateDto candidateDto) {
        Candidate candidate = mapToEntity(candidateDto);

        // retrieve party entity by id
        Party party = retrievePartyById(partyId);

        // set party to candidate entity
        candidate.setParty(party);

        // candidate entity to database
        Candidate newCandidate = candidateRepository.save(candidate);

        return mapToDTO(newCandidate);
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        // retrieve all candidates
        List<Candidate> candidates = candidateRepository.findAll();
        // convert list of candidate entities to list of candidate dto's
        return candidates.stream().map(candidate -> mapToDTO(candidate)).collect(Collectors.toList());
    }

    @Override
    public CandidateDto getCandidateByPartyIdAndCandidateId(int partyId, int candidateId) {
        // retrieve party entity by id
        Party party = retrievePartyById(partyId);

        // retrieve candidate by id
        Candidate candidate = retrieveCandidateById(candidateId);

        // check if candidate belongs to party
        checkEntityRelation(party,candidate);
        return mapToDTO(candidate);
    }

    @Override
    public List<CandidateDto> getCandidatesByPartyId(int partyId) {
        // retrieve candidates by partyId
        List<Candidate> candidates = candidateRepository.findByPartyId(partyId);

        // convert list of candidate entities to list of candidate dto's
        return candidates.stream().map(candidate -> mapToDTO(candidate)).collect(Collectors.toList());
    }

    @Override
    public CandidateDto updateCandidate(int partyId, int candidateId, CandidateDto candidateRequest) {
        // retrieve party entity by id
        Party party = retrievePartyById(partyId);

        // retrieve candidate by id
        Candidate candidate = retrieveCandidateById(candidateId);

        // check if candidate belongs to party
        checkEntityRelation(party,candidate);

        candidate.setFirstName(candidateRequest.getFirstName());
        candidate.setLastName(candidateRequest.getLastName());

        Candidate updatedCandidate = candidateRepository.save(candidate);
        return mapToDTO(updatedCandidate);
    }

    @Override
    public void deleteCandidateById(int candidateId) {
        // retrieve candidate by id
        Candidate candidate = retrieveCandidateById(candidateId);
        candidateRepository.delete(candidate);
    }

//    @Override
//    public void deleteCandidate(int partyId, int candidateId) {
//        // retrieve party by id
//        Party party = retrievePartyById(partyId);
//
//        // retrieve candidate by id
//        Candidate candidate = retrieveCandidateById(candidateId);
//
//        // check if candidate belongs to party
//        checkEntityRelation(party,candidate);
//        candidateRepository.delete(candidate);
//    }

    // retrieve party entity by id
    private Party retrievePartyById(int partyId) {
        Party party = partyRepository.findById(partyId).orElseThrow(
                () -> new ResourceNotFoundException("Party", "id", partyId));
        return party;
    }

    // retrieve candidate by id
    private Candidate retrieveCandidateById(int candidateId) {
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(()-> new ResourceNotFoundException("Candidate", "id", candidateId));
        return candidate;
    }

    // check if candidate belongs to party
    private void checkEntityRelation(Party party, Candidate candidate) {
        if(candidate.getParty().getId() != party.getId()) {
            throw new ElectionAPIException(HttpStatus.BAD_REQUEST, "Candidate does not belong to this political party");
        }
    }

    // convert Entity into DTO
    private CandidateDto mapToDTO(Candidate candidate){
        CandidateDto candidateDto = mapper.map(candidate, CandidateDto.class);
        return candidateDto;
    }

    // convert DTO to entity
    private Candidate mapToEntity(CandidateDto candidateDto){
        Candidate candidate = mapper.map(candidateDto, Candidate.class);
        return candidate;
    }
}
