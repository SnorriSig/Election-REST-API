package sem3.electionrestapi.service;

import sem3.electionrestapi.payload.PartyDto;
import sem3.electionrestapi.payload.PartyResponse;

import java.util.List;

public interface PartyService {

    PartyDto createParty(PartyDto partyDto);

    PartyResponse getAllParties(int pageNo, int pageSize, String sortBy, String sortDir);

    PartyDto getPartyById(int id);
}
