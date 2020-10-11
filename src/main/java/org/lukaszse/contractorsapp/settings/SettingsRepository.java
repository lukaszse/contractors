package org.lukaszse.contractorsapp.settings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer> {

    public List<Settings> findAll();
    public Optional<Settings> findFirstByOrderByIdDesc();
}
