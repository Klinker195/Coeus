package dao;

import java.util.ArrayList;

import entities.Topic;

public interface TopicDAO {

	ArrayList<Topic> getAllTopicNamesByProjectName(String ProjectName);

}