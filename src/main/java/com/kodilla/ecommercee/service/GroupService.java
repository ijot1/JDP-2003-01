package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.EntityNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupMapper groupMapper;

    public GroupDto create(GroupDto groupDto) {
        Group group = Group.builder()
                .id(null)
                .groupName(groupDto.getGroupName())
                .description(groupDto.getDescription())
                .build();
        return groupMapper.mapToDto(groupRepository.save(group));
    }

    public GroupDto update(GroupDto groupDto) {
        groupRepository.findById(groupDto.getId()).orElseThrow(() -> new EntityNotFoundException(Group.class, groupDto.getId()));
        return groupMapper.mapToDto(groupRepository.save(groupMapper.map(groupDto)));
    }

    public List<GroupDto> getGroups() {
        return groupMapper.mapToDtoList(groupRepository.findAll());
    }

    public GroupDto getGroup(final Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return groupMapper.mapToDto(group.orElseThrow(() -> new EntityNotFoundException(Group.class, id)));
    }
}