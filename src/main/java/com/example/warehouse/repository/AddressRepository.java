package com.example.warehouse.repository;

import com.example.warehouse.model.dao.AddressDAO;
import com.example.warehouse.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long> {
    @Query(value = "SELECT * FROM Addresses a WHERE a.profile_id =?1", nativeQuery = true)
    List<Address> findByProfile(Long profileId);

    Address findByAddressName(String addressName);

}

