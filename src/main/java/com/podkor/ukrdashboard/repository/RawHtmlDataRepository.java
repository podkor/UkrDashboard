package com.podkor.ukrdashboard.repository;

import com.podkor.ukrdashboard.dto.FeedType;
import com.podkor.ukrdashboard.dto.RawHtmlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RawHtmlDataRepository extends JpaRepository<RawHtmlData, Long> {

    Optional<RawHtmlData> findOneByFeedType(FeedType feedType);
    Optional<RawHtmlData> findOneById(Long id);

    @Modifying
    @Query("update RawHtmlData rhd set rhd.htmlData = :htmlData where rhd.feedType = :feedType")
    void updateByFeedType(FeedType feedType, String htmlData);
}
