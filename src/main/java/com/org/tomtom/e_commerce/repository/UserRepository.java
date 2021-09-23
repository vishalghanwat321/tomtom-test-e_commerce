package com.org.tomtom.e_commerce.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.org.tomtom.e_commerce.service.model.user.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor, Serializable {

}
