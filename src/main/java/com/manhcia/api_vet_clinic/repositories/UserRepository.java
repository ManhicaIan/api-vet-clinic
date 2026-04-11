package com.manhcia.api_vet_clinic.repositories;

import com.manhcia.api_vet_clinic.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
