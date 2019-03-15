package com.example.assignment6server.services;

import com.example.assignment6server.models.Topic;
import com.example.assignment6server.models.Widget;
import com.example.assignment6server.repositories.TopicRepository;
import com.example.assignment6server.repositories.WidgetRepository;

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
public class WidgetService {
  @Autowired
  TopicRepository topicRepository;

  @Autowired
  WidgetRepository widgetRepository;

  @PostMapping("/api/topics/{tid}/widgets")
  public Widget createWidget(@PathVariable("tid") Integer tid, @RequestBody Widget widget) {
    widget.setTopic(topicRepository.findById(tid).get());
    widgetRepository.save(widget);
    return widget;
  }

  @GetMapping("/api/topics/{tid}/widgets")
  public List<Widget> findAllWidgets(@PathVariable("tid") Integer tid) {
    Topic topic = topicRepository.findById(tid).get();
    return widgetRepository.findAllWidgets(topic);
  }

  @GetMapping("/api/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid") Integer wid) {
    return widgetRepository.findById(wid).get();
  }

  @PutMapping("/api/widgets/{wid}")
  public Widget updateWidget(@PathVariable("wid") Integer wid, @RequestBody Widget newWidget) {
    Widget widget = widgetRepository.findById(wid).get();
    widget.set(newWidget);
    widgetRepository.save(newWidget);
    return widget;
  }

  @DeleteMapping("/api/widgets/{wid}")
  public void deleteWidget(@PathVariable("wid") Integer wid) {
    widgetRepository.deleteById(wid);
  }

  @DeleteMapping("/api/widgets/deleteAll/{tid}")
  public void deleteAllWidgets(@PathVariable("tid")Integer tid){
    List<Widget> widgets = findAllWidgets(tid);
    for (Widget widget: widgets){
      widgetRepository.delete(widget);
    }
  }
}

