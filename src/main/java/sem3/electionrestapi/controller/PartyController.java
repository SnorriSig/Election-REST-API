package sem3.electionrestapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sem3.electionrestapi.entity.Party;
import sem3.electionrestapi.payload.PartyDto;
import sem3.electionrestapi.service.PartyService;

import java.util.List;

@RestController
@RequestMapping("/api/parties")
public class PartyController {

    private PartyService partyService;

    public PartyController(PartyService partyService) {
        this.partyService = partyService;
    }

    // add new political party rest api
    @PostMapping
    public ResponseEntity<PartyDto> createParty(@RequestBody PartyDto partyDto) {
        return new ResponseEntity<>(partyService.createParty(partyDto), HttpStatus.CREATED);
    }

    // list all political parties rest api
    @GetMapping
    public List<PartyDto> getAllParties() {
        return partyService.getAllParties();
    }

    // get party by id
    @GetMapping("/{id}")
    public ResponseEntity<PartyDto> getPartyById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(partyService.getPartyById(id));
    }
}
