package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public Group map(final GroupDto groupDto) {
        return Group.builder()
                .id(groupDto.getId())
                .groupName(groupDto.getGroupName())
                .description(groupDto.getDescription())
                .build();
    }

    public GroupDto mapToDto(final Group group) {
        return new GroupDto(
                group.getId(),
                group.getGroupName(),
                group.getDescription());
    }

    public List<GroupDto> mapToDtoList(final List<Group> groupList) {
        return groupList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
