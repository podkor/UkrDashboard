package com.podkor.ukrdashboard.repository;

import com.podkor.ukrdashboard.dto.DataFeed;
import com.podkor.ukrdashboard.dto.DataTab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataFeedRepository extends JpaRepository<DataFeed, Long> {

    DataFeed getDataFeedByDataTab(DataTab dataTab);
}
