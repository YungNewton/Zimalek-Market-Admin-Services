package com.zmarket.marketadminservice.modules.states.repository;

import com.zmarket.marketadminservice.modules.states.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    Optional<State> findFirstByName(String name);

}