package sem3.electionrestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sem3.electionrestapi.entity.Party;
import sem3.electionrestapi.payload.PartyDto;
import sem3.electionrestapi.repository.PartyRepository;
import sem3.electionrestapi.service.PartyService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartyServiceImpl implements PartyService {

    private PartyRepository partyRepository;

    private ModelMapper mapper;

    // @Autowired Spring 4.3+ does not need this if there is only one constructor


    public PartyServiceImpl(PartyRepository partyRepository, ModelMapper mapper) {
        this.partyRepository = partyRepository;
        this.mapper = mapper;
    }

    @Override
    public PartyDto createParty(PartyDto partyDto) {

        // convert DTO to entity
        Party party = mapToEntity(partyDto);
        Party newParty = partyRepository.save(party);

        // convert entity to DTO
        PartyDto partyResponse = mapToDTO(newParty);
        return partyResponse;
    }

    @Override
    public List<PartyDto> getAllParties() {
        List<Party> parties = partyRepository.findAll();
        return parties.stream().map(party -> mapToDTO(party)).collect(Collectors.toList());
    }

    // convert Entity into DTO
    private PartyDto mapToDTO(Party party){
        PartyDto partyDto = mapper.map(party, PartyDto.class);
        return partyDto;
    }

    // convert DTO to entity
    private Party mapToEntity(PartyDto partyDto){
        Party party = mapper.map(partyDto, Party.class);
        return party;
    }
}
