package org.lukaszse.contractorsapp.contractors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lukaszse.contractorsapp.orders.Order;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ContractorServiceImplTest {

    @Test
    @DisplayName("Contractor with given name already exist")
    void addContractor_throwsIllegalArgumnentException() {

        // given -----------------------------------
        String company = "Company Name";
        Contractor mockContractor = getMockContractor(company);
        // and
        ContractorRepository mockContractorRepo = getMockContractorRepository(mockContractor);


        // system under test ---------------------------
        var toTest = new ContractorServiceImpl(mockContractorRepo);


        // when ----------------------------------------
        var exception = catchThrowable(() -> toTest.addContractor(mockContractor));


        // then ----------------------------------------
        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("already exist");


    }


    @Test
    @DisplayName("Contractor succesfully added")
    void addContractor_Ok() {

        // given -----------------------------------
        String company1 = "Company Name";
        Contractor mockContractor1 = getMockContractor(company1);

        String company2 = "Other Company Name";
        Contractor mockContractor2 = getMockContractor(company2);
        // and
        ContractorRepository mockContractorRepo = getMockContractorRepository(mockContractor1);


        // system under test ---------------------
        var toTest = new ContractorServiceImpl(mockContractorRepo);


        // when -----------------------------------
        var result = toTest.addContractor(mockContractor2);


        // then -----------------------------------
    }

    private ContractorRepository getMockContractorRepository(Contractor mockContractor) {
        List<Contractor> mockList = new ArrayList<>();
        mockList.add(mockContractor);
        var mockContractorRepo = mock(ContractorRepository.class);
        when(mockContractorRepo.findAll()).thenReturn(mockList);
        return mockContractorRepo;
    }

    private Contractor getMockContractor(String company) {
        var mockContractor = mock(Contractor.class);
        when(mockContractor.getName()).thenReturn(company);
        return mockContractor;
    }
}



// could be useful in future
/*        String city = "city";
        String country = "Poland";
        String street = "example street";*/
/*        when(mockContractor.getCity()).thenReturn("Excample City");
        when(mockContractor.getCountry()).thenReturn("Poland");
        when(mockContractor.getStreet()).thenReturn("Ulica");
        when(mockContractor.getProperty()).thenReturn(23);*/
