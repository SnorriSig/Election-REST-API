package sem3.electionrestapi.service;

import sem3.electionrestapi.payload.PartyDto;

import java.util.List;

public interface PartyService {

    PartyDto createParty(PartyDto partyDto);

    List<PartyDto> getAllParties();
}
