package sem3.electionrestapi.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sem3.electionrestapi.entity.Party;
import sem3.electionrestapi.exception.ResourceNotFoundException;
import sem3.electionrestapi.payload.PartyDto;
import sem3.electionrestapi.payload.PartyResponse;
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
    public PartyResponse getAllParties(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Party> parties = partyRepository.findAll(pageable);

        // get content for page object
        List<Party> listOfParties = parties.getContent();

        List<PartyDto> content = listOfParties.stream().map(party -> mapToDTO(party)).collect(Collectors.toList());

        PartyResponse partyResponse = new PartyResponse();
        partyResponse.setContent(content);
        partyResponse.setPageNo(parties.getNumber());
        partyResponse.setPageSize(parties.getSize());
        partyResponse.setTotalElements(parties.getTotalElements());
        partyResponse.setTotalPages(parties.getTotalPages());
        partyResponse.setLast(parties.isLast());

        return partyResponse;
    }

    @Override
    public PartyDto getPartyById(int id) {
        Party party = partyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Party", "id", id));
        return mapToDTO(party);
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
