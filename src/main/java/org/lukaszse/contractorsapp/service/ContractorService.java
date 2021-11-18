package org.lukaszse.contractorsapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lukaszse.contractorsapp.exceptions.ConflictException;
import org.lukaszse.contractorsapp.exceptions.NotFoundException;
import org.lukaszse.contractorsapp.model.Contractor;
import org.lukaszse.contractorsapp.model.Order;
import org.lukaszse.contractorsapp.repository.ContractorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContractorService {

    public static final String CONTRACTOR_CONFLICT_ERROR_MESSAGE = "Contractor with given id=%s already exist.";
    public static final String CONTRACTOR_NOT_FOUND_ERROR_MESSAGE = "Contractor with id=%s has not been found.";
    private final ContractorRepository repository;


    public List<Contractor> findAll() {
        return repository.findAll();
    }

    public Contractor getContractor(Integer id) {
            return repository.findById(id)
                    .orElse(null);
    }

    public void addEditContractor(final Contractor contractor) {
        if(contractor.getId() == null) {
            addContractor(contractor);
        } else {
            editContractor(contractor);
        }
    }

    public void addContractor(Contractor contractor) {
        repository.save(contractor);
    }

    public void editContractor(Contractor contractor) {
        if(!repository.existsById(contractor.getId())) {
            throw new NotFoundException(CONTRACTOR_NOT_FOUND_ERROR_MESSAGE.formatted(contractor.getId()));
        }
        repository.save(contractor);
    }

    public void deleteContractor(Integer Contractorid) {
        repository.deleteById(Contractorid);
    }

    public Page<Contractor> getPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        var users = repository.findAll();
        var contractorsPage = Stream.of(users)
                .filter(orderList -> !orderList.isEmpty())
                .flatMap(Collection::stream)
                .skip((long) currentPage * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        return new PageImpl<Contractor>(contractorsPage, PageRequest.of(currentPage, pageSize), users.size());
    }
}
