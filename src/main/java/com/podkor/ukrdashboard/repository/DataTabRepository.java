package com.podkor.ukrdashboard.repository;

import com.podkor.ukrdashboard.dto.Category;
import com.podkor.ukrdashboard.dto.DataTab;
import com.podkor.ukrdashboard.dto.FeedType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataTabRepository extends JpaRepository<DataTab, Long> {

    DataTab getDataTabById(Long id);

    List<DataTab> getAllByCategory(Category category);

    @Modifying
    @Query("update DataTab dt set dt.htmlData = :htmlData where dt.id = :tabId")
    void updateHtmlData(Long tabId, String htmlData);
}
