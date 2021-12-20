package sem3.electionrestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.electionrestapi.payload.CandidateDto;
import sem3.electionrestapi.service.CandidateService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CandidateController {

    private CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/parties/{partyId}/candidates")
    public ResponseEntity<CandidateDto> createCandidate(@PathVariable(value = "partyId") int partyId,
                                                        @RequestBody CandidateDto candidateDto) {
        return new ResponseEntity<>(candidateService.createCandidate(partyId, candidateDto), HttpStatus.CREATED);
    }

    @GetMapping("/parties/{partyId}/candidates")
    private List<CandidateDto> getCandidatesByPartyId(@PathVariable(value = "partyId") int partyId) {
        return candidateService.getCandidatesByPartyId(partyId);
    }

    @PutMapping("/parties/{partyId}/candidates/{candidateId}")
    public ResponseEntity<CandidateDto> updateCandidate(@PathVariable(value = "partyId") int partyId,
                                                        @PathVariable(value = "candidateId") int candidateId,
                                                        @RequestBody CandidateDto candidateDto) {
        CandidateDto updateCandidate = candidateService.updateCandidate(partyId, candidateId, candidateDto);
        return new ResponseEntity<>(updateCandidate, HttpStatus.OK);
    }

    @DeleteMapping("/parties/{partyId}/candidates/{candidateId}")
    public ResponseEntity<String> deleteCandidate(@PathVariable(value = "partyId") int partyId, @PathVariable(value = "candidateId") int candidateId) {
        candidateService.deleteCandidate(partyId,candidateId);
        return new ResponseEntity<>("Candidate has been successfully removed", HttpStatus.OK);
    }
}
