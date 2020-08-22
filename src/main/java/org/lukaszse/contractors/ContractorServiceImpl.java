package org.lukaszse.contractors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class ContractorServiceImpl implements ContractorService {

    // == fields ==
    private final ContractorsRepository repository;

    @Autowired
    public ContractorServiceImpl(ContractorsRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        System.out.println("===================================================");
        System.out.println("=========  this is your database print  ===========");
        System.out.println(findAll().toString());
        System.out.println("================  this is the end  ================");
    }

    @Override
    public List<Contractor> findAll() {
        return repository.findAll();
    }

    @Override
    public String getContractorList() {
        return null;
    }

    @Override
    public boolean addContractor(Contractor contractor) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public ContractorsRepository getRepository() {
        return repository;
    }
}
