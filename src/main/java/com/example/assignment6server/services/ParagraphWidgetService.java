package com.example.assignment6server.services;

import com.example.assignment6server.models.ParagraphWidget;
import com.example.assignment6server.models.Widget;
import com.example.assignment6server.repositories.ParagraphWidgetRepository;
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
public class ParagraphWidgetService {
  @Autowired
  TopicRepository topicRepository;

  @Autowired
  ParagraphWidgetRepository paragraphWidgetRepository;

  @PostMapping("/api/topics/{tid}/paragraph/widgets")
  public ParagraphWidget createWidget(@PathVariable("tid") Integer tid, @RequestBody ParagraphWidget widget) {
    widget.setTopic(topicRepository.findById(tid).get());
    paragraphWidgetRepository.save(widget);
    return widget;
  }

  @GetMapping("/api/topics/{tid}/paragraph/widgets")
  public List<ParagraphWidget> findAllWidgets(@PathVariable("tid") Integer tid) {
    return (List<ParagraphWidget>) paragraphWidgetRepository.findAll();
  }

  @GetMapping("/api/paragraph/widgets/{wid}")
  public Widget findWidgetById(@PathVariable("wid")Integer wid){
    return paragraphWidgetRepository.findById(wid).get();
  }

  @PutMapping("/api/paragraph/widgets/{wid}")
  public ParagraphWidget updateWidget(@PathVariable("wid") Integer wid,@RequestBody ParagraphWidget newWidget){
    ParagraphWidget widget = paragraphWidgetRepository.findById(wid).get();
    widget.set(newWidget);
    paragraphWidgetRepository.save(newWidget);
    return widget;
  }

  @DeleteMapping("/api/paragraph/widgets/{wid}")
  public void deleteWidget(@PathVariable("wid") Integer wid){
    paragraphWidgetRepository.deleteById(wid);
  }
}

