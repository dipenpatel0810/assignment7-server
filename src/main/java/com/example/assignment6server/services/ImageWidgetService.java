package com.example.assignment6server.services;

import com.example.assignment6server.models.ImageWidget;
import com.example.assignment6server.repositories.ImageWidgetRepository;
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
public class ImageWidgetService {
  @Autowired
  TopicRepository topicRepository;

  @Autowired
  ImageWidgetRepository imageWidgetRepository;

  @PostMapping("/api/topics/{tid}/image/widgets")
  public ImageWidget createWidget(@PathVariable("tid") Integer tid, @RequestBody ImageWidget widget) {
    widget.setTopic(topicRepository.findById(tid).get());
    imageWidgetRepository.save(widget);
    return widget;
  }

  @GetMapping("/api/topics/{tid}/image/widgets")
  public List<ImageWidget> findAllWidgets(@PathVariable("tid") Integer tid) {
    return (List<ImageWidget>) imageWidgetRepository.findAll();
  }

  @GetMapping("/api/image/widgets/{wid}")
  public ImageWidget findWidgetById(@PathVariable("wid") Integer wid) {
    return imageWidgetRepository.findById(wid).get();
  }

  @PutMapping("/api/image/widgets/{wid}")
  public ImageWidget updateWidget(@PathVariable("wid") Integer wid, @RequestBody ImageWidget newWidget) {
    ImageWidget widget = imageWidgetRepository.findById(wid).get();
    widget.set(newWidget);
    imageWidgetRepository.save(newWidget);
    return widget;
  }

  @DeleteMapping("/api/image/widgets/{wid}")
  public void deleteWidget(@PathVariable("wid") Integer wid) {
    imageWidgetRepository.deleteById(wid);
  }

}
