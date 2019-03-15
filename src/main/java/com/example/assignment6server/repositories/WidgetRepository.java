package com.example.assignment6server.repositories;

import com.example.assignment6server.models.Topic;
import com.example.assignment6server.models.Widget;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
  @Query("SELECT widget FROM Widget widget WHERE widget.topic=:topic")
  public List<Widget> findAllWidgets(@Param("topic") Topic topic);

  @Query("DELETE FROM Widget widget WHERE widget.topic=:topic")
  public void deleteAllWidgetsForATopic(@Param("topic") Topic topic);
}
