package com.demo.models;

import java.util.List;

 
public class Task {
    
    public String name;
    public Integer duration;
    public String groupId;
    public String assigneeId;
    
    public List<String> subtaskIds;
    public boolean finished;
}