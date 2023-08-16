package com.podkor.ukrdashboard.repository;

import com.podkor.ukrdashboard.dto.Category;
import com.podkor.ukrdashboard.dto.DataTab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataTabRepository extends JpaRepository<DataTab, Long> {

    DataTab getDataTabById(Long id);

    List<DataTab> getAllByCategory(Category category);
}
