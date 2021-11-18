package org.lukaszse.contractorsapp.repository;

import org.lukaszse.contractorsapp.model.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {

    List<Settings> findAll();
    Optional<Settings> findFirstByOrderByIdDesc();
}
