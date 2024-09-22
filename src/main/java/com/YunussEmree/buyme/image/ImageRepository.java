package com.YunussEmree.buyme.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Blob;

public interface ImageRepository extends JpaRepository<Blob, Long> {
}
