package com.example.assignment6server.services;

import com.example.assignment6server.models.ListWidget;
import com.example.assignment6server.models.Widget;
import com.example.assignment6server.repositories.ListWidgetRepository;
import com.example.assignment6server.repositories.TopicRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
public class ListWidgetService {
  @Autowired
  TopicRepository topicRepository;

  @Autowired
  ListWidgetRepository listWidgetRepository;

  @PostMapping("/api/topics/{tid}/list/widgets")
  public ListWidget createWidget(@PathVariable("tid") Integer tid, @RequestBody ListWidget widget) {
    widget.setTopic(topicRepository.findById(tid).get());
    listWidgetRepository.save(widget);
    return widget;
  }

  @GetMapping("/api/topics/{tid}/list/widgets")
  public List<ListWidget> findAllWidgets(@PathVariable("tid") Integer tid) {
    return (List<ListWidget>) listWidgetRepository.findAll();
  }

  @GetMapping("/api/list/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid")Integer wid){
    return listWidgetRepository.findById(wid).get();
  }

  @PutMapping("/api/list/widgets/{wid}")
  public ListWidget updateWidget(@PathVariable("wid") Integer wid,@RequestBody ListWidget newWidget){
    ListWidget widget = listWidgetRepository.findById(wid).get();
    widget.set(newWidget);
    listWidgetRepository.save(newWidget);
    return widget;
  }

  @DeleteMapping("/api/list/widgets/{wid}")
  public void deleteWidget(@PathVariable("wid") Integer wid){
    listWidgetRepository.deleteById(wid);
  }
}
