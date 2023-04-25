package com.smartcontactmanager.code.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcontactmanager.code.Entity.user;

public interface Dao extends JpaRepository<user, Integer> {

}
