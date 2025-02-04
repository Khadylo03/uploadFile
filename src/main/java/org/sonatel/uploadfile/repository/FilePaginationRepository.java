package org.sonatel.uploadfile.repository;

import org.sonatel.uploadfile.domain.entity.FileEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilePaginationRepository extends PagingAndSortingRepository <FileEntity, Integer> {
}
